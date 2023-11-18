package com.codewithprojects.springsecurity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @GetMapping
    public ResponseEntity<String> seyHello(){
        return ResponseEntity.ok("thang giang luyen awn cut lam nhan vien");
    }
}
