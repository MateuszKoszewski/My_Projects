package com.example.auction.services;

import com.example.auction.model.dao.AuctionEntity;
import com.example.auction.model.dao.LicytationEntity;
import com.example.auction.model.dao.NotificationEntity;
import com.example.auction.model.dao.UserEntity;
import com.example.auction.model.dto.GetNotificationsResponse;
import com.example.auction.repositories.NotificationRepository;
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
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    @Test
    void shouldReturnNotificationEntityForAddingAuctions (){
        NotificationEntity notificationEntity = getNotificationEntity();
        String title = notificationEntity.getMessage().split("dodałeś aukcję o tytule ")[1];
        assertEquals(notificationEntity, notificationService.CreateNotificationForAddingAuctions(notificationEntity.getUser(), title));
    }

    @Test
    void shouldSaveNotificationEntityToDB(){
NotificationEntity notificationEntity = getNotificationEntity();
notificationService.saveNotificationToDB(notificationEntity);
        Mockito.verify(notificationRepository).save(notificationEntity);

    }
    @Test
    void shouldReturnListOfNotificationEntities(){
        NotificationEntity notificationEntity = getNotificationEntity();
        Mockito.when(notificationRepository.findAllByUser(notificationEntity.getUser())).thenReturn(List.of(notificationEntity));
        assertEquals(List.of(notificationEntity), notificationService.getNotificationListByUser(notificationEntity.getUser()));
    }
    @Test
    void shouldMapNotificationEntityToGetNotificationResponse(){
        NotificationEntity notificationEntity = getNotificationEntity();
        GetNotificationsResponse getNotificationsResponse = GetNotificationsResponse.builder()
                .message(notificationEntity.getMessage())
                .userEmail(notificationEntity.getUser().getEmailAddress())
                .build();
        assertEquals(getNotificationsResponse, notificationService.mapNotificationEntityToGetNotificationResponse(notificationEntity));
    }
    @Test
    void shouldCreateNotificationForSubscribing(){
        NotificationEntity notificationEntity = new NotificationEntity();
        AuctionEntity auctionEntity = GetEntities.getAuctionEntityForTests();
        notificationEntity.setUser(auctionEntity.getUserEntity());
        UserEntity userSubscribed = notificationEntity.getUser();
        userSubscribed.setEmailAddress("another@emailAdress");
        setMessageForNotificationOfSubscribing(notificationEntity, auctionEntity, userSubscribed);
        assertEquals(notificationEntity, notificationService.createNotificationForSubscribing(userSubscribed, auctionEntity));
    }
    @Test
    void shouldDeleteNotificationsOfUser(){
        UserEntity userEntity = GetEntities.getUserEntityForTests();
        notificationService.deleteNotificationsByUser(userEntity);
        Mockito.verify(notificationRepository).deleteAllByUser(userEntity);
    }

    @Test
    void shouldCreateNotificationForBeingOverlicytated(){
        UserEntity lastLicytatingUser = new UserEntity();
        lastLicytatingUser.setEmailAddress("last@licytating");
        LicytationEntity licytationEntity = GetEntities.getLicytationEntity();
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setUser(lastLicytatingUser);
        AuctionEntity auctionEntity = GetEntities.getAuctionEntityForTests();
        setMessageFotNotificationOfBeingOverlicytated(notificationEntity, auctionEntity, licytationEntity);
        assertEquals(notificationEntity, notificationService.createNotificationForBeingOverlicytated(lastLicytatingUser, auctionEntity, licytationEntity));
    }

    @Test
    void shouldCreateNotificationForAuctionOwner(){
        NotificationEntity notificationEntity = new NotificationEntity();
        AuctionEntity auctionEntity = GetEntities.getAuctionEntityForTests();
        LicytationEntity licytationEntity = GetEntities.getLicytationEntity();
        UserEntity lastLicytatingUser = GetEntities.getUserEntityForTests();
        lastLicytatingUser.setEmailAddress("last@licytating");
        notificationEntity.setUser(auctionEntity.getUserEntity());
        setMessageForNotificationOfAuctionOwner(notificationEntity, auctionEntity, licytationEntity);
        assertEquals(notificationEntity, notificationService.createNotificationForUserForLicytatingHisAuction(auctionEntity, licytationEntity));
    }



    private void setMessageForNotificationOfSubscribing(NotificationEntity notificationEntity, AuctionEntity auctionEntity, UserEntity userEntity){
        notificationEntity.setMessage(returnLocaleDateTimeNowInString() + " : użytkownik " + userEntity.getEmailAddress() + " zaczął obserwować Twoją aukcję o tytule " + auctionEntity.getTitle());
    }
    private void setMessageFotNotificationOfBeingOverlicytated(NotificationEntity notificationEntity, AuctionEntity auctionEntity, LicytationEntity thisLicytation){
        notificationEntity.setMessage(returnLocaleDateTimeNowInString() + " : zostałeś przelicytowany na aukcji o tytule " + auctionEntity.getTitle() + " kwotą " + thisLicytation.getPrice() + " przez użytkownika " + thisLicytation.getUser().getEmailAddress());
    }
    private void setMessageForNotificationOfAuctionOwner(NotificationEntity notificationEntity, AuctionEntity auctionEntity, LicytationEntity thisLicytation){
        notificationEntity.setMessage(returnLocaleDateTimeNowInString() + " : użytkownik " + thisLicytation.getUser().getEmailAddress() + " zalicytował kwotę " + thisLicytation.getPrice() + " na Twojej aukcji o tytule " + auctionEntity.getTitle());
    }
    private NotificationEntity getNotificationEntity(){
        NotificationEntity notificationEntity = new NotificationEntity();
        AuctionEntity auctionEntity = GetEntities.getAuctionEntityForTests();
        String message = returnLocaleDateTimeNowInString() + " : dodałeś aukcję o tytule " + auctionEntity.getTitle();
        notificationEntity.setUser(auctionEntity.getUserEntity());
        notificationEntity.setMessage(message);
        return notificationEntity;
    }



    private String returnLocaleDateTimeNowInString() {
        String date = LocalDateTime.now().withNano(0).toString();
        String[] array = date.split("T");
        return array[0] + " " + array[1];
    }
}