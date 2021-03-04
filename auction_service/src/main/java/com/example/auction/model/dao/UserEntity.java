package com.example.auction.model.dao;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer Id;

    private String emailAdress;

    private String password;

    private String name;

    private String lastName;

    @JoinColumn
    @OneToOne
    private AddressEntity addressEntity;

    private LocalDate dateOfCreatingUser;

    private String status;

    private String type;

    @OneToOne
    private AuthorityEntity authorityEntity;

}
