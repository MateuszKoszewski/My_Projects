package com.example.auction.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ImageModel {

    private String path;

    private String directory;

    private boolean main;

    private String base64Image;
}
