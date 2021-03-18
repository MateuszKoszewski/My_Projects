package com.example.auction.repositories;

import com.example.auction.model.dao.PurchaseEntity;
import com.example.auction.model.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository <PurchaseEntity, Long> {
    List<PurchaseEntity> findAllByUserEntity(UserEntity userEntity);
}
