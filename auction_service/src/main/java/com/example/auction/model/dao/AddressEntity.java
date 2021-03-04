package com.example.auction.model.dao;

import javax.persistence.*;

@Entity
@Table (name = "adress")
public class AdressEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer Id;

    private String county;

    private String city;

    private String adress;

}
