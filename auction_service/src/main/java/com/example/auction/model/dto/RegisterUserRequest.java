package com.example.auction.model.dto;

import com.example.auction.model.dao.AddressEntity;
import lombok.Getter;
import org.codehaus.jackson.annotate.JsonProperty;


import java.util.Map;

@Getter
public class RegisterUserRequest {
    private String emailAddress;
    private String password;
    private String name;
    private String lastName;

    private RegisterAddressRequest address;
}
