package com.example.auction.security;

import com.example.auction.model.dao.AuthorityEntity;
import com.example.auction.model.dao.UserEntity;
import com.example.auction.repositories.AuthoritiesRepository;
import com.example.auction.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolesProvider implements CommandLineRunner {

    private final UsersRepository usersRepository;
    private final AuthoritiesRepository authoritiesRepository;

    @Override
    public void run(String... args) throws Exception {
        AuthorityEntity adminRole = new AuthorityEntity();
        adminRole.setAuthority("ROLE_ADMIN");
        adminRole = authoritiesRepository.save(adminRole);

        AuthorityEntity userRole = new AuthorityEntity();
        userRole.setAuthority("ROLE_USER");
        userRole = authoritiesRepository.save(userRole);

        UserEntity admin = new UserEntity();
        admin.setEmailAdress("admin@admin");
        admin.setPassword("admin123");
        admin.setAuthorityEntity(adminRole);
        usersRepository.save(admin);
    }
}
