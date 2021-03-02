package com.example.auction.security;

import com.example.auction.model.dao.UserEntity;
import com.example.auction.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Primary
public class CustomUserDetails implements UserDetailsService {
    private final UsersRepository usersRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailAdress) throws UsernameNotFoundException {
        UserEntity userEntity = usersRepository.findByEmailAdress(emailAdress).orElseThrow(()-> new RuntimeException("cant find email"));
        return User.builder().username(emailAdress)
                .password(userEntity.getPassword())
                .authorities(userEntity.getAuthorityEntity().getAuthority())
                .build();
    }
}
