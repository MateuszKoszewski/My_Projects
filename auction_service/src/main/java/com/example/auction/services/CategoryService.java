package com.example.auction.services;

import com.example.auction.model.dao.CategoryEntity;
import com.example.auction.model.dto.AddCategoryRequest;
import com.example.auction.model.dto.AddCategoryResponse;
import com.example.auction.model.dto.GetCategoriesResponse;
import com.example.auction.model.exceptions.AppErrorMessage;
import com.example.auction.model.exceptions.AppException;
import com.example.auction.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public AddCategoryResponse addCategory(AddCategoryRequest addCategoryRequest) {
        Optional<CategoryEntity> category = categoryRepository.findByName(addCategoryRequest.getName());
        if (category.isEmpty()) {
            CategoryEntity newCategory = new CategoryEntity();
            newCategory.setName(addCategoryRequest.getName());
            categoryRepository.save(newCategory);
            return new AddCategoryResponse("category added", addCategoryRequest.getName());
        }
        throw new AppException(AppErrorMessage.CATEGORY_ALREADY_EXISTS, addCategoryRequest.getName());
    }

    public List<GetCategoriesResponse> getCategories() {
        return categoryRepository.findAll().stream().map(category ->
                GetCategoriesResponse.builder()
                        .name(category.getName())
                        .build()).collect(Collectors.toList());
    }

    public CategoryEntity getCategoryEntityFromDB(String categoryName) {
        return categoryRepository.findByName(categoryName).orElseThrow(()-> new RuntimeException("category doesn't exist"));
    }
}
