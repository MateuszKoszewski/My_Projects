package com.example.auction.model.dao;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class PictureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String path;

    @ManyToOne
    private AuctionEntity auctionEntity;
}
