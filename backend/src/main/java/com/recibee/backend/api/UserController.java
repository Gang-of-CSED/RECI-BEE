package com.recibee.backend.api;

import java.time.temporal.ChronoUnit;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.recibee.backend.managers.UserManager;
import com.recibee.backend.models.UserModel;
import com.recibee.backend.utils.Auth;

import io.jsonwebtoken.Jwts;
import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin(origins = { "http://localhost:8081" })
public class UserController {

    @PostMapping("/login")
    public String login(String username, String password) {
        UserModel user = UserManager.getInstance().getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            // generate jwt from username
            String jwtToken = Auth.getToken(username);

            return jwtToken;
        }
        return null;
    }

    @PostMapping("/register")
    public UserModel register(String username, String password, String name) {
        if (username == null || password == null || name == null)
            return null;
        UserModel user = new UserModel(username, password, name);
        UserManager.getInstance().addUser(user);
        return user;
    }

    @GetMapping("/info")
    public UserModel info(@RequestHeader String authorization) {
        try {
            return Auth.getUserModel(authorization);

        } catch (Exception e) {
            return null;
        }
    }

}
