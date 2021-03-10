package com.example.auction.model.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "pictures")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String path;

    private boolean main;

}
