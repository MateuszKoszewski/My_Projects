package com.example.auction.services;

import com.example.auction.model.dao.AddressEntity;
import com.example.auction.model.dao.AuthorityEntity;
import com.example.auction.model.dao.UserEntity;
import com.example.auction.model.dto.GetUserResponse;
import com.example.auction.model.dto.RegisterUserRequest;
import com.example.auction.model.dto.RegisterUserResponse;
import com.example.auction.model.exceptions.AppErrorMessage;
import com.example.auction.model.exceptions.AppException;
import com.example.auction.repositories.AuthoritiesRepository;
import com.example.auction.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class UserService {

    private final UsersRepository usersRepository;
    private final AuthoritiesRepository authoritiesRepository;


    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        Optional<UserEntity> foundedUser = usersRepository.findByEmailAddress(registerUserRequest.getEmailAddress());
        if (foundedUser.isPresent()) {
            throw new AppException(AppErrorMessage.USER_ALREADY_EXISTS, registerUserRequest.getEmailAddress());
        } else {
            UserEntity user = new UserEntity();
            user.setEmailAddress(registerUserRequest.getEmailAddress());
            user.setPassword(registerUserRequest.getPassword());
            user.setName(registerUserRequest.getName());
            user.setLastName(registerUserRequest.getLastName());
            user.setDateOfCreatingUser(LocalDate.now());
            AddressEntity address = new AddressEntity();
            address.setCity(registerUserRequest.getAddress().getCity());
            address.setCounty(registerUserRequest.getAddress().getCounty());
            address.setStreet(registerUserRequest.getAddress().getStreet());
            address.setNumberOfHouse(registerUserRequest.getAddress().getNumberOfHouse());
            address.setNumberOfFlat(registerUserRequest.getAddress().getNumberOfFlat());
            address.setPostCode(registerUserRequest.getAddress().getPostCode());
            user.setAddress(address);
            Optional<AuthorityEntity> authority = authoritiesRepository.findAuthorityEntityByAuthority("ROLE_USER");
            authority.ifPresent(user::setAuthorityEntity);
            usersRepository.save(user);
        }
        return RegisterUserResponse.builder().emailAddress(registerUserRequest.getEmailAddress()).message("user registered").build();
    }

    public GetUserResponse getUser(String userEmail) {
        UserEntity foundedUser = usersRepository.findByEmailAddress(userEmail).orElseThrow(() -> new AppException(AppErrorMessage.USER_DOES_NOT_EXISTS, userEmail));
        ModelMapper mapper = new ModelMapper();
        return mapper.map(foundedUser, GetUserResponse.class);
    }

    public UserEntity getUserByEmailAddress(String emailAddress) {
        return usersRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new AppException(AppErrorMessage.USER_DOES_NOT_EXISTS, emailAddress));
    }

    public GetUserResponse mapUserEntityToGetUserResponse(UserEntity userEntity) {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(userEntity, GetUserResponse.class);
    }
}
