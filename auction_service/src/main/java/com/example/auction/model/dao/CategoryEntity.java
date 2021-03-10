package com.example.auction.model.dao;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
}
