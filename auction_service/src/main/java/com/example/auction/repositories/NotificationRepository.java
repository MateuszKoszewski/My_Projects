package com.example.auction.repositories;

import com.example.auction.model.dao.NotificationEntity;
import com.example.auction.model.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {
    List<NotificationEntity> findAllByUser(UserEntity user);
    void deleteAllByUser(UserEntity userEntity);
}
