package com.oauth2.google.controller;

import com.oauth2.google.entity.Account;
import com.oauth2.google.repository.AccountRepository;
import com.oauth2.google.service.Impl.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private LoginServiceImpl loginService;

    @GetMapping("/user")
    public String getUser(@AuthenticationPrincipal OAuth2User oAuth2User){

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        Account account = new Account();
        account.setEmail(email);
        account.setName(name);

        loginService.loginWithGoogle(account);

        if (account.getEmail() == null){
            return "ban khong co email vui long chon phuong thuc dang nhap khac";
        }
        oAuth2User.getAttributes();
        return "dang nhap thanh cong";
    }

}
