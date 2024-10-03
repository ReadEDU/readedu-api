package com.team2.api;

import com.team2.dto.user.CreateUserDTO;
import com.team2.dto.user.UpdateUserDTO;
import com.team2.dto.user.UserDTO;
import com.team2.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/get-user/{id}")
    public UserDTO getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }

    @PostMapping("/register/")
    public UserDTO register(@Valid @RequestBody CreateUserDTO request){
        return userService.register(request);
    }

    @PutMapping("/update-user/")
    public UserDTO updateUser(@Valid @RequestBody UpdateUserDTO request){
        return userService.updateUser(request);
    }
}