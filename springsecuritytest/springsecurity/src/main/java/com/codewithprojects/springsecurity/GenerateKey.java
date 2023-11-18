package com.codewithprojects.springsecurity;

import java.security.SecureRandom;
import java.util.Base64;

public class GenerateKey {

    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32];
        random.nextBytes(keyBytes);

        String secretKey = Base64.getEncoder().encodeToString(keyBytes);
        System.out.println("Generated SECRET_KEY: "+ secretKey);
    }

}
