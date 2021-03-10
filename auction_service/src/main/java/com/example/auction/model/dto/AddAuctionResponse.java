package com.example.auction.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddAuctionResponse {


private String title;
private String category;
private String description;
private double minPrice;
private double buyNowPrice;
private AddLocalizationRequest address;
private boolean promoted;
private List<String> images;
private String emailAddress;

}
