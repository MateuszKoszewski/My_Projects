package com.example.auction.repositories;

import com.example.auction.model.dao.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<ImageEntity, Long> {
ImageEntity findByPath (String path);

}
