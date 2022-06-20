package com.zurum.test.service;

import com.zurum.test.dto.request.UpdateUserDetailsRequestDto;
import com.zurum.test.dto.request.UserRegisterRequestDto;
import com.zurum.test.dto.response.GetUserResponseDto;

public interface UserService {
    String registerUser(UserRegisterRequestDto userRegisterRequestDto);
    GetUserResponseDto getUserById(String userId);
    String updateUserDetails(UpdateUserDetailsRequestDto updateUserDetailsDto, String userId);
    void deleteUserById(String userId);

}
