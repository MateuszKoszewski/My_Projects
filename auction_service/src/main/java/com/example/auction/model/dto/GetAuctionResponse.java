package com.example.auction.model.dto;

import com.example.auction.model.dao.ImageEntity;
import com.example.auction.model.dao.LocalizationEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class GetAuctionResponse {

    private Long id;

    private String title;

    private String description;

    private List<ImageModel> pictures;

    private double minPrice;
    private double buyNowPrice;

    private String dateOfStart;
    private String dateOfFinish;

    private GetLocalizationResponse localization;
    private GetUserResponse user;

    private boolean isAcive;

}
