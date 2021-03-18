package com.example.auction.controllers;

import com.example.auction.model.dao.NotificationEntity;
import com.example.auction.model.dto.AddLicytationRequest;
import com.example.auction.model.dto.AddLicytationResponse;
import com.example.auction.model.dto.*;
import com.example.auction.services.AuctionService;
import com.example.auction.services.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AuctionBrowserController {

    private final AuctionService auctionService;
    private final UserService userService;


@GetMapping("/api/getAllAuctions")
    public List<GetAuctionResponse> getAllAuctions (){
    return auctionService.getAllAuctions();
}



    @GetMapping("/log")
    public String login(){
        return "authenticated succesfully";
    }

//    @PostMapping("/image/upload")
//    public ResponseEntity<String> uploadImage(@RequestParam ("imageFile") MultipartFile file) throws IOException {
//        auctionService.saveImage(file);
//        return ResponseEntity.status(HttpStatus.OK).body(null);
//    }


    @PostMapping("/user")
    public RegisterUserResponse registerUser (@RequestBody RegisterUserRequest registerUserRequest){
return userService.registerUser(registerUserRequest);
    }

    @GetMapping("/user/{userEmail}")
    public GetUserResponse getUser (@PathVariable (name="userEmail") String userEmail){
    return userService.getUser(userEmail);
    }

    @PostMapping("/auction")
    public String addAuction (@RequestParam ("imageFile") List<MultipartFile> file, @RequestParam ("auction") String auction){
    return auctionService.addAuction(file, auction);
    }

//    @PostMapping("/auction2")
//    public String addAuction (@RequestParam ("auction") String auction){
//        return auctionService.addAuction(auction);
//    }
    @GetMapping("/auctions/{userEmail}/{active}")
    public List<GetAuctionResponse> getAuctions (@PathVariable (name="userEmail") String userEmail, @PathVariable (name="active") boolean isActive){
    return auctionService.getAuctionsByUser(userEmail, isActive);
    }

    @GetMapping("/localization")
    public Map<String, HashSet<String>> getLocalizations (){
    return auctionService.getLocalizations();
    }

    @GetMapping("/auction")
    public List<GetAuctionResponseWithObserversAndLicytation> getAuctionsBySearchingTag (@RequestParam (value = "searchingTag") String searchingTag, @RequestParam(value="county") String county, @RequestParam(value="city") String city, @RequestParam(value="category") String category, @RequestParam (value= "userEmail") String userEmail){
    return auctionService.getAuctionsBySearchingTag(searchingTag, county, city, category, userEmail);
    }
//    @GetMapping("/observation")
//    public GetObservationsResponse getObservationsByAuctions (@RequestParam (value = "userEmail") String userEmail, @RequestParam (value= "auctionId") Long auctionId){
//    return auctionService.getObservationByAuction
//    }
    @PostMapping("/observe")
    public AddAuctionToObserveResponse addObservationOfAuction (@RequestBody AddAuctionToObserveRequest addAuctionToObserveRequest){
    return auctionService.addObservationOfAuction(addAuctionToObserveRequest);
    }
    @PostMapping("/licytation")
    public AddLicytationResponse addLicytation(@RequestBody AddLicytationRequest addLicytationRequest){
    return auctionService.addLicytation(addLicytationRequest);
    }
    @GetMapping("/auction/{auctionId}")
    public GetAuctionResponseWithObserversAndLicytation getAuctionById (@PathVariable (name="auctionId") Long auctionId){
    return auctionService.getAuctionById(auctionId);
    }
    @GetMapping("/notifications/{userEmail}")
    public List<GetNotificationsResponse> getNotifications (@PathVariable (name="userEmail") String userEmail){
    return auctionService.getNotifications(userEmail);
    }
    @DeleteMapping("/notifications/{userEmail}")
    public void deleteNotifications (@PathVariable (name="userEmail") String userEmail){
        auctionService.deleteNotifications(userEmail);
    }
    @PostMapping("/buyauction")
    public AddPurchaseResponse addPurchaseBuyNowPrice (@RequestBody AddPurchaseRequest addPurchaseRequest){
    return auctionService.addPurchaseBuyNowPrice(addPurchaseRequest);
    }
    @GetMapping("/auction/bought/{userEmail}")
    public List<GetPurchaseResponse> getBoughtAuctionsOfUser(@PathVariable (name="userEmail") String userEmail){
    return auctionService.getBoughtAuctionsOfUser (userEmail);
    }

}
