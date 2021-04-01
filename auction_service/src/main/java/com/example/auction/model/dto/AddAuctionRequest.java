package com.example.auction.model.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


import javax.validation.constraints.*;
import java.util.List;

@Getter
@NoArgsConstructor
public class AddAuctionRequest {

    @NotBlank
    @Size(min = 4, message = "title must has at least 4 characters")
    private String title;

    @NotNull
    private String category;

    private String description;

    @NotNull
    @Pattern(regexp = "^[0-9]{0,3}[,?.][0-9]{0,2}$")
    private double minPrice;
    @Pattern(regexp = "^[0-9]{0,3}[,?.][0-9]{0,2}$")
    private double buyNowPrice;

    private AddLocalizationRequest address;

    private boolean promoted;

    @NotNull(message = "you must add at least main photo")
    private List<String> images;

    @NotNull
    @Email
    private String emailAddress;

}
