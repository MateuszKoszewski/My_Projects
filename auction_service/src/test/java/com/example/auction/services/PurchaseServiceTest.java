package com.example.auction.services;

import com.example.auction.model.dao.PurchaseEntity;
import com.example.auction.model.dao.UserEntity;
import com.example.auction.model.dto.AddPurchaseResponse;
import com.example.auction.repositories.PurchaseRepository;
import com.example.auction.testUtils.GetEntities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PurchaseServiceTest {

    @InjectMocks
    private PurchaseService purchaseService;

    @Mock
    private PurchaseRepository purchaseRepository;

    @Test
    void shouldReturnAllPurchaseOfUser(){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmailAddress("admin@admin");
        PurchaseEntity firstPurchase = new PurchaseEntity();
        PurchaseEntity secondPurchase = new PurchaseEntity();
        firstPurchase.setUserEntity(userEntity);
        secondPurchase.setUserEntity(userEntity);

        Mockito.when(purchaseRepository.findAllByUserEntity(userEntity)).thenReturn(List.of(firstPurchase, secondPurchase));
        List<PurchaseEntity> listOfPurchase = purchaseService.getAllPurchaseByUser(userEntity);
        assertEquals(firstPurchase.getUserEntity().getEmailAddress(), listOfPurchase.get(0).getUserEntity().getEmailAddress());
        assertEquals(secondPurchase.getUserEntity().getEmailAddress(), listOfPurchase.get(1).getUserEntity().getEmailAddress());
        Mockito.verify(purchaseRepository).findAllByUserEntity(userEntity);
    }

    @Test
    void shouldSavePurchaseInDb(){
        PurchaseEntity purchaseEntity = createPurchaseEntity();
        purchaseService.savePurchaseInDB(purchaseEntity);
        Mockito.verify(purchaseRepository).save(purchaseEntity);
    }

    @Test
    void shouldMapPurchaseEntityToAddPurchaseResponse(){
        PurchaseEntity purchaseEntity = createPurchaseEntity();
        AddPurchaseResponse addPurchaseResponse = purchaseService.mapPurchaseEntityToAddPurchaseResponse(purchaseEntity);

    }

    private PurchaseEntity createPurchaseEntity (){
        PurchaseEntity purchaseEntity = new PurchaseEntity();
        UserEntity userEntity = GetEntities.getUserEntityForTests();
        purchaseEntity.setUserEntity(userEntity);
        purchaseEntity.setAuctionEntity(GetEntities.getAuctionEntityForTests());
        purchaseEntity.setPrice(90);
        purchaseEntity.setDate(LocalDateTime.now());
        return purchaseEntity;
    }
}