package com.example.auction.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
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
    private LocalDate dateOfStart;
    private LocalDate dateOfFinish;
    private GetLocalizationResponse localization;
    private GetUserResponse user;
    private boolean isAcive;
    private List<AddObservationOfAuctionResponse> observations;
    private List<GetLicytationsResponse> licytations;
    private boolean loggedInUserSubscribedAuction;

}
