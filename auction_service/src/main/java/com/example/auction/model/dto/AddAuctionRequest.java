package com.example.auction.model.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;

@Getter
@NoArgsConstructor
public class AddAuctionRequest {

    private String title;

    private String category;

    private String description;

    private double minPrice;

    private double buyNowPrice;

    private AddLocalizationRequest address;

    private boolean promoted;

    private List<String> images;

    private String emailAddress;
    private List<MultipartFile> imageFiles;

}
