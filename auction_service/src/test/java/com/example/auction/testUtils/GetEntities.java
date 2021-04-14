package com.example.auction.testUtils;

import com.example.auction.model.dao.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class GetEntities {


    public static UserEntity getUserEntityForTests(){
        UserEntity userEntity = new UserEntity();
        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthority("ROLE_USER");
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setPostCode("14-500");
        addressEntity.setCity("Braniewo");
        addressEntity.setStreet("Kopernika");
        addressEntity.setCounty("warminsko-mazurskie");
        addressEntity.setNumberOfHouse(3);
        userEntity.setEmailAddress("agnieszka@agnieszka");
        userEntity.setName("admin");
        userEntity.setLastName("adminByAdmin");
        userEntity.setPassword("admin123");
        userEntity.setAddress(addressEntity);
        userEntity.setDateOfCreatingUser(LocalDate.now());
        userEntity.setAuthorityEntity(authorityEntity);
        return userEntity;
    }

    public static List<ImageEntity> getListOfImageEntitiesForTests(){
        ImageEntity firstImageEntity = new ImageEntity();
        ImageEntity secondImageEntity = new ImageEntity();
        firstImageEntity.setMain(true);
        firstImageEntity.setDirectory("C://temp/");
        firstImageEntity.setPath("C://temp/firstPhoto");
        secondImageEntity.setMain(false);
        secondImageEntity.setPath("C://temp/secondPhoto");
        secondImageEntity.setDirectory("C://temp/");
        return List.of(firstImageEntity, secondImageEntity);
    }

    public static CategoryEntity getCategoryEntityForTests(){
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("testingCategory");
        return categoryEntity;
    }

    public static LocalizationEntity getLocalizationEntityForTests(){
        LocalizationEntity localizationEntity = new LocalizationEntity();
        localizationEntity.setCity("gdansk");
        localizationEntity.setCounty("pomorskie");
        return localizationEntity;
    }

    public static AuctionEntity getAuctionEntityForTests(){
        AuctionEntity auctionEntity = new AuctionEntity();
        auctionEntity.setUserEntity(getUserEntityForTests());
        auctionEntity.setBuyNowPrice(150);
        auctionEntity.setMinPrice(50);
        auctionEntity.setTitle("test auction");
        auctionEntity.setActive(true);
        auctionEntity.setPictures(getListOfImageEntitiesForTests());
        auctionEntity.setCategory(getCategoryEntityForTests());
        auctionEntity.setLocalization(getLocalizationEntityForTests());
        auctionEntity.setDateOfStart(LocalDateTime.now());
        return auctionEntity;
    }
    public static ObservationOfAuctionEntity getObservationEntity(){
        ObservationOfAuctionEntity observationOfAuctionEntity = new ObservationOfAuctionEntity();
        observationOfAuctionEntity.setAuctionEntity(getAuctionEntityForTests());
        observationOfAuctionEntity.setUserEntity(getUserEntityForTests());
        return observationOfAuctionEntity;
    }
    public static LicytationEntity getLicytationEntity(){
        LicytationEntity licytationEntity = new LicytationEntity();
        UserEntity userEntity = getUserEntityForTests();
        userEntity.setEmailAddress("licytating@user");
        licytationEntity.setAuction(getAuctionEntityForTests());
        licytationEntity.setPrice(350);
        licytationEntity.setUser(userEntity);
        return licytationEntity;
    }


}
