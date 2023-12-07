package com.recibee.backend.managers;
import java.util.HashMap;
import java.util.Map;
import com.recibee.backend.models.UserModel;
public class UserManager {
    private static UserManager instance = null;
    private Map<String, UserModel> users = new HashMap<String, UserModel>();

    private UserManager() {
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    
    public UserModel getUser(String username) {
        return users.get(username);
    }

    public void addUser(UserModel user) {
        users.put(user.getUsername(), user);
    }

    public void removeUser(String username) {
        users.remove(username);
    }

    public void updateUser(UserModel user) {
        users.put(user.getUsername(), user);
    }

    public Map<String, UserModel> getUsers() {
        return users;
    }

    


}
