package com.example.auction.repositories;

import com.example.auction.model.dao.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritiesRepository extends JpaRepository<AuthorityEntity, Integer> {
}
