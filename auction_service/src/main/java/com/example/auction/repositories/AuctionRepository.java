package com.example.auction.repositories;

import com.example.auction.model.dao.AuctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<AuctionEntity, Integer> {
}
