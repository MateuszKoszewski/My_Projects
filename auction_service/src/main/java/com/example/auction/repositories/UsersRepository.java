package com.example.auction.repositories;

import com.example.auction.model.dao.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmailAdress(String emailAdress);
}