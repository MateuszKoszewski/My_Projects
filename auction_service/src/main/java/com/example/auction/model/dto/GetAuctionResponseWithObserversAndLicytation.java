package com.example.auction.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class GetAuctionResponseWithObserversAndLicytation {

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
    private boolean isActive;
    private List<GetObservationOfAuctionResponse> observations;
    private List<GetLicytationsResponse> licytations;
    private boolean loggedInUserSubscribedAuction;
    private String duration;

}
