package com.example.auction.model.dao;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer Id;

    private String login;

    private String password;

    @JoinColumn
    @OneToOne
    private AdressEntity adressEntity;

    private LocalDate dateOfCreatingUser;

    private String status;

    private String type;

}
