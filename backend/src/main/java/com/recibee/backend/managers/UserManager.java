package com.recibee.backend.managers;
import java.util.HashMap;
import java.util.Map;
import com.recibee.backend.models.UserModel;
import com.recibee.backend.utils.FileManager;
public class UserManager {
    private static UserManager instance = null;
    private Map<String, UserModel> users = new HashMap<String, UserModel>();

    private UserManager() {
    }

    public static synchronized UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
            instance.users = FileManager.readObjectFromFile("users.dat");

            if(instance.users==null){
                System.out.println("users.dat not found, creating new file");
                instance.users = new HashMap<String, UserModel>();
                FileManager.saveObjectToFile(instance.users, "users.dat");
            }
        }
        return instance;
    }

    
    public UserModel getUser(String username) {
        return users.get(username);
    }

    public void addUser(UserModel user) {
        users.put(user.getUsername(), user);
        FileManager.saveObjectToFile(users, "users.dat");
    }

    public void removeUser(String username) {
        users.remove(username);
        FileManager.saveObjectToFile(users, "users.dat");
    }

    public Map<String, UserModel> getUsers() {
        return users;
    }




}
