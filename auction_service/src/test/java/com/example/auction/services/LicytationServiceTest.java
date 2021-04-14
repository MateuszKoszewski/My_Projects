package com.example.auction.services;

import com.example.auction.model.dao.AuctionEntity;
import com.example.auction.model.dao.LicytationEntity;
import com.example.auction.model.dao.UserEntity;
import com.example.auction.model.dto.GetLicytationsResponse;
import com.example.auction.repositories.LicytationsRepository;
import com.example.auction.testUtils.GetEntities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LicytationServiceTest {

    @Mock
    private LicytationsRepository licytationsRepository;

    @InjectMocks
    private LicytationService licytationService;

    @Test
    void shouldReturnListOfGetLicytationResponseByAuction(){
        ModelMapper mapper = new ModelMapper();
        LicytationEntity licytationEntity =GetEntities.getLicytationEntity();
        AuctionEntity auctionEntity = licytationEntity.getAuction();
        List<LicytationEntity> listOfLicytations = List.of(licytationEntity);
        Mockito.when(licytationsRepository.findAllByAuction(auctionEntity)).thenReturn(listOfLicytations);
        List <GetLicytationsResponse> resultList = listOfLicytations.stream().map(element -> mapper.map(element, GetLicytationsResponse.class)).collect(Collectors.toList());
        assertEquals(resultList, licytationService.getListOfGetLicytationsResponseByAuction(auctionEntity));
    }
    @Test
    void shouldReturnMaxValueFromLicytations(){
        LicytationEntity firstLicytationEntity = GetEntities.getLicytationEntity();
        LicytationEntity secondLicytationEntity = GetEntities.getLicytationEntity();
        secondLicytationEntity.setPrice(100);
        List<LicytationEntity> listOfLicytations = List.of(firstLicytationEntity, secondLicytationEntity);
        AuctionEntity auctionEntity = firstLicytationEntity.getAuction();
        Mockito.when(licytationsRepository.findAllByAuction(auctionEntity)).thenReturn(listOfLicytations);
        double maxValue = Math.max(firstLicytationEntity.getPrice(), secondLicytationEntity.getPrice());
        assertEquals(maxValue, licytationService.getMaxValueFromLicytations(auctionEntity));
    }

    @Test
    void shouldCreateLicytationEntity(){
        LicytationEntity licytationEntity = GetEntities.getLicytationEntity();
        UserEntity user = licytationEntity.getUser();
        AuctionEntity auction = licytationEntity.getAuction();
        double price = licytationEntity.getPrice();
        assertEquals(licytationEntity, licytationService.createLicytationEntity(auction, user, price));
    }

    @Test
    void shouldSaveLicytationToDB(){
        LicytationEntity licytationEntity = GetEntities.getLicytationEntity();
        licytationService.saveLicytationToDB(licytationEntity);
        Mockito.verify(licytationsRepository).save(licytationEntity);
    }

    @Test
    void shouldReturnLastLicytationOfAuction(){
        LicytationEntity firstLicytation = GetEntities.getLicytationEntity();
        LicytationEntity secondLicytation = GetEntities.getLicytationEntity();
        secondLicytation.setPrice(firstLicytation.getPrice()+50);
        AuctionEntity auctionEntity = firstLicytation.getAuction();
        List<LicytationEntity> listOfLicytations = List.of(firstLicytation, secondLicytation);
        Mockito.when(licytationsRepository.findAllByAuction(auctionEntity)).thenReturn(listOfLicytations);
        assertEquals(secondLicytation, licytationService.getLastLicytationOfAuction(auctionEntity));
    }

}