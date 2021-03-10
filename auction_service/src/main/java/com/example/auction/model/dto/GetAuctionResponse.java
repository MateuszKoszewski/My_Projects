package com.example.auction.model.dto;

import com.example.auction.model.dao.ImageEntity;
import lombok.Builder;

import java.util.List;

@Builder
public class GetAuctionResponse {

    private String title;

    private String description;

    private List<ImageEntity> pictures;
}
