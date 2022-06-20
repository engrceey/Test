package com.zurum.test.service;

import com.zurum.test.dto.request.UpdateUserDetailsRequestDto;
import com.zurum.test.dto.request.UserRegisterRequestDto;
import com.zurum.test.dto.response.GetUserResponseDto;
import com.zurum.test.dto.response.PaginatedResponse;

public interface UserService {
    String registerUser(UserRegisterRequestDto userRegisterRequestDto);
    GetUserResponseDto getUserById(String userId);
    PaginatedResponse<GetUserResponseDto> getAllUsers(int start, int limit);
    String updateUserDetails(UpdateUserDetailsRequestDto updateUserDetailsDto, String userId);
    void deleteUserById(String userId);


}
