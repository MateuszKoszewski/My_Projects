package com.example.auction.controllers;

import com.example.auction.model.dto.GetAuctionResponse;
import com.example.auction.services.AuctionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuctionBrowserController {

    private final AuctionService auctionService;

    @CrossOrigin(origins = "http://localhost:4200")
@GetMapping("/api/getAllAuctions")
    public List<GetAuctionResponse> getAllAuctions (){
    return auctionService.getAllAuctions();
}


@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/log")
    public String login(){

        return "authenticated succesfully";
    }


}
