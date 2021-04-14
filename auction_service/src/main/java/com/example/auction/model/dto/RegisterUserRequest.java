package com.example.auction.model.dto;


import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Data
public class RegisterUserRequest {
    @Email(message = "you must put an email address")
    private String emailAddress;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$", message = "password must contain at least one digit, one lowercase character, one uppercase character and must has 6-20 characters")
    private String password;
    @NotNull
    @Size(min=2, message = "name must has at least 2 characters")
    private String name;
    @NotNull
    @Size(min=3, message = "last name must has at least 3 characters")
    private String lastName;

    private RegisterAddressRequest address;
}
