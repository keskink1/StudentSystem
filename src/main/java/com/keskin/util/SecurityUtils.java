package com.keskin.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SecurityUtils {
    public static StringBuilder hashPassword(String password, HashAlgorithm hashAlgorithm){
        StringBuilder str = new StringBuilder();

        try {
            MessageDigest messageDigest = MessageDigest.getInstance(hashAlgorithm.getHashAlg());
            byte[] hashedPassword = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));

            for (byte b : hashedPassword) {
                str.append(String.format("%02x",b));
            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return str;
    }

}
