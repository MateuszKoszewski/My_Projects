package com.example.auction.services;

import com.example.auction.model.dao.CategoryEntity;
import com.example.auction.model.dto.AddCategoryRequest;
import com.example.auction.model.dto.AddCategoryResponse;
import com.example.auction.model.dto.GetCategoriesResponse;
import com.example.auction.model.exceptions.AppException;
import com.example.auction.repositories.CategoryRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;


    @InjectMocks
    private CategoryService categoryService;

    @ParameterizedTest
    @CsvSource(value = {
            "motoryzacja, category added",
            "nastepna, category added",
            "category, category added"
    })
    void shouldAddCategory(String categoryName, String message) {
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest(categoryName);
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName(addCategoryRequest.getName());

        AddCategoryResponse addCategoryResponse = categoryService.addCategory(addCategoryRequest);
        Mockito.verify(categoryRepository).save(categoryEntity);
        assertEquals(message, addCategoryResponse.getMessage());
        assertEquals(categoryName, addCategoryResponse.getName());
    }

    @Test
    void shouldntAddCategoryIfExists() {
        AddCategoryRequest addCategoryRequest = new AddCategoryRequest("motoryzacja");
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("motoryzacja");
        Mockito.when(categoryRepository.findByName("motoryzacja")).thenReturn(Optional.of(categoryEntity));
        assertThrows(AppException.class, () -> categoryService.addCategory(addCategoryRequest), "category motoryzacja already exists");

    }

    @Test
    void shouldReturnAllCategoriesIfExist(){
        CategoryEntity fistCategory = new CategoryEntity();
        CategoryEntity secondCategory = new CategoryEntity();
        CategoryEntity thirdCategory = new CategoryEntity();
        fistCategory.setName("moda");
        secondCategory.setName("elektronika");
        thirdCategory.setName("ogrod");
        Mockito.when(categoryRepository.findAll()).thenReturn(List.of(fistCategory, secondCategory, thirdCategory));
        List<GetCategoriesResponse> listOfCategories = categoryService.getCategories();
        assertEquals("moda", listOfCategories.get(0).getName());
        assertEquals(3, listOfCategories.size());

    }

}