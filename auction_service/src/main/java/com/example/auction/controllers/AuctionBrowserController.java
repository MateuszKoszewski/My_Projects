package com.example.auction.controllers;

import com.example.auction.model.dto.*;
import com.example.auction.services.AuctionService;
import com.example.auction.services.UserService;
import com.example.auction.utils.FileUploadUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

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


@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/log")
    public String login(){

        return "authenticated succesfully";
    }

    @PostMapping("/image/upload")
    public ResponseEntity<String> uploadImage(@RequestParam ("imageFile") MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String uploadDir = "src/main/resources/images/filesAddedAt" + LocalDate.now();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    @PostMapping("/user")
    public RegisterUserResponse registerUser (@RequestBody RegisterUserRequest registerUserRequest){
return userService.registerUser(registerUserRequest);
    }

    @PostMapping("/admin/category")
    public AddCategoryResponse addCategory (@RequestBody AddCategoryRequest addCategoryRequest){
    return auctionService.addCategory(addCategoryRequest);
    }

    @GetMapping("/category")
    public List<GetCategoriesResponse> getCategories (){
    return auctionService.getCategories();
    }

}
