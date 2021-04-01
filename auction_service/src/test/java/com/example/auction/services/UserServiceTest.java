package com.example.auction.services;

import com.example.auction.model.dao.AddressEntity;
import com.example.auction.model.dao.AuthorityEntity;
import com.example.auction.model.dao.UserEntity;
import com.example.auction.model.dto.GetUserResponse;
import com.example.auction.model.dto.RegisterAddressRequest;
import com.example.auction.model.dto.RegisterUserRequest;
import com.example.auction.model.dto.RegisterUserResponse;
import com.example.auction.model.exceptions.AppErrorMessage;
import com.example.auction.model.exceptions.AppException;
import com.example.auction.repositories.AuthoritiesRepository;
import com.example.auction.repositories.UsersRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
   private UserService userService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private AuthoritiesRepository authoritiesRepository;


    @ParameterizedTest
    @CsvSource(value = {
            "admin@admin",
            "mati@mati",
            "pawelek@pawelek"
    })
    void shouldReturnUserIfExists(String emailAddress){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmailAddress(emailAddress);
        Mockito.when(usersRepository.findByEmailAddress(emailAddress)).thenReturn(Optional.of(userEntity));
        UserEntity foundedUser = userService.getUserByEmailAddress(emailAddress);
        Mockito.verify(usersRepository).findByEmailAddress(emailAddress);
        assertEquals(emailAddress, foundedUser.getEmailAddress());
    }
    @Test
    void shouldThrowExceptionIfUserDoesntExist (){
        Mockito.when(usersRepository.findByEmailAddress("admin@admin")).thenThrow(new AppException(AppErrorMessage.USER_DOES_NOT_EXISTS, "admin@admin"));
        assertThrows(AppException.class, ()->userService.getUserByEmailAddress("admin@admin"), "User with username mati@mati not found");
    }

    @Test
    void shouldReturnGetUserResponseIfExists(){
        UserEntity userEntity = createUserEntityForTests();
        Mockito.when(usersRepository.findByEmailAddress("admin@admin")).thenReturn(Optional.of(userEntity));
        GetUserResponse foundedUser = userService.getUser("admin@admin");
        assertEquals(userEntity.getName(), foundedUser.getName());
        assertEquals(userEntity.getLastName(), foundedUser.getLastName());
        assertEquals(userEntity.getPassword(), foundedUser.getPassword());
        assertEquals(userEntity.getAddress().getCity(), foundedUser.getAddress().getCity());
        assertEquals(userEntity.getAddress().getStreet(), foundedUser.getAddress().getStreet());
        assertEquals(userEntity.getAddress().getCounty(), foundedUser.getAddress().getCounty());
        assertEquals(userEntity.getAddress().getPostCode(), foundedUser.getAddress().getPostCode());
        assertEquals(userEntity.getAddress().getNumberOfHouse(), foundedUser.getAddress().getNumberOfHouse());
    }
    @Test
    void shouldReturnAppExceptionIfUserDoesntExist(){
        Mockito.when(usersRepository.findByEmailAddress("admin@admin")).thenThrow(new AppException(AppErrorMessage.USER_DOES_NOT_EXISTS, "admin@admin"));
        assertThrows(AppException.class, ()->userService.getUser("admin@admin"), "User with username admin@admin not found");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "mati@mati, mateusz, mati123",
            "pawelek@pawelek, pawelek, pawel123",
            "patryk@patryk, patryczek, patryk123"
    })
    void shouldReturnGetUserResponse(String emailAddress, String lastName, String password){
        UserEntity userEntity = new UserEntity();
        userEntity.setEmailAddress(emailAddress);
        userEntity.setLastName(lastName);
        userEntity.setPassword(password);
        GetUserResponse mappedUser = userService.mapUserEntityToGetUserResponse(userEntity);
        assertEquals(userEntity.getEmailAddress(), mappedUser.getEmailAddress());
    }
@Test
    void shouldReturnRegisterUserResponse(){
    ModelMapper mapper = new ModelMapper();
        UserEntity userEntity = createUserEntityForTests();
        AuthorityEntity authorityEntity = new AuthorityEntity();
        authorityEntity.setAuthority("ROLE_USER");
        userEntity.setAuthorityEntity(authorityEntity);
        RegisterUserRequest registerUserRequest = mapper.map(userEntity, RegisterUserRequest.class);
        registerUserRequest.setAddress(mapper.map(userEntity.getAddress(), RegisterAddressRequest.class));
        Mockito.when(authoritiesRepository.findAuthorityEntityByAuthority("ROLE_USER")).thenReturn(Optional.of(authorityEntity));
        Mockito.when(usersRepository.findByEmailAddress(userEntity.getEmailAddress())).thenReturn(Optional.empty());
        RegisterUserResponse registerUserResponse = userService.registerUser(registerUserRequest);
        assertEquals(userEntity.getEmailAddress(), registerUserResponse.getEmailAddress());
        assertEquals("user registered", registerUserResponse.getMessage());
        Mockito.verify(usersRepository).save(userEntity);

    }

    @Test
    void shouldReturnAppExceptionIfUserAlreadyExists(){
        ModelMapper mapper = new ModelMapper();
        UserEntity userEntity = createUserEntityForTests();
        RegisterUserRequest registerUserRequest = mapper.map(userEntity, RegisterUserRequest.class);
        Mockito.when(usersRepository.findByEmailAddress(userEntity.getEmailAddress())).thenReturn(Optional.of(userEntity));
        assertThrows(AppException.class, ()-> userService.registerUser(registerUserRequest), "User with email address " + userEntity.getEmailAddress() + " already exists");
    }

private UserEntity createUserEntityForTests(){
    UserEntity userEntity = new UserEntity();
    AuthorityEntity authorityEntity = new AuthorityEntity();
    authorityEntity.setAuthority("ROLE_USER");
    AddressEntity addressEntity = new AddressEntity();
    addressEntity.setPostCode("14-500");
    addressEntity.setCity("Braniewo");
    addressEntity.setStreet("Kopernika");
    addressEntity.setCounty("warminsko-mazurskie");
    addressEntity.setNumberOfHouse(3);
    userEntity.setEmailAddress("agnieszka@agnieszka");
    userEntity.setName("admin");
    userEntity.setLastName("adminByAdmin");
    userEntity.setPassword("admin123");
    userEntity.setAddress(addressEntity);
    userEntity.setDateOfCreatingUser(LocalDate.now());
    userEntity.setAuthorityEntity(authorityEntity);
       return userEntity;
}


}