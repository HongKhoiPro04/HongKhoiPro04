package com.codewithprojects.springsecurity.service;


import com.codewithprojects.springsecurity.dto.JwtAuthenticationResponse;
import com.codewithprojects.springsecurity.dto.RefreshTokenRequest;
import com.codewithprojects.springsecurity.dto.SignUpRequest;
import com.codewithprojects.springsecurity.dto.SigninRequest;
import com.codewithprojects.springsecurity.entities.User;

public interface AuthenticationService {

    User signup(SignUpRequest signUpRequest);

    boolean addAdmin();

    JwtAuthenticationResponse signin(SigninRequest signinRequest);

    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

}
