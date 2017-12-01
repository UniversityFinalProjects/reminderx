package com.example.stavalfi.app1;

import java.util.ArrayList;
import java.util.List;

public class UserManager {

    private static final UserManager ourInstance = new UserManager();

    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {
    }

    private User loggedInUser;
    private List<User> users = new ArrayList<>();

    public boolean login(String username, String password) {
        for (User user : this.users)
            if (user.username.equals(username) && user.password.equals(password)) {
                this.loggedInUser = user;
                return true;
            }
        return false;
    }

    public String getUsers()
    {
        String str="[";
        for (User user1 : this.users)
            str+="("+user1.username+","+user1.password+"),";
        str+="]";
        return str;
    }

    public User getLoggedInUser() {
        return this.loggedInUser;
    }

    public User register(User user) {
        for (User user1 : this.users)
            if (user1.username.equals(user.username)) {
                return user1;
            }
        User newUser = new User((this.users.size() + 1) + "", user.username, user.password, user.emailAddress, user.firstName, user.lastName, user.cityName, user.streetAddress, user.image);
        this.users.add(newUser);
        login(newUser.username,newUser.password);
        return newUser;
    }
}
