package com.oauth2.google.service;

import com.oauth2.google.entity.Account;

public interface ILoginService {

    String loginWithGoogle(Account account);

}
