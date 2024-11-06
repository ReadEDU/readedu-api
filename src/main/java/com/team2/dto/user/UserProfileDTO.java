package com.team2.dto.user;

import com.team2.model.enums.ERole;
import lombok.Data;

@Data
public class UserProfileDTO {

    private Integer id;
    private String email;
    private ERole role; //Roles entre CREATOR o READER
    private String firstName;
    private String lastName;

    private String biography;
}
