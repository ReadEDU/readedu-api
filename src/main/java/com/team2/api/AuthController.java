package com.team2.api;


import com.team2.dto.user.AuthResponseDTO;
import com.team2.dto.user.LoginDTO;
import com.team2.dto.user.UserProfileDTO;
import com.team2.dto.user.UserRegistrationDTO;
import com.team2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
//No se usa el @PreAuthorize al ser un controlador publico
public class AuthController {

    private final UserService userService;

    //Registro de Lectores
    @PostMapping("/register/reader")
    public ResponseEntity<UserProfileDTO> registerReader(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserProfileDTO userProfileDTO = userService.registerReader(userRegistrationDTO);
        return new ResponseEntity<>(userProfileDTO, HttpStatus.CREATED);
    }

    //Registro de Creadores
    @PostMapping("/register/creator")
    public ResponseEntity<UserProfileDTO> registerCreator(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserProfileDTO userProfile = userService.registerCreator(userRegistrationDTO);
        return new ResponseEntity<>(userProfile, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO authResponseDTO = userService.login(loginDTO);
        return new ResponseEntity<>(authResponseDTO, HttpStatus.OK);
    }

}
