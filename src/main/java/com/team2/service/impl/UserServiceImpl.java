package com.team2.service.impl;

import com.team2.dto.user.UserProfileDTO;
import com.team2.dto.user.UserRegistrationDTO;
import com.team2.exception.BadRequestException;
import com.team2.exception.ResourceNotFoundException;
import com.team2.mapper.UserMapper;
import com.team2.model.entity.Creator;
import com.team2.model.entity.Reader;
import com.team2.model.entity.Role;
import com.team2.model.entity.User;
import com.team2.model.enums.ERole;
import com.team2.repository.CreatorRepository;
import com.team2.repository.ReaderRepository;
import com.team2.repository.RoleRepository;
import com.team2.repository.UserRepository;
import com.team2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ReaderRepository readerRepository;
    private final CreatorRepository creatorRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public UserProfileDTO registerReader(UserRegistrationDTO registrationDTO) {
        return registerUserWithRole(registrationDTO, ERole.READER);
    }

    @Transactional
    @Override
    public UserProfileDTO registerCreator(UserRegistrationDTO registrationDTO) {
        return registerUserWithRole(registrationDTO, ERole.CREATOR);
    }

    @Transactional
    @Override
    public UserProfileDTO updateUserProfile(Integer id, UserProfileDTO userProfileDTO) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        // Comprobamos si el nuevo perfil es igual al existente
        boolean isSameFirstName = user.getReader() != null &&
                user.getReader().getFirstName().equals(userProfileDTO.getFirstName());
        boolean isSameLastName = user.getReader() != null &&
                user.getReader().getLastName().equals(userProfileDTO.getLastName());
        boolean isSameBiography = user.getReader() != null &&
                user.getReader().getBiography().equals(userProfileDTO.getBiography());

        // Si todos los campos coinciden, lanzamos la excepción sin realizar cambios
        if (isSameFirstName && isSameLastName && isSameBiography) {
            throw new IllegalArgumentException("Ya existe un usuario con el mismo nombre, apellido y biografía");
        }

        // Solo actualizamos el nombre y apellido si se han proporcionado y son diferentes
        if ((userProfileDTO.getFirstName() != null && !userProfileDTO.getFirstName().isEmpty()) &&
                (userProfileDTO.getLastName() != null && !userProfileDTO.getLastName().isEmpty()) &&
                (!isSameFirstName || !isSameLastName)) {

            boolean existsAsReader = readerRepository.existsByFirstNameAndLastNameAndUserIdNot(
                    userProfileDTO.getFirstName(), userProfileDTO.getLastName(), id);
            boolean existsAsCreator = creatorRepository.existsByFirstNameAndLastNameAndUserIdNot(
                    userProfileDTO.getFirstName(), userProfileDTO.getLastName(), id);

            if (existsAsReader || existsAsCreator) {
                throw new IllegalArgumentException("Ya existe un usuario con el mismo nombre y apellido");
            }

            if (user.getReader() != null) {
                user.getReader().setFirstName(userProfileDTO.getFirstName());
                user.getReader().setLastName(userProfileDTO.getLastName());
            }

            if (user.getCreator() != null) {
                user.getCreator().setFirstName(userProfileDTO.getFirstName());
                user.getCreator().setLastName(userProfileDTO.getLastName());
            }
        }

        // Actualizamos la biografía si está presente y es diferente
        if (userProfileDTO.getBiography() != null && !userProfileDTO.getBiography().isEmpty() && !isSameBiography) {
            if (user.getReader() != null) {
                user.getReader().setBiography(userProfileDTO.getBiography());
                user.getReader().setUpdatedAt(LocalDateTime.now());
            }

            if (user.getCreator() != null) {
                user.getCreator().setBiography(userProfileDTO.getBiography());
                user.getCreator().setUpdatedAt(LocalDateTime.now());
            }
        }

        // Guardamos los cambios solo si se realizaron actualizaciones
        User updatedUser = userRepository.save(user);

        return userMapper.toUserProfileDTO(updatedUser);
    }


    @Transactional(readOnly = true)
    @Override
    public UserProfileDTO getUserProfileById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        return userMapper.toUserProfileDTO(user);
    }

    private UserProfileDTO registerUserWithRole(UserRegistrationDTO registrationDTO, ERole roleEnum) {

        //Comprobar si el email ya está en uso o si ya existe un usuario con el mismo nombre y apellido
        boolean existsByEmail = userRepository.existsByEmail(registrationDTO.getEmail());
        boolean existsAsReader = readerRepository.existsByFirstNameAndLastName(registrationDTO.getFirstName(), registrationDTO.getLastName());
        boolean existsAsCreator = creatorRepository.existsByFirstNameAndLastName(registrationDTO.getFirstName(), registrationDTO.getLastName());

        if (existsByEmail) {
            throw new IllegalArgumentException("El email ya esta registrado");
        }

        if (existsAsReader || existsAsCreator) {
            throw new IllegalArgumentException("Ya existe un usuario con el mismo nombre y apellido");
        }

        Role role = roleRepository.findByName(roleEnum)
                .orElseThrow(() -> new ResourceNotFoundException("No existe ese role"));

        registrationDTO.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));

        User user = userMapper.toUserEntity(registrationDTO);
        user.setRole(role);

        if (roleEnum == ERole.READER){
            Reader reader = new Reader();
            reader.setFirstName(registrationDTO.getFirstName());
            reader.setLastName(registrationDTO.getLastName());
            reader.setBiography(registrationDTO.getBiography());
            reader.setCreatedAt(LocalDateTime.now());
            reader.setUser(user);
            user.setReader(reader);
        }else if(roleEnum == ERole.CREATOR){
            Creator creator = new Creator();
            creator.setFirstName(registrationDTO.getFirstName());
            creator.setLastName(registrationDTO.getLastName());
            creator.setBiography(registrationDTO.getBiography());
            creator.setCreatedAt(LocalDateTime.now());
            creator.setUser(user);
            user.setCreator(creator);
        }

        User savedUser = userRepository.save(user);

        return userMapper.toUserProfileDTO(savedUser);
    }
}