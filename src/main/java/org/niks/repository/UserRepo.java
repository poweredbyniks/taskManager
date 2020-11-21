package org.niks.repository;

import org.niks.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepo {
    private Map<String, User> userMap = new HashMap<>();

    public Map<String, User> showAll() {
        return userMap;
    }

    public List<User> findAll(List<String> names) {
        List<User> userList = new ArrayList<>();
        for (String name : names) {
            if (names.contains(name))
                userList.add(userMap.get(name));
        }
        return userList;
    }

    public User findOne(String name) {
        return userMap.get(name);
    }

    public User verifyUser(String userName, String password) {
        if (userMap.containsKey(userName) &&
                password.equals(userMap.get(userName).getMd5Password())) {
            System.out.println("Welcome " + userName);
            return userMap.get(userName);

        } else System.out.println("Wrong user name or password");
        return null;
    }

    public boolean save(User user) {
        if (!userMap.containsKey(user.getUserName())) {
            userMap.put(user.getUserName(), user);
        } else {
            System.out.println("User already exists");
        }
        return true;
    }

    public boolean userNameUpdate(String newUserName, User user) {
        if (userMap.containsValue(user)) {
            userMap.remove(user.getUserName());
            userMap.put(newUserName, user);
        }
        return true;
    }

    public boolean passwordUpdate(String password, User user) {
        if (user.getMd5Password().equals(password)) {
            userMap.remove(user);
            userMap.put(user.getUserName(), user);
            return true;
        } else return false;
    }

    public void remove(String name) {
        userMap.remove(name);
    }

    public void removeAll() {
        userMap.clear();
    }
}