package com.zurum.test.service.implementations;

import com.zurum.test.dto.request.UpdateUserDetailsRequestDto;
import com.zurum.test.dto.request.UserRegisterRequestDto;
import com.zurum.test.dto.response.GetUserResponseDto;
import com.zurum.test.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public String registerUser(UserRegisterRequestDto userRegisterRequestDto) {
        return null;
    }

    @Override
    public GetUserResponseDto getUserById(String userId) {
        return null;
    }

    @Override
    public String updateUserDetails(UpdateUserDetailsRequestDto updateUserDetailsDto, String userId) {
        return null;
    }

    @Override
    public void deleteUserById(String userId) {

    }
}
