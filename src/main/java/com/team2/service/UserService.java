package com.team2.service;

import com.team2.dto.user.AuthResponseDTO;
import com.team2.dto.user.LoginDTO;
import com.team2.dto.user.UserProfileDTO;
import com.team2.dto.user.UserRegistrationDTO;

public interface UserService {

    UserProfileDTO registerReader(UserRegistrationDTO registrationDTO);

    UserProfileDTO registerCreator(UserRegistrationDTO registrationDTO);

    AuthResponseDTO login(LoginDTO loginDTO);

    UserProfileDTO updateUserProfile(Integer id, UserProfileDTO userProfileDTO);

    UserProfileDTO getUserProfileById(Integer id);

}
