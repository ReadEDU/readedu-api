package com.team2.api;

import com.team2.dto.user.UserProfileDTO;
import com.team2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user/profile")
public class UserProfileController {
    private final UserService userService;

    //Actualizar perfil de usuario
    @PutMapping("/{id}")
    public ResponseEntity<UserProfileDTO> updateProfile(@PathVariable Integer id, @Valid @RequestBody UserProfileDTO userProfileDTO) {
        UserProfileDTO updatedProfile = userService.updateUserProfile(id, userProfileDTO);
        return new ResponseEntity<>(updatedProfile, HttpStatus.OK);
    }

    //Obtener el perfil de un usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<UserProfileDTO> getProfile(@PathVariable Integer id) {
        UserProfileDTO userProfile = userService.getUserProfileById(id);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

}
