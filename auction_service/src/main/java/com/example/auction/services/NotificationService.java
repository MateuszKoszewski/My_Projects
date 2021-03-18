package com.example.auction.services;

import com.example.auction.model.dao.AuctionEntity;
import com.example.auction.model.dao.LicytationEntity;
import com.example.auction.model.dao.NotificationEntity;
import com.example.auction.model.dao.UserEntity;
import com.example.auction.model.dto.GetNotificationsResponse;
import com.example.auction.repositories.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private String returnLocaleDateTimeNowInString() {
        String date = LocalDateTime.now().withNano(0).toString();
        String[] array = date.split("T");
        return array[0] + " " + array[1];
    }
    public NotificationEntity CreateNotificationForAddingAuctions(UserEntity userEntity, String titleOfAuction) {
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setUser(userEntity);
        notificationEntity.setMessage(returnLocaleDateTimeNowInString() + " : dodałeś aukcję o tytule " + titleOfAuction);
        return notificationEntity;
    }

    public void saveNotificationToDB(NotificationEntity notificationEntity) {
        this.notificationRepository.save(notificationEntity);
    }

    public List<NotificationEntity> getNotificationListByUser(UserEntity userEntity) {
        return notificationRepository.findAllByUser(userEntity);
    }

    public GetNotificationsResponse mapNotificationEntityToGetNotificationResponse(NotificationEntity notificationEntity) {
        return GetNotificationsResponse.builder()
                .userEmail(notificationEntity.getUser().getEmailAddress())
                .message(notificationEntity.getMessage())
                .build();
    }
    public NotificationEntity createNotificationForSubscribing (UserEntity userEntity, AuctionEntity auctionEntity){
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setUser(auctionEntity.getUserEntity());
        notificationEntity.setMessage(returnLocaleDateTimeNowInString() + " : użytkownik " + userEntity.getEmailAddress() + " zaczął obserwować Twoją aukcję o tytule " + auctionEntity.getTitle());
        return notificationEntity;

    }
    public void deleteNotificationsByUser (UserEntity userEntity){
        notificationRepository.deleteAllByUser(userEntity);
    }

    public NotificationEntity createNotificationForBeingOverlicytated (UserEntity lastLicytatingUser, AuctionEntity auctionEntity, LicytationEntity thisLicytation){
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setUser(lastLicytatingUser);
        notificationEntity.setMessage(returnLocaleDateTimeNowInString() + " : zostałeś przelicytowany na aukcji o tytule " + auctionEntity.getTitle() + " kwotą " + thisLicytation.getPrice() + " przez użytkownika " + thisLicytation.getUser().getEmailAddress());
        return notificationEntity;

    }
    public NotificationEntity createNotificationForUserForLicytatingHisAuction (AuctionEntity auctionEntity, LicytationEntity thisLicytation){
        NotificationEntity notificationEntity = new NotificationEntity();
        notificationEntity.setUser(auctionEntity.getUserEntity());
        notificationEntity.setMessage(returnLocaleDateTimeNowInString() + " : użytkownik " + thisLicytation.getUser().getEmailAddress() + " zalicytował kwotę " + thisLicytation.getPrice() + " na Twojej aukcji o tytule " + auctionEntity.getTitle());
        return notificationEntity;
    }

}
