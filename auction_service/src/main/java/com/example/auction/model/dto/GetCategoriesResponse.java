package com.example.auction.model.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class GetCategoriesResponse {

    private String name;

    private String description;
}
