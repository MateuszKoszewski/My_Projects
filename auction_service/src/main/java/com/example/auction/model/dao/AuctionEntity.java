package com.example.auction.model.dao;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "auctions")
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String description;

    @OneToMany
    private List<PictureEntity> pictures;

    @ManyToOne
    @JoinColumn
    private CategoryEntity category;

    private double minPrice;

    private double buyNowPrice;

    private boolean promoted;

    @OneToOne
    private AdressEntity adressEntity;

    private LocalDate dateOfStart;

    private LocalDate dateOfFinish;

    private int amountOfVisits;

    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;


}
