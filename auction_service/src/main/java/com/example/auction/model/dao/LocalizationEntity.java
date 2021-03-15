package com.example.auction.model.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="Localization_of_auctions")
public class LocalizationEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String county;
    private String city;


}


