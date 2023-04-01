package com.example.springstudentservice.Dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class StudentDto {
    @NotBlank(message = "student name must not be empty")
    private String stuName;

    private char stuGender;

    @Email(message = "Please provide a valid email-id")
    @NotBlank(message = "email-id must not be empty")
    private String emailId;
}
