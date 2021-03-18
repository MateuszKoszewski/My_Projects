package com.example.auction.controllers;

import com.example.auction.model.dto.AddCategoryRequest;
import com.example.auction.model.dto.AddCategoryResponse;
import com.example.auction.model.dto.GetCategoriesResponse;
import com.example.auction.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/admin/category")
    public AddCategoryResponse addCategory (@RequestBody AddCategoryRequest addCategoryRequest){
        return categoryService.addCategory(addCategoryRequest);
    }

    @GetMapping("/category")
    public List<GetCategoriesResponse> getCategories (){
        return categoryService.getCategories();
    }
}
