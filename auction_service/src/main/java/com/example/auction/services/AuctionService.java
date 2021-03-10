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
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final CategoryRepository categoryRepository;
    private final ImagesRepository imagesRepository;
    private final UsersRepository usersRepository;

    public List<GetAuctionResponse> getAllAuctions() {
        return auctionRepository.findAll().stream().map(auction -> GetAuctionResponse.builder()
                .title(auction.getTitle())
                .description(auction.getDescription())
                .pictures(auction.getPictures())
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


    private void addToDBAndToImageEntityList(MultipartFile file, boolean isMainImage, List<ImageEntity> listOfImageEntity) {
        ImageEntity imageEntity = new ImageEntity();
        String path = "src/main/resources/images/filesAddedAt" + LocalDate.now() + "/" + file.getOriginalFilename();
        imageEntity.setPath(path);
        imageEntity.setMain(isMainImage);
        try {
            saveImage(file);

        } catch (IOException e) {
            e.printStackTrace();
        }
        listOfImageEntity.add(imageEntity);
        imagesRepository.save(imageEntity);

    }
}