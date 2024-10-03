package com.team2.service;

import com.team2.dto.user.CreateUserDTO;
import com.team2.dto.user.UpdateUserDTO;
import com.team2.dto.user.UserDTO;

public interface UserService {
    // Ver detalles de perfil
    UserDTO getUser(Integer id);
    // Registrar usuario sin JWT
    UserDTO register(CreateUserDTO userDTO);
    // Editar usuario
    UserDTO updateUser(UpdateUserDTO userDTO);
}
