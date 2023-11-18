package com.codewithprojects.springsecurity.config;


import com.codewithprojects.springsecurity.entities.User;
import com.codewithprojects.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInfoDetailsServices implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    private UserInfoDetails userDetails;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("2");
        Optional<User> userInfo = userRepository.findByEmail(email);
        return userInfo.map(UserInfoDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("email not found"));
    }
}
