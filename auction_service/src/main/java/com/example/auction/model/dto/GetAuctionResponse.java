package com.example.auction.model.dto;

import com.example.auction.model.dao.ImageEntity;
import com.example.auction.model.dao.LocalizationEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class GetAuctionResponse {

    private String title;

    private String description;

    private List<ImageModel> pictures;

    private double minPrice;
    private double buyNowPrice;

    private LocalDate dateOfStart;
    private LocalDate dateOfFinish;

    private LocalizationEntity localization;
    private GetUserResponse user;

}
