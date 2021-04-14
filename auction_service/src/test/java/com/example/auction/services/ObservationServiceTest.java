package com.example.auction.services;

import com.example.auction.model.dao.AuctionEntity;
import com.example.auction.model.dao.ObservationOfAuctionEntity;
import com.example.auction.model.dao.UserEntity;
import com.example.auction.repositories.ObservationRepository;
import com.example.auction.testUtils.GetEntities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.constraints.AssertFalse;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ObservationServiceTest {

    @Mock
    private ObservationRepository observationRepository;

    @InjectMocks
    private ObservationService observationService;


    @Test
    void shouldReturnBooleanIfUserObserveThatAuctionOrNot(){
        ObservationOfAuctionEntity observationOfAuctionEntity = GetEntities.getObservationEntity();
        UserEntity userThatObserveAuction = observationOfAuctionEntity.getUserEntity();
        AuctionEntity observedAuctionByUser = observationOfAuctionEntity.getAuctionEntity();
        Mockito.when(observationRepository.findAllByAuctionEntity(observedAuctionByUser)).thenReturn(List.of(observationOfAuctionEntity));
        assertTrue(observationService.checkIfUserObserveThatAuction(userThatObserveAuction.getEmailAddress(), observedAuctionByUser));
        UserEntity userThatNotObserveAuction = GetEntities.getUserEntityForTests();
        userThatNotObserveAuction.setEmailAddress("mateusz@mateusz");
        assertFalse(observationService.checkIfUserObserveThatAuction(userThatNotObserveAuction.getEmailAddress(), observedAuctionByUser));
    }
    @Test
    void shouldReturnTrueIfObservationOfAuctionDoesntExist(){
        ObservationOfAuctionEntity observationOfAuctionEntity = GetEntities.getObservationEntity();
        UserEntity userThatObserveAuction = observationOfAuctionEntity.getUserEntity();
        AuctionEntity auctionObservedByUser = observationOfAuctionEntity.getAuctionEntity();
        Mockito.when(observationRepository.findByAuctionEntityAndUserEntity(auctionObservedByUser, userThatObserveAuction)).thenReturn(Optional.of(observationOfAuctionEntity));
        assertFalse(observationService.checkIfObservationByAuctionAndUserDoesntExist(auctionObservedByUser, userThatObserveAuction));
        Mockito.when(observationRepository.findByAuctionEntityAndUserEntity(auctionObservedByUser, userThatObserveAuction)).thenReturn(Optional.empty());
        assertTrue(observationService.checkIfObservationByAuctionAndUserDoesntExist(auctionObservedByUser, userThatObserveAuction));
    }
@Test
    void shouldCreateObservationEntity(){
        ObservationOfAuctionEntity observationOfAuctionEntity = GetEntities.getObservationEntity();
        assertEquals(observationOfAuctionEntity, observationService.createObservationEtity(observationOfAuctionEntity.getAuctionEntity(), observationOfAuctionEntity.getUserEntity()));
}
@Test
    void shouldSaveObservationEntityToDB(){
        ObservationOfAuctionEntity observationOfAuctionEntity = GetEntities.getObservationEntity();
        observationService.saveObservationEntityToDB(observationOfAuctionEntity);
        Mockito.verify(observationRepository).save(observationOfAuctionEntity);
}
}