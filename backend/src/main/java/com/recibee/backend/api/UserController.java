package com.recibee.backend.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.recibee.backend.managers.UserManager;
import com.recibee.backend.models.UserModel;
import com.recibee.backend.utils.Auth;

@RestController
@CrossOrigin(origins = { "http://localhost:8081" })
public class UserController {

    @PostMapping("/login")
    public String login(@RequestBody UserModel requser) {
                System.out.println("login");
                System.out.println(requser.getUsername());
                System.out.println(requser.getPassword());

        UserModel user = UserManager.getInstance().getUser(requser.getUsername());
        String hashedPassword = Auth.hashString(requser.getPassword());
        if (user != null && user.getPassword().equals(hashedPassword)) {
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
        
        UserModel checkUser = UserManager.getInstance().getUser(user.getUsername());
        if (checkUser != null)
            return null;
        String hashedPassword = Auth.hashString(user.getPassword());
        
        user = new UserModel(user.getUsername(), hashedPassword, user.getName());
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
