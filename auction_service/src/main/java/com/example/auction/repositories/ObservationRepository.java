package com.example.auction.repositories;

import com.example.auction.model.dao.AuctionEntity;
import com.example.auction.model.dao.ObservationOfAuctionEntity;
import com.example.auction.model.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ObservationRepository extends JpaRepository<ObservationOfAuctionEntity, Long> {

Optional<ObservationOfAuctionEntity> findByAuctionEntityAndUserEntity(AuctionEntity auction, UserEntity user);
List<ObservationOfAuctionEntity>findAllByAuctionEntity(AuctionEntity auction);
}
