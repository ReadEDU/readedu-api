package com.team2.dto.author;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthorDTO {
    private Integer id;

    @NotBlank(message = "The name is required")
    @Size(max = 50, message = "Name must be 50 characters or less")
    private String firstName;

    @NotBlank(message = "The lastname is required")
    @Size(max = 50, message = "Lastname must be 50 characters or less")
    private String lastName;

    @NotBlank(message = "Link required")
    private String profile;
}
