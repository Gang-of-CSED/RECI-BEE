package com.recibee.backend.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javax.crypto.spec.SecretKeySpec;

import com.recibee.backend.managers.UserManager;
import com.recibee.backend.models.UserModel;

import java.security.Key;
import java.util.Base64;

public class Auth {

    static String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

    static Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
            SignatureAlgorithm.HS256.getJcaName());

    public static String getToken(String username) {
        return Jwts.builder()
                .setId(username)
                .setSubject("account")
                .signWith(hmacKey)
                .compact();
    }

    public static String getUser(String jwtString) {

        Jws<Claims> jwt = Jwts.parserBuilder()
                .setSigningKey(hmacKey)
                .build()
                .parseClaimsJws(jwtString);

        return jwt.getBody().getId();
    }

    public static UserModel getUserModel(String jwtString) {


        String username = getUser(jwtString);
        return UserManager.getInstance().getUser(username);
    }
}
