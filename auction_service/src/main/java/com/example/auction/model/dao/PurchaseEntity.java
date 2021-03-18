package com.example.auction.model.dao;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table (name = "purchase")
@Data
public class PurchaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private UserEntity userEntity;

    @OneToOne
    private AuctionEntity auctionEntity;

    private double price;

    private LocalDateTime date;


}
