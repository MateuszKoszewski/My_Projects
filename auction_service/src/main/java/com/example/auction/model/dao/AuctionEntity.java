package com.example.auction.model.dao;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "auctions")
@Data
public class AuctionEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name="auction_id")
    private List<ImageEntity> pictures;

    @ManyToOne
    @JoinColumn
    private CategoryEntity category;

    private double minPrice;

    private double buyNowPrice;

    private boolean promoted;

    @OneToOne(cascade = {CascadeType.ALL})
    private LocalizationEntity localization;

    private LocalDateTime dateOfStart;

    private LocalDateTime dateOfFinish;

    private int amountOfVisits;

    @ManyToOne
    @JoinColumn
    private UserEntity userEntity;

    private boolean isActive;


}
