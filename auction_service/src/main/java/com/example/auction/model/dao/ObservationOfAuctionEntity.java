package com.example.auction.model.dao;

import javax.persistence.*;

@Entity
@Table(name = "Observation of auction")
public class ObservationOfAuctionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne
    private AuctionEntity auctionEntity;
}
