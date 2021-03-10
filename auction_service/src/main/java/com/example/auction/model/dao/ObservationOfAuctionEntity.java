package com.example.auction.model.dao;

import javax.persistence.*;

@Entity
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
