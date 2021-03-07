package com.example.auction.model.dao;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;

@Data
@Entity
@Table (name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer Id;

    private String county;

    private String city;

    private String street;

    private int numberOfHouse;

    private int numberOfFlat;

    private String postCode;

}
