package com.example.auction.model.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "licytation")
public class LicytationEntity {

    @Id
    private Integer id;


}
