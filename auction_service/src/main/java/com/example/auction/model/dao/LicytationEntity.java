package com.example.auction.model.dao;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
@Data
@Entity
@Table(name = "licytation")
public class LicytationEntity {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn
private AuctionEntity auction;

    @ManyToOne
    @JoinColumn
    private UserEntity user;

    private double price;



}
