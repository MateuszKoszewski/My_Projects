package com.example.auction.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetNotificationsResponse {

    private String userEmail;
    private String message;
}
