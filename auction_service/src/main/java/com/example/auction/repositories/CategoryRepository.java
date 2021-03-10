package com.example.auction.repositories;

import com.example.auction.model.dao.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    CategoryEntity findByName (String categoryName);
}
