package com.team2.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserDTO {
    @NotEmpty(message = "Empty Data")
    @NotBlank(message = "A data with only blank spaces is not valid")
    private String firstName;
    @NotEmpty(message = "Empty Data")
    @NotBlank(message = "A data with only blank spaces is not valid")
    private String lastName;
    @NotEmpty(message = "Empty Data")
    @Email(message = "Incorrect format")
    private String email;
    @NotEmpty(message = "Empty Data")
    @NotBlank(message = "A data with only blank spaces is not valid")
    @Size(min = 6, message = "Password must be at least 6 characters long")
    private String password;
}