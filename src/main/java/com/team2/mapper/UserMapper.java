package com.team2.mapper;

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
}
