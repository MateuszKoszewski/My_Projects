package com.example.auction.repositories;

import com.example.auction.model.dao.AuctionEntity;
import com.example.auction.model.dao.LicytationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LicytationsRepository extends JpaRepository <LicytationEntity, Long> {
List<LicytationEntity> findAllByAuction(AuctionEntity auction);
}
