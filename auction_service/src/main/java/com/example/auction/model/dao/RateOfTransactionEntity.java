package com.example.auction.model.dao;

import javax.persistence.*;

@Entity
@Table (name = "Rate of transaction")
public class RateOfTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private PurchaseEntity purchaseEntity;

    private double rateOfSeller;

    private String descriptionOfSeller;

    private double rateOfConsumer;

    private String descriptionOfConsumer;
}
