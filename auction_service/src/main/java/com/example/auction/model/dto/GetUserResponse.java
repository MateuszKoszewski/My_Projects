package com.example.auction.model.dto;

import com.example.auction.model.dao.AddressEntity;
import lombok.Builder;

import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;


@Data

public class GetUserResponse {

    private String emailAddress;

    private String password;

    private String name;

    private String lastName;

    private AddressEntity address;

}
