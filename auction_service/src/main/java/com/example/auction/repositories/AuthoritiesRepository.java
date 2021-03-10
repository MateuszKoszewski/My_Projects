package com.example.auction.repositories;

import com.example.auction.model.dao.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthoritiesRepository extends JpaRepository<AuthorityEntity, Long> {

    Optional<AuthorityEntity> findAuthorityEntityByAuthority(String authority);
}
