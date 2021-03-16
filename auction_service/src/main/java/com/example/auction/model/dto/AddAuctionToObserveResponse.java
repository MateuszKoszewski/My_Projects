package com.example.auction.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddAuctionToObserveResponse {
    private String userEmail;
    private String title;
}
