package com.team2.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email  no es válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    private String password;
}