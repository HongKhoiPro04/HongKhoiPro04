package com.codewithprojects.springsecurity.service.Impl;

import com.codewithprojects.springsecurity.config.UserInfoDetails;
import com.codewithprojects.springsecurity.config.UserInfoDetailsServices;
import com.codewithprojects.springsecurity.dto.JwtAuthenticationResponse;
import com.codewithprojects.springsecurity.dto.RefreshTokenRequest;
import com.codewithprojects.springsecurity.dto.SignUpRequest;
import com.codewithprojects.springsecurity.dto.SigninRequest;
import com.codewithprojects.springsecurity.entities.Role;
import com.codewithprojects.springsecurity.entities.User;
import com.codewithprojects.springsecurity.repository.RoleRepository;
import com.codewithprojects.springsecurity.repository.UserRepository;
import com.codewithprojects.springsecurity.service.AuthenticationService;
import com.codewithprojects.springsecurity.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class AuthenticationServiceIplm implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private UserInfoDetailsServices user;

    private AuthenticationManager authenticationManager;


    private JWTService jwtService;

    public AuthenticationServiceIplm(UserInfoDetailsServices user,
                                     AuthenticationManager authenticationManager,
                                     JWTService jwtService) {
        this.user = user;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    public User signup(SignUpRequest signUpRequest) {
        Optional<Role> roleId = roleRepository.findById(signUpRequest.getRoleId());
        Role role = roleId.get();
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public boolean addAdmin() {
        Optional<Role> roleId = roleRepository.findById(2);
        Role role = roleId.get();
            User user = new User();
            user.setName("hongkhoi");
            user.setEmail("khoi@gmail.com");
            user.setRole(role);
            user.setPassword(new BCryptPasswordEncoder().encode("hongkhoideptrai"));
            userRepository.save(user);

        return true;
    }

    @Override
    public JwtAuthenticationResponse signin(SigninRequest signinRequest) {
        System.out.println("1");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),
                signinRequest.getPassword()));

        var userToken = user.loadUserByUsername(signinRequest.getEmail());

        var jwt = jwtService.generateToken(userToken);
        var refreshToken = jwtService.generateRefreshToken(new HashMap<>(),userToken);

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRefreshToken(refreshToken);

        return jwtAuthenticationResponse;
    }

    @Override
    public JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        String userEmail = jwtService.extractUserName(refreshTokenRequest.getToken());
        UserDetails userToken = user.loadUserByUsername(userEmail);

        if(jwtService.isTokenValid(refreshTokenRequest.getToken(),userToken)){
            var jwt = jwtService.generateToken(userToken);

            JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();

            jwtAuthenticationResponse.setToken(jwt);
            jwtAuthenticationResponse.setRefreshToken(refreshTokenRequest.getToken());

            return jwtAuthenticationResponse;
        }


        return null;
    }


}
