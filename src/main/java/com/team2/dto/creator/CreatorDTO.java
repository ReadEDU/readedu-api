package com.team2.dto.creator;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatorDTO {
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    @Size(max = 50, message = "El nombre debe tener 50 caracteres o menos.")
    private String firstName;

    @NotBlank(message = "El apellido es requerido")
    @Size(max = 50, message = "El apellido debe tener 50 caracteres o menos.")
    private String lastName;

    @NotBlank(message = "La biografia es necesaria")
    @Size(max = 500, message = "La biografia debe tener 500 caracteres o menos")
    private String biography;
}
