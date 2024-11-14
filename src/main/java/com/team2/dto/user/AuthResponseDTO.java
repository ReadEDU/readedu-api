package com.team2.dto.user;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private Integer id;
    private String token; //Token JWT
    private String firstName; //Nombre
    private String lastName; //Apellido
    private String role; //Rol
}