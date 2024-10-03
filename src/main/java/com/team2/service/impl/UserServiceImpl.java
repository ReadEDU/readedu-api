package com.team2.service.impl;

import com.team2.dto.user.CreateUserDTO;
import com.team2.dto.user.UpdateUserDTO;
import com.team2.dto.user.UserDTO;
import com.team2.exception.ResourceNotExistsException;
import com.team2.mapper.UserMapper;
import com.team2.model.entity.User;
import com.team2.repository.AuthorRepository;
import com.team2.repository.ReaderRepository;
import com.team2.repository.UserRepository;
import com.team2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AuthorRepository authorRepository;
    private final ReaderRepository readerRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDTO getUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotExistsException("Usuario no encontrado"));
        return userMapper.toUserDTO(user);
    }

    @Transactional
    @Override
    public UserDTO register(CreateUserDTO userDTO) {
        boolean existsAsReader = readerRepository.existsByFirstNameAndLastName(userDTO.getFirstName(), userDTO.getLastName());
        boolean existsAsAuthor = authorRepository.existsByFirstNameAndLastName(userDTO.getFirstName(), userDTO.getLastName());

        if(existsAsReader || existsAsAuthor){
            throw new IllegalArgumentException("Ya existe un usuario con el mismo nombre y apellido");
        }

        User user = userMapper.toUserEntity(userDTO);

        User saveUser = userRepository.save(user);

        return userMapper.toUserDTO(saveUser);
    }

    @Transactional
    @Override
    public UserDTO updateUser(UpdateUserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId())
                .orElseThrow(() -> new ResourceNotExistsException("Usuario no encontrado"));

        boolean existsAsReader = readerRepository.existsByFirstNameAndLastNameAndUserIdNot(
                userDTO.getFirstName(), userDTO.getLastName(), userDTO.getId());
        boolean  existsAsAuthor = authorRepository.existsByFirstNameAndLastNameAndUserIdNot(
                userDTO.getFirstName(), userDTO.getLastName(), userDTO.getId());

        if(existsAsReader || existsAsAuthor){
            throw new IllegalArgumentException("Ya existe un usuario con el mismo nombre y apellido");
        }

        user.setPassword(userDTO.getPassword());

        User updateUser = userRepository.save(user);
        return userMapper.toUserDTO(updateUser);
    }
}
