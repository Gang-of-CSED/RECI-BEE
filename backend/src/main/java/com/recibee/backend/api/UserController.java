package com.recibee.backend.api;

import java.time.temporal.ChronoUnit;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public String login(@RequestBody UserModel requser) {
                System.out.println("login");
                System.out.println(requser.getUsername());
                System.out.println(requser.getPassword());

        UserModel user = UserManager.getInstance().getUser(requser.getUsername());
        if (user != null && user.getPassword().equals(requser.getPassword())) {
            // generate jwt from user.getUsername()
            String jwtToken = Auth.getToken(requser.getUsername());
                    System.out.println("logged in");

            return jwtToken;
        }
        return null;
    }

    @PostMapping("/register")
    public UserModel register(@RequestBody UserModel user) {
        System.out.println("register");
        if (user.getUsername()== null || user.getPassword() == null || user.getName() == null)
            return null;
        System.out.println("registered");
        user = new UserModel(user.getUsername(), user.getPassword(), user.getName());
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
