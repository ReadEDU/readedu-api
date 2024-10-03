package com.team2.mapper;

import com.team2.dto.user.CreateUserDTO;
import com.team2.dto.user.UserDTO;
import com.team2.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final ModelMapper modelMapper;

    public User toUserEntity(CreateUserDTO createUserDTO){
        return modelMapper.map(createUserDTO, User.class);
    }

    public UserDTO toUserDTO(User user){
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        if(user.getReader() != null){
            userDTO.setName(user.getReader().getFirstName());
            userDTO.setLastName(user.getReader().getLastName());
        }
        if(user.getAuthor() != null){
            userDTO.setName(user.getAuthor().getFirstName());
            userDTO.setLastName(user.getAuthor().getLastName());
        }
        return userDTO;
    }
}
