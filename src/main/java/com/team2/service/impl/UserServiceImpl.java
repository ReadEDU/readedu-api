package com.team2.service.impl;

import com.team2.model.entity.User;
import com.team2.repository.UserRepository;
import com.team2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }

        // Establece fechas de creación
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }
}
