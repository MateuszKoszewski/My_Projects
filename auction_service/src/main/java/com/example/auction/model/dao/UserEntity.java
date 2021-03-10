package com.example.auction.model.dao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;

    private String emailAddress;

    private String password;

    private String name;

    private String lastName;

    @JoinColumn
    @OneToOne(cascade = {CascadeType.ALL})
    private AddressEntity addressEntity;

    private LocalDate dateOfCreatingUser;

    private String status;

    private String type;

    @OneToOne
    private AuthorityEntity authorityEntity;

}
