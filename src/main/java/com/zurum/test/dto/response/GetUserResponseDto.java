package com.zurum.test.dto.response;

import lombok.Builder;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Builder
public class GetUserResponseDto {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private LocalDate dob;
    private String phoneNumber;
}
