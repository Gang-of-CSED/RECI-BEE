package com.recibee.backend.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import com.recibee.backend.managers.UserManager;
import com.recibee.backend.models.UserModel;

@RestController
@CrossOrigin(origins = { "http://localhost:8081" })
public class UserController {
    
    @PostMapping("/login")
    public UserModel login(String username, String password) {
        UserModel user = UserManager.getInstance().getUser(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    @PostMapping("/register")
    public UserModel register(String username, String password, String name) {
        UserModel user = new UserModel(username, password, name);
        UserManager.getInstance().addUser(user);
        return user;
    }


    
}
