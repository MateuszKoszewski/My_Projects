package com.example.auction.services;

import com.example.auction.model.dao.*;
import com.example.auction.model.dto.*;
import com.example.auction.model.exceptions.AppErrorMessage;
import com.example.auction.model.exceptions.AppException;
import com.example.auction.repositories.AuctionRepository;
import com.example.auction.repositories.CategoryRepository;
import com.example.auction.repositories.ImagesRepository;
import com.example.auction.repositories.UsersRepository;
import com.example.auction.utils.FileUploadUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.core.io.InputStreamSource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final CategoryRepository categoryRepository;
    private final ImagesRepository imagesRepository;
    private final UsersRepository usersRepository;
    private final ServletContext servletContext;

    public List<GetAuctionResponse> getAllAuctions() {
        return auctionRepository.findAll().stream().map(auction -> GetAuctionResponse.builder()
                .title(auction.getTitle())
                .description(auction.getDescription())
                .pictures(mapToImageModel(auction.getPictures()))
                .build()).collect(Collectors.toList());
    }

    public AddCategoryResponse addCategory(AddCategoryRequest addCategoryRequest) {
        CategoryEntity newCategory = new CategoryEntity();
        newCategory.setName(addCategoryRequest.getName());
        newCategory.setDescription(addCategoryRequest.getDescription());
        categoryRepository.save(newCategory);
        return new AddCategoryResponse("category added");
    }

    public List<GetCategoriesResponse> getCategories() {
        return categoryRepository.findAll().stream().map(category ->
                GetCategoriesResponse.builder()
                        .name(category.getName())
                        .description(category.getDescription())
                        .build()).collect(Collectors.toList());
    }

    public void saveImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String uploadDir = "src/main/resources/images/filesAddedAt" + LocalDate.now();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
    }

    public String addAuction(List<MultipartFile> file, String auction) {
        List<ImageEntity> imageEntityList = new ArrayList<>();
        addToDBAndToImageEntityList(file.get(0), true, imageEntityList);
        for (int i = 1; i < file.size(); i++) {
            addToDBAndToImageEntityList(file.get(i), false, imageEntityList);
        }
        AddAuctionResponse addAuctionResponse = null;
        try {
            addAuctionResponse = new ObjectMapper().readValue(auction, AddAuctionResponse.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        if (addAuctionResponse != null) {
            CategoryEntity categoryEntity = categoryRepository.findByName(addAuctionResponse.getCategory());
            UserEntity userEntity = usersRepository.findByEmailAddress(addAuctionResponse.getEmailAddress()).get();
            LocalizationEntity localizationEntity = new LocalizationEntity();
            localizationEntity.setCounty(addAuctionResponse.getAddress().getCounty());
            localizationEntity.setCity(addAuctionResponse.getAddress().getCity());
            localizationEntity.setPostCode(addAuctionResponse.getAddress().getPostCode());
            AuctionEntity auctionEntity = new AuctionEntity();
            auctionEntity.setTitle(addAuctionResponse.getTitle());
            auctionEntity.setDescription(addAuctionResponse.getDescription());
            auctionEntity.setPictures(imageEntityList);
            auctionEntity.setCategory(categoryEntity);
            auctionEntity.setMinPrice(addAuctionResponse.getMinPrice());
            auctionEntity.setBuyNowPrice(addAuctionResponse.getBuyNowPrice());
            auctionEntity.setPromoted(addAuctionResponse.isPromoted());
            auctionEntity.setLocalization(localizationEntity);
            auctionEntity.setDateOfStart(LocalDate.now());
            auctionEntity.setDateOfFinish(LocalDate.now().plusDays(14));
            auctionEntity.setUserEntity(userEntity);
            auctionRepository.save(auctionEntity);
        }
        return auction;
    }



//    public String addAuction(String auction) {
//        AddAuctionResponse addAuctionResponse = null;
//        try {
//            addAuctionResponse = new ObjectMapper().readValue(auction, AddAuctionResponse.class);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//
//        if (addAuctionResponse != null) {
//            CategoryEntity categoryEntity = categoryRepository.findByName(addAuctionResponse.getCategory());
//            UserEntity userEntity = usersRepository.findByEmailAddress(addAuctionResponse.getEmailAddress()).get();
//            LocalizationEntity localizationEntity = new LocalizationEntity();
//            localizationEntity.setCounty(addAuctionResponse.getAddress().getCounty());
//            localizationEntity.setCity(addAuctionResponse.getAddress().getCity());
//            localizationEntity.setPostCode(addAuctionResponse.getAddress().getPostCode());
//            AuctionEntity auctionEntity = new AuctionEntity();
//            auctionEntity.setTitle(addAuctionResponse.getTitle());
//            auctionEntity.setDescription(addAuctionResponse.getDescription());
//            auctionEntity.setCategory(categoryEntity);
//            auctionEntity.setMinPrice(addAuctionResponse.getMinPrice());
//            auctionEntity.setBuyNowPrice(addAuctionResponse.getBuyNowPrice());
//            auctionEntity.setPromoted(addAuctionResponse.isPromoted());
//            auctionEntity.setLocalization(localizationEntity);
//            auctionEntity.setDateOfStart(LocalDate.now());
//            auctionEntity.setDateOfFinish(LocalDate.now().plusDays(14));
//            auctionEntity.setUserEntity(userEntity);
//            auctionRepository.save(auctionEntity);
//        }
//        return auction;
//    }


    private void addToDBAndToImageEntityList(MultipartFile file, boolean isMainImage, List<ImageEntity> listOfImageEntity) {
        ImageEntity imageEntity = new ImageEntity();
        String uploadPath = "src/main/resources/images/filesAddedAt" + LocalDate.now();

        String fileName = file.getOriginalFilename();
        imageEntity.setMain(isMainImage);
        imageEntity.setDirectory(uploadPath);
        imageEntity.setPath(uploadPath+"/"+fileName);
        try {
            FileUploadUtil.saveFile(uploadPath, fileName, file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        listOfImageEntity.add(imageEntity);
        imagesRepository.save(imageEntity);
    }

    public List<GetAuctionResponse> getAuctionsByUser(String userEmail) {

        UserEntity userEntity = usersRepository.findByEmailAddress(userEmail).orElseThrow(() -> new AppException(AppErrorMessage.USER_DOES_NOT_EXISTS, userEmail));
        List<AuctionEntity> listOfAuctionsByUser = auctionRepository.findByUserEntity(userEntity);
        return listOfAuctionsByUser.stream().map(auction -> GetAuctionResponse.builder().
                title(auction.getTitle())
                .description(auction.getDescription())
                .pictures(mapToImageModel(auction.getPictures()))
                .buyNowPrice(auction.getBuyNowPrice())
                .minPrice(auction.getMinPrice())
                .dateOfStart(auction.getDateOfStart())
                .dateOfFinish(auction.getDateOfFinish())
                .localization(auction.getLocalization())
                .user(GetUserResponse.builder()
                        .emailAddress(auction.getUserEntity().getEmailAddress())
                        .address(auction.getUserEntity().getAddressEntity())
                        .name(auction.getUserEntity().getName())
                        .lastName(auction.getUserEntity().getLastName())
                        .build())
                .build()).collect(Collectors.toList());
    }

    private List<ImageModel> mapToImageModel (List<ImageEntity> listOfImageEntities){
        return listOfImageEntities.stream().map(picture -> {
            File file = new File(picture.getPath());
            String encodedString = null;
            try (FileInputStream imageInFile = new FileInputStream(file)){

                byte[] fileContent = FileUtils.readFileToByteArray(file);
                imageInFile.read(fileContent);
                encodedString = Base64.getEncoder().encodeToString(fileContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ImageModel.builder().base64Image(encodedString).path(picture.getPath()).directory(picture.getDirectory()).main(picture.isMain()).build();
        }).collect(Collectors.toList());
    }

//    public Resource loadFile(String filename) {
//        try {
//            Path file = rootLocation.resolve(filename);
//            Resource resource = new UrlResource(file.toUri());
//            if (resource.exists() || resource.isReadable()) {
//                return resource;
//            } else {
//                throw new RuntimeException("FAIL!");
//            }
//        } catch (MalformedURLException e) {
//            throw new RuntimeException("FAIL!");
//        }
//    }
}