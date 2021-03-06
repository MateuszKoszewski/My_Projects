package com.example.auction.controllers;

import com.example.auction.model.dto.GetAuctionResponse;
import com.example.auction.model.dto.ImageModel;
import com.example.auction.services.AuctionService;
import com.example.auction.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity.BodyBuilder;
import java.io.IOException;
import java.time.LocalDate;
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
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/image/upload")
    public BodyBuilder uploadImage(@RequestParam ("imageFile") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String uploadDir = "src/main/resources/images/filesAddedAt" + LocalDate.now();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return ResponseEntity.status(HttpStatus.OK);
    }
}
