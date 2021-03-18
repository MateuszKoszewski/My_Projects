package com.example.auction.services;

import com.example.auction.model.dao.*;
import com.example.auction.model.dto.*;

import com.example.auction.repositories.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuctionService {

    private final AuctionRepository auctionRepository;

    private final CategoryService categoryService;
    private final UserService userService;
    private final NotificationService notificationService;
    private final ObservationService observationService;
    private final LicytationService licytationService;
    private final ImagesService imagesService;
    private final PurchaseService purchaseService;


    public List<GetAuctionResponse> getAllAuctions() {
        return auctionRepository.findAll().stream().map(auction -> GetAuctionResponse.builder()
                .title(auction.getTitle())
                .description(auction.getDescription())
                .pictures(mapToImageModel(auction.getPictures()))
                .build()).collect(Collectors.toList());
    }

    private AuctionEntity findAuctionById(Long id) {
        return auctionRepository.findById(id).orElseThrow(() -> new RuntimeException("auction doesn't exist"));
    }


    public String addAuction(List<MultipartFile> file, String auction) {

        String directory = "src/main/resources/images/filesAddedAt" + LocalDate.now();
        imagesService.uploadFiles(file, directory);
        List<ImageEntity> imageEntityList = imagesService.getImageEntityList(file, directory);
        imagesService.addImagesEntityDataToDb(imageEntityList);
        AddAuctionResponse addAuctionResponse = null;
        try {
            addAuctionResponse = new ObjectMapper().readValue(auction, AddAuctionResponse.class);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (addAuctionResponse != null) {
            AuctionEntity auctionEntity = prepareAuctionEntityToSave(addAuctionResponse, imageEntityList);
            auctionRepository.save(auctionEntity);
            UserEntity userEntity = userService.getUserByEmailAddress(addAuctionResponse.getEmailAddress());
            NotificationEntity notificationEntity = notificationService.CreateNotificationForAddingAuctions(userEntity, addAuctionResponse.getTitle());
            notificationService.saveNotificationToDB(notificationEntity);
        }
        return auction;
    }

    private AuctionEntity prepareAuctionEntityToSave(AddAuctionResponse addAuctionResponse, List<ImageEntity> imageEntityList) {
        CategoryEntity categoryEntity = categoryService.getCategoryEntityFromDB(addAuctionResponse.getCategory());
        UserEntity userEntity = userService.getUserByEmailAddress(addAuctionResponse.getEmailAddress());
        LocalizationEntity localizationEntity = new LocalizationEntity();
        localizationEntity.setCounty(addAuctionResponse.getAddress().getCounty());
        localizationEntity.setCity(addAuctionResponse.getAddress().getCity());
        AuctionEntity auctionEntity = new AuctionEntity();
        auctionEntity.setTitle(addAuctionResponse.getTitle());
        auctionEntity.setDescription(addAuctionResponse.getDescription());
        auctionEntity.setPictures(imageEntityList);
        auctionEntity.setCategory(categoryEntity);
        auctionEntity.setMinPrice(addAuctionResponse.getMinPrice());
        auctionEntity.setBuyNowPrice(addAuctionResponse.getBuyNowPrice());
        auctionEntity.setPromoted(addAuctionResponse.isPromoted());
        auctionEntity.setLocalization(localizationEntity);
        auctionEntity.setDateOfStart(LocalDateTime.now());
        auctionEntity.setDateOfFinish(LocalDateTime.now().plusDays(14));
        auctionEntity.setUserEntity(userEntity);
        auctionEntity.setActive(true);
        return auctionEntity;
    }


    public List<GetAuctionResponse> getAuctionsByUser(String userEmail, boolean isActive) {

        UserEntity userEntity = userService.getUserByEmailAddress(userEmail);
        List<AuctionEntity> listOfAuctionsByUser = auctionRepository.findByUserEntity(userEntity);
        List<AuctionEntity> listOfAuctionsFilteredByActive = listOfAuctionsByUser.stream().filter(auctionEntity -> auctionEntity.isActive() == isActive).collect(Collectors.toList());
        return listOfAuctionsFilteredByActive.stream().map(this::mapAuctionEntityToGetAuctionResponse).collect(Collectors.toList());

    }


    private GetAuctionResponse mapAuctionEntityToGetAuctionResponse(AuctionEntity auctionEntity) {
        return GetAuctionResponse.builder()
                .title(auctionEntity.getTitle())
                .description(auctionEntity.getDescription())
                .pictures(mapToImageModel(auctionEntity.getPictures()))
                .buyNowPrice(auctionEntity.getBuyNowPrice())
                .minPrice(auctionEntity.getMinPrice())
                .dateOfStart(mapLocalDateTimeToString(auctionEntity.getDateOfStart()))
                .dateOfFinish(mapLocalDateTimeToString(auctionEntity.getDateOfFinish()))
                .localization(mapLocalizationEntityToGetLocalizationResponse(auctionEntity.getLocalization()))
                .user(userService.mapUserEntityToGetUserResponse(auctionEntity.getUserEntity()))
                .isAcive(auctionEntity.isActive())
                .id(auctionEntity.getId())
                .build();
    }

    private GetLocalizationResponse mapLocalizationEntityToGetLocalizationResponse(LocalizationEntity localizationEntity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(localizationEntity, GetLocalizationResponse.class);
    }

    private List<ImageModel> mapToImageModel(List<ImageEntity> listOfImageEntities) {
        return listOfImageEntities.stream().map(picture -> {
            File file = new File(picture.getPath());
            String encodedString = null;
            try (FileInputStream imageInFile = new FileInputStream(file)) {

                byte[] fileContent = FileUtils.readFileToByteArray(file);
                imageInFile.read(fileContent);
                encodedString = Base64.getEncoder().encodeToString(fileContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ImageModel.builder().base64Image(encodedString).path(picture.getPath()).directory(picture.getDirectory()).main(picture.isMain()).build();
        }).collect(Collectors.toList());
    }

    public Map<String, HashSet<String>> getLocalizations() {
        List<AuctionEntity> listOfAllAuctions = auctionRepository.findAll();
        Map<String, HashSet<String>> localizationsMap = new HashMap<>();
        listOfAllAuctions.stream().map(AuctionEntity::getLocalization).collect(Collectors.toList()).forEach(localization -> {
            HashSet<String> insideSet;
            if (localizationsMap.get(localization.getCounty()) != null) {
                insideSet = localizationsMap.get(localization.getCounty());
            } else {
                insideSet = new HashSet<>();
            }
            HashSet<String> newSet = new HashSet<>(insideSet);
            newSet.add(localization.getCity());
            localizationsMap.put(localization.getCounty(), newSet);
        });
        return localizationsMap;
    }

    public List<GetAuctionResponseWithObserversAndLicytation> getAuctionsBySearchingTag(String searchingTag, String county, String city, String category, String userEmail) {

        List<AuctionEntity> listOfAllAuctions = auctionRepository.findAll();
        String tagToSearch = searchingTag.split(" ")[0];
        List<AuctionEntity> listOfFilteredAuctions = listOfAllAuctions.stream()
                .filter(categoriesAreEqual(category))
                .filter(citiesAreEqual(city))
                .filter(countiesAreEqual(county))
                .filter(containsTagSearch(tagToSearch))
                .filter(isActive())
                .collect(Collectors.toList());

        return prepareListToSendToClient(userEmail, listOfFilteredAuctions);
    }

    private Predicate<AuctionEntity> categoriesAreEqual(String category) {
        return auction -> !category.isBlank() ? auction.getCategory().getName().equals(category) : auction.getCategory() != null;
    }

    private Predicate<AuctionEntity> citiesAreEqual(String city) {
        return auction -> !city.isBlank() ? auction.getLocalization().getCity().equals(city) : auction.getLocalization() != null;
    }

    private Predicate<AuctionEntity> countiesAreEqual(String county) {
        return auction -> !county.isBlank() ? auction.getLocalization().getCounty().equals(county) : auction.getLocalization() != null;
    }

    private Predicate<AuctionEntity> containsTagSearch(String tagToSearch) {
        return auction -> auction.getTitle().contains(tagToSearch);
    }

    private Predicate<AuctionEntity> isActive() {
        return AuctionEntity::isActive;
    }


    private List<GetAuctionResponseWithObserversAndLicytation> prepareListToSendToClient(String userEmail, List<AuctionEntity> auctionEntityList) {
        return auctionEntityList.stream().map(auction -> GetAuctionResponseWithObserversAndLicytation.builder()
                .title(auction.getTitle())
                .localization(mapLocalizationEntityToGetLocalizationResponse(auction.getLocalization()))
                .pictures(mapToImageModel(auction.getPictures()))
                .user(userService.mapUserEntityToGetUserResponse(auction.getUserEntity()))
                .description(auction.getDescription())
                .minPrice(auction.getMinPrice())
                .dateOfStart(mapLocalDateTimeToString(auction.getDateOfStart()))
                .dateOfFinish(mapLocalDateTimeToString(auction.getDateOfFinish()))
                .buyNowPrice(auction.getBuyNowPrice())
                .id(auction.getId())
                .isActive(auction.isActive())
                .observations(observationService.getListOfAddObservationOfAuctionResponseFromAuctionEntityByAuction(auction))
                .licytations(licytationService.getListOfGetLicytationsResponseByAuction(auction))
                .loggedInUserSubscribedAuction(!userEmail.isBlank() && observationService.checkIfUserObserveThatAuction(userEmail, auction))
                .build()).collect(Collectors.toList());
    }


    public AddAuctionToObserveResponse addObservationOfAuction(AddAuctionToObserveRequest addAuctionToObserveRequest) {
        UserEntity user = userService.getUserByEmailAddress(addAuctionToObserveRequest.getUserEmail());
        AuctionEntity auction = findAuctionById(addAuctionToObserveRequest.getAuctionId());
        if (observationService.checkIfObservationByAuctionAndUserDoesntExist(auction, user)) {
            NotificationEntity notificationEntity = notificationService.createNotificationForSubscribing(user, auction);
            notificationService.saveNotificationToDB(notificationEntity);
            ObservationOfAuctionEntity observationOfAuctionEntity = observationService.createObservationEtity(auction, user);
            observationService.saveObservationEntityToDB(observationOfAuctionEntity);
        }

        return AddAuctionToObserveResponse.builder().title(auction.getTitle()).userEmail(user.getEmailAddress()).build();
    }

    public AddLicytationResponse addLicytation(AddLicytationRequest addLicytationRequest) {
        AuctionEntity auctionEntity = findAuctionById(addLicytationRequest.getId());
        UserEntity userEntity = userService.getUserByEmailAddress(addLicytationRequest.getUserEmail());
        double price = addLicytationRequest.getPrice();
        double maxValue = licytationService.getMaxValueFromLicytations(auctionEntity);
        if (price > maxValue) {
            LicytationEntity licytationEntity = licytationService.createLicytationEntity(auctionEntity, userEntity, price);
            if (licytationService.getLastLicytationOfAuction(auctionEntity) != null) {

                NotificationEntity notificationForUserBeingOverlicyted = createNotificationForUserBeingOverlicyted(auctionEntity, licytationEntity);

                notificationService.saveNotificationToDB(notificationForUserBeingOverlicyted);
            }
            NotificationEntity notificationForUserAuctionOwner = notificationService.createNotificationForUserForLicytatingHisAuction(auctionEntity, licytationEntity);
            notificationService.saveNotificationToDB(notificationForUserAuctionOwner);
            licytationService.saveLicytationToDB(licytationEntity);
        } else {
            throw new RuntimeException("Nie możesz licytować niższą kwotą niż " + maxValue);
        }
        return AddLicytationResponse.builder().message("licytation added").build();
    }

    public NotificationEntity createNotificationForUserBeingOverlicyted(AuctionEntity auctionEntity, LicytationEntity licytationEntity) {
        UserEntity userOfLastLicytation = licytationService.getLastLicytationOfAuction(auctionEntity).getUser();
        if (userOfLastLicytation != null) {
            return notificationService.createNotificationForBeingOverlicytated(userOfLastLicytation, auctionEntity, licytationEntity);
        } else {
            return null;
        }
    }


    public GetAuctionResponseWithObserversAndLicytation getAuctionById(Long auctionId) {
        AuctionEntity auctionEntity = findAuctionById(auctionId);

        return GetAuctionResponseWithObserversAndLicytation.builder()
                .id(auctionEntity.getId())
                .title(auctionEntity.getTitle())
                .description(auctionEntity.getDescription())
                .pictures(mapToImageModel(auctionEntity.getPictures()))
                .minPrice(auctionEntity.getMinPrice())
                .buyNowPrice((auctionEntity.getBuyNowPrice()))
                .dateOfStart(mapLocalDateTimeToString(auctionEntity.getDateOfStart()))
                .dateOfFinish(mapLocalDateTimeToString(auctionEntity.getDateOfFinish()))
                .localization(mapLocalizationEntityToGetLocalizationResponse(auctionEntity.getLocalization()))
                .user(userService.mapUserEntityToGetUserResponse(auctionEntity.getUserEntity()))
                .isActive(auctionEntity.isActive())
                .observations(observationService.getListOfAddObservationOfAuctionResponseFromAuctionEntityByAuction(auctionEntity))
                .licytations(licytationService.getListOfGetLicytationsResponseByAuction(auctionEntity))
                .duration(mapDurationToString(LocalDateTime.now(), auctionEntity.getDateOfFinish()))
                .build();

    }

    private String mapDurationToString(LocalDateTime startDate, LocalDateTime finishDate) {
        Duration duration = Duration.between(startDate, finishDate);
        if (duration.toDays() > 1) {
            return duration.toDays() + " dni";
        } else if (duration.toDays() == 1) {
            return duration.toDays() + " dzień";
        } else if (duration.toHours() > 1 && duration.toHours() < 24) {
            return duration.toHours() + " godzin";
        } else if (duration.toHours() == 1) {
            return duration.toHours() + " godzina";
        } else if (duration.toMinutes() < 60) {
            return duration.toMinutes() + " minut";
        }
        return "error";
    }

    private String mapLocalDateTimeToString(LocalDateTime localDateTime){
        LocalDateTime dateWithoutSecondsAndNanos = localDateTime.withSecond(0).withNano(0);
        String[] arrayOfDateParts = dateWithoutSecondsAndNanos.toString().split("T");
        return arrayOfDateParts[0]+ " " + arrayOfDateParts[1];
    }

    public List<GetNotificationsResponse> getNotifications(String userEmail) {
        UserEntity user = userService.getUserByEmailAddress(userEmail);
        List<NotificationEntity> list = notificationService.getNotificationListByUser(user);
        return list.stream().map(notificationService::mapNotificationEntityToGetNotificationResponse).collect(Collectors.toList());

    }

    public void deleteNotifications(String userEmail) {
        UserEntity userEntity = userService.getUserByEmailAddress(userEmail);
        notificationService.deleteNotificationsByUser(userEntity);
//        return "notifications deleted";
    }

    public AddPurchaseResponse addPurchaseBuyNowPrice(AddPurchaseRequest addPurchaseRequest) {
        AuctionEntity auctionEntity = findAuctionById(addPurchaseRequest.getAuctionId());
        auctionEntity.setActive(false);
        UserEntity userEntity = userService.getUserByEmailAddress(addPurchaseRequest.getUserEmail());
        PurchaseEntity purchaseEntity = purchaseService.createPurchaseEntity(userEntity, auctionEntity);
        purchaseService.savePurchaseInDB(purchaseEntity);
        return purchaseService.mapPurchaseEntityToAddPurchaseResponse(purchaseEntity);
    }

    public List<GetPurchaseResponse> getBoughtAuctionsOfUser(String userEmail) {
        UserEntity userEntity = userService.getUserByEmailAddress(userEmail);
        List<PurchaseEntity> listOfPurchaseEntity = purchaseService.getAllPurchaseByUser(userEntity);

        return listOfPurchaseEntity.stream().map(this::mapPurchaseEntityToGetPurchaseResponse).collect(Collectors.toList());
    }

    private GetPurchaseResponse mapPurchaseEntityToGetPurchaseResponse(PurchaseEntity purchaseEntity) {
        return GetPurchaseResponse.builder()
                .Id(purchaseEntity.getId())
                .auction(mapAuctionEntityToGetAuctionResponse(purchaseEntity.getAuctionEntity()))
                .user(userService.mapUserEntityToGetUserResponse(purchaseEntity.getUserEntity()))
                .date(mapLocalDateTimeToString(purchaseEntity.getDate()))
                .price(purchaseEntity.getPrice())
                .build();


    }


}