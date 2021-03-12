package com.example.auction.repositories;

import com.example.auction.model.dao.AuctionEntity;
import com.example.auction.model.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuctionRepository extends JpaRepository<AuctionEntity, Long> {

    List<AuctionEntity> findByUserEntity (UserEntity user);
}
