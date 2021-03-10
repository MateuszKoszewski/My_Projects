package com.example.auction.model.dao;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table (name = "purchase")
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private UserEntity userEntity;

    @OneToOne
    private AuctionEntity auctionEntity;

    private double price;

    private LocalDate date;


}
