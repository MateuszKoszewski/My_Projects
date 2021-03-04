package com.example.auction.security;

import com.example.auction.model.dao.AuthorityEntity;
import com.example.auction.model.dao.UserEntity;
import com.example.auction.repositories.AuthoritiesRepository;
import com.example.auction.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RolesProvider implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final AuthoritiesRepository authoritiesRepository;

    @Override
    public void run(String... args) throws Exception {

        String adminAuthority = "ROLE_ADMIN";
        String userAuthority = "ROLE_USER";
        String adminEmailAddress = "admin@admin";
        String userEmailAddress = "user@user";
        String adminPassword = "admin123";
        String userPassword = "user123";


        UserEntity admin = createBasicUserWithAuthorities(adminAuthority, adminEmailAddress, adminPassword);
        usersRepository.save(admin);
        UserEntity user = createBasicUserWithAuthorities(userAuthority, userEmailAddress, userPassword);
        usersRepository.save(user);
    }

    private UserEntity createBasicUserWithAuthorities(String authority, String userEmail, String password) {
        Optional<AuthorityEntity> authorityEntity = authoritiesRepository.findAuthorityEntityByAuthority(authority);
        AuthorityEntity userRole;
        if (authorityEntity.isEmpty()) {
            userRole = new AuthorityEntity();
            userRole.setAuthority(authority);
            authoritiesRepository.save(userRole);
        } else {
            userRole = authorityEntity.get();
        }

        Optional<UserEntity> userEntity = usersRepository.findByEmailAdress(userEmail);
        UserEntity adminEntity;
        if (userEntity.isEmpty()) {
            adminEntity = new UserEntity();
            adminEntity.setEmailAdress(userEmail);
            adminEntity.setPassword(password);
            adminEntity.setAuthorityEntity(userRole);

        } else {
            adminEntity = userEntity.get();
        }
        return adminEntity;
    }
}


