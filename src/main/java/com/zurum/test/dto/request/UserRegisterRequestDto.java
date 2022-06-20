package com.zurum.test.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@Builder
public class UserRegisterRequestDto {

    @NotBlank(message = "first name should not be blank")
    private String firstName;

    @NotBlank(message = "last name should not be blank")
    private String lastName;

    @Email
    @NotBlank(message = "email should not be blank")
    private String email;

    @Min(value = 18, message = "must be 18 years and above")
    private int age;

    @Past(message = "Date of birth can't be current or future date")
    @NotEmpty(message = "Date of birth cannot be empty")
    @JsonAlias(value = "date_of_birth")
    private LocalDate dob;

    @NotBlank(message = "Phone number cannot be blank")
    private String phoneNumber;


}
