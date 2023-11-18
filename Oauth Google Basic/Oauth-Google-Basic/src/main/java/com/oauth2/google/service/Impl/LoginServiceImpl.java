package com.oauth2.google.service.Impl;

import com.oauth2.google.entity.Account;
import com.oauth2.google.repository.AccountRepository;
import com.oauth2.google.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements ILoginService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public String loginWithGoogle(Account account) {

        accountRepository.save(account);

        return "dang nhap thanh cong";
    }
}
