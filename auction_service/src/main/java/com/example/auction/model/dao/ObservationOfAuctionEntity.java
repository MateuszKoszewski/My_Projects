package com.example.auction.model.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Observation_of_auction")
public class ObservationOfAuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne
    private AuctionEntity auctionEntity;
}
