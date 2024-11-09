package com.team2.mapper;

import com.team2.dto.user.AuthResponseDTO;
import com.team2.dto.user.LoginDTO;
import com.team2.dto.user.UserProfileDTO;
import com.team2.dto.user.UserRegistrationDTO;
import com.team2.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public User toUserEntity(UserRegistrationDTO registrationDTO){
        return modelMapper.map(registrationDTO, User.class);
    }

    public UserProfileDTO toUserProfileDTO(User user){
           UserProfileDTO userProfileDTO = modelMapper.map(user, UserProfileDTO.class);

            if (user.getReader() != null) {
                userProfileDTO.setFirstName(user.getReader().getFirstName());
                userProfileDTO.setLastName(user.getReader().getLastName());
                userProfileDTO.setBiography(user.getReader().getBiography());
            }

            if (user.getCreator() != null) {
                userProfileDTO.setFirstName(user.getCreator().getFirstName());
                userProfileDTO.setLastName(user.getCreator().getLastName());
                userProfileDTO.setBiography(user.getCreator().getBiography());
            }

            return userProfileDTO;
    }

    public User toUserEntity(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, User.class);
    }

    public AuthResponseDTO toAuthResponseDTO(User user, String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setToken(token);

        // Obtener el nombre y apellido
        String firstName = (user.getReader() != null) ? user.getReader().getFirstName()
                : (user.getCreator() != null) ? user.getCreator().getFirstName()
                : "Admin";
        String lastName = (user.getReader() != null) ? user.getReader().getLastName()
                : (user.getCreator() != null) ? user.getCreator().getLastName()
                : "User";

        authResponseDTO.setFirstName(firstName);
        authResponseDTO.setLastName(lastName);

        authResponseDTO.setRole(user.getRole().getName().name());

        return authResponseDTO;
    }
}
