package com.example.auction.services;

import com.example.auction.model.dao.CategoryEntity;
import com.example.auction.model.dto.AddCategoryRequest;
import com.example.auction.model.dto.AddCategoryResponse;
import com.example.auction.model.dto.GetAuctionResponse;
import com.example.auction.model.dto.GetCategoriesResponse;
import com.example.auction.repositories.AuctionRepository;
import com.example.auction.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuctionService {

    private final AuctionRepository auctionRepository;
    private final CategoryRepository categoryRepository;

    public List<GetAuctionResponse> getAllAuctions() {
        return auctionRepository.findAll().stream().map(auction -> GetAuctionResponse.builder()
                .title(auction.getTitle())
                .description(auction.getDescription())
                .pictures(auction.getPictures())
                .build()).collect(Collectors.toList());
    }
    public AddCategoryResponse addCategory(AddCategoryRequest addCategoryRequest){
        CategoryEntity newCategory = new CategoryEntity();
newCategory.setName(addCategoryRequest.getName());
newCategory.setDescription(addCategoryRequest.getDescription());
categoryRepository.save(newCategory);
return new AddCategoryResponse("category added");
    }

    public List<GetCategoriesResponse> getCategories(){
        return categoryRepository.findAll().stream().map(category ->
                GetCategoriesResponse.builder()
                        .name(category.getName())
                        .description(category.getDescription())
                        .build()).collect(Collectors.toList());
    }
}
