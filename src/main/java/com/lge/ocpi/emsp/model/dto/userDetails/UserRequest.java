package com.lge.ocpi.emsp.model.dto.userDetails;


import com.lge.ocpi.emsp.model.entity.autentication.UserRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private Long id;

    @NotBlank(message = "The username is required.")
    @Pattern(regexp = "^[a-zA-Z0-9_]{5,15}$",message = "Enter valid format username ex: Smith_198")
    @Size(min = 5, max = 15, message = "The username must be from 3 to 20 characters.")
    private String username;

    @NotBlank(message = "The password is required.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!*()]).{8,}$", message = "Password must be 8 characters long and combination of uppercase letters, lowercase letters, numbers, special characters.")
    private String password;

    @NotEmpty(message = "The email is required.")
    @Email(message = "The email is not a valid email.")
    private String email;

    @NotBlank(message = "The name is required.")
    @Pattern(regexp = "^[a-zA-Z]{5,15}$",message = "Enter valid format username ex: Smith_198")
    @Size(min = 5, max = 15, message = "The name must be from 3 to 20 characters.")
    private String name;

    @NotBlank(message = "The Phone Number is required.")
    @Size(max = 10, message = "The Phone Number must be min 10 digits.")
    private String phoneNumber;

    private Set<UserRole> roles;


}
