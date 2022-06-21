package com.zurum.test.service;

import com.github.javafaker.Faker;
import com.zurum.test.dto.constants.Responses;
import com.zurum.test.dto.request.UpdateUserDetailsRequestDto;
import com.zurum.test.dto.request.UserRegisterRequestDto;
import com.zurum.test.dto.response.GetUserResponseDto;
import com.zurum.test.entity.User;
import com.zurum.test.repository.UserRepository;
import com.zurum.test.service.implementations.UserServiceImpl;
import com.zurum.test.utils.ModelMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.context.annotation.Profile;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@Slf4j
@Profile("test")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class UserServiceTest {

    //Class to be tested
    private UserService userService;

    //Dependencies (To be mocked)
    @Mock
    private UserRepository userRepository;
    //Test Data
    private UserRegisterRequestDto userRegisterRequestDto;
    private UpdateUserDetailsRequestDto updateUserDetailsRequestDto;
    private GetUserResponseDto getUserResponseDto;
    private User user;
    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;
    private final Faker faker = new Faker();

    @BeforeEach
    void setUp() {
        updateUserDetailsRequestDto = UpdateUserDetailsRequestDto
                .builder()
                .firstName("john")
                .lastName("doe")
                .phoneNumber("+2348012345678")
                .build();

        userRegisterRequestDto = UserRegisterRequestDto
                .builder()
                .age(20)
                .dob(LocalDate.of(1992,5,4))
                .email("john.doe@gmail.com")
                .firstName("jane")
                .lastName("doe")
                .phoneNumber("+2349123456789")
                .build();

        user = User.builder()
                .age(20)
                .dob(LocalDate.of(1992,5,4))
                .email("john.doe@gmail.com")
                .firstName("jane")
                .lastName("doe")
                .createdDate(Timestamp.from(Instant.now()))
                .id("abc-123")
                .updatedDate(Timestamp.from(Instant.now()))
                .version(2L)
                .phoneNumber("+2349123456789")
                .build();

        userRepository = mock(UserRepository.class);
        userService = new UserServiceImpl(userRepository);

        when(userRepository.save(user)).thenReturn(user);
        when(userRepository.findById("abc-123")).thenReturn(Optional.of(user));


    }

    @Test
    void registerUser() {
        //When
        String response = userService.registerUser(userRegisterRequestDto);

        //Then
        verify(userRepository, Mockito.times(1)).save(ArgumentMatchers.any(User.class));
        verify(userRepository, Mockito.times(1)).save(userArgumentCaptor.capture());

        assertThat(response).isNotNull();
        assertThat(response).isEqualTo(Responses.SUCCESS.getStatus());
        assertThat(userArgumentCaptor.getValue().getAge()).isEqualTo(20);
        assertThat(userArgumentCaptor.getValue().getEmail()).isEqualTo("john.doe@gmail.com");
    }

    @Test
    void getUserById() {
        // When
        getUserResponseDto = userService.getUserById("abc-123");

        //Then
        assertThat(getUserResponseDto).isNotNull();
        assertThat(getUserResponseDto.getAge()).isEqualTo(20);
        assertThat(getUserResponseDto.getEmail()).isEqualTo("john.doe@gmail.com");
        assertThat(getUserResponseDto.getPhoneNumber()).isEqualTo("+2349123456789");
    }

    @Test
    void getAllUsers() {
        //Given
        List<User> requests = new ArrayList<>();


        for (int i = 1; i <= 20; i++) {
             userRegisterRequestDto = UserRegisterRequestDto
                    .builder()
                    .age(20)
                    .dob(LocalDate.of(1990,6,4))
                    .email(faker.internet().emailAddress())
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .phoneNumber("+2349123456789")
                    .build();

            User userz = ModelMapperUtils.map(userRegisterRequestDto, user);
            requests.add(userz);
        }

        when(userRepository.saveAll(requests)).thenReturn(requests);

//        verify(userRepository).findAll(PageRequest.of(0,10));

    }

    @Test
    void updateUserDetails() {
        // When
        userService.updateUserDetails(updateUserDetailsRequestDto, "abc-123");

        // Then
        verify(userRepository, Mockito.times(1)).save(user);
    }

    @Test
    void deleteUserById() {
        //When
        userService.deleteUserById("abc-123");

        //Then
        verify(userRepository,Mockito.times(1)).deleteById("abc-123");

    }
}