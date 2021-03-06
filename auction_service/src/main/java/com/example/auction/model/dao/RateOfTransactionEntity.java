package com.example.auction.model.dao;

import javax.persistence.*;

@Entity
@Table (name = "Rate_of_transaction")
public class RateOfTransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private PurchaseEntity purchaseEntity;

    private double rateOfSeller;

    private String descriptionOfSeller;

    private double rateOfConsumer;

    private String descriptionOfConsumer;
}
