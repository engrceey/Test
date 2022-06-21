package com.zurum.test.controller;

import com.github.javafaker.Faker;
import com.zurum.test.dto.request.UpdateUserDetailsRequestDto;
import com.zurum.test.dto.request.UserRegisterRequestDto;
import com.zurum.test.service.UserService;
import com.zurum.test.utils.AppUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Profile("test")
@WebMvcTest(UserController.class)
class UserControllerTest {

    private final Faker faker = new Faker();

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;
    private UserRegisterRequestDto userRegisterRequestDto;
    private UpdateUserDetailsRequestDto updateUserDetailsRequestDto;
    @BeforeEach
    void setUp() {
        userRegisterRequestDto = UserRegisterRequestDto
                .builder()
                .build();

        updateUserDetailsRequestDto = UpdateUserDetailsRequestDto
                .builder()
                .build();
    }

    @Test
    void registerUser() throws Exception {
        userRegisterRequestDto = UserRegisterRequestDto
                .builder()
                .age(20)
//                .dob(LocalDate.parse("04/05/1992", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .email("john.doe@gmail.com")
                .firstName("jane")
                .lastName("doe")
                .phoneNumber("+2349123456789")
                .build();


        mockMvc.perform(post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(AppUtil.toJson(userRegisterRequestDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusMessage").value("success"))
                .andExpect(jsonPath("$.isSuccessful").value(true));
    }

    @Test
    void updateUser() throws Exception {
        updateUserDetailsRequestDto = UpdateUserDetailsRequestDto
                .builder()
                .firstName("john")
                .lastName("doe")
                .phoneNumber("+2348012345678")
                .build();

        mockMvc.perform(put("/user/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(AppUtil.toJson(updateUserDetailsRequestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusMessage").value("user updated successfully"))
                .andExpect(jsonPath("$.isSuccessful").value(true));
    }

    @Test
    void deleteUser() throws Exception {
        mockMvc.perform(delete("/user/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.statusMessage").value("user deleted successfully"))
                .andExpect(jsonPath("$.data").value(true))
                .andExpect(jsonPath("$.isSuccessful").value(true));

    }

    @Test
    void getUserById() throws Exception {

        mockMvc.perform(get("/user/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusMessage").value("successfully"))
                .andExpect(jsonPath("$.isSuccessful").value(true));
    }

    @Test
    void getExpanses() throws Exception {
        mockMvc.perform(get("/user/users")
                        .queryParam("start", "0")
                        .queryParam("limit", "10")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusMessage").value("success"))
                .andExpect(jsonPath("$.isSuccessful").value(true));
    }
}