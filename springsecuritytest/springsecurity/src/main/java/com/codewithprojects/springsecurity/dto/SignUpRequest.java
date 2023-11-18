package com.codewithprojects.springsecurity.dto;

import com.codewithprojects.springsecurity.entities.Role;
import lombok.Data;

@Data
public class SignUpRequest {

    private String name;

    private String email;

    private String password;

    private Integer roleId;

}
