package org.niks.service;

import org.niks.AccessRoles;
import org.niks.entity.User;
import org.niks.repository.UserRepo;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class UserService {
    private UserRepo userRepo;
    private User currentUser;
    public static final String USER_SALT = "i(el@ku38SBFLW!kKm?h";

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public void userCreate(String userName, String password) {
        User user = new User(AccessRoles.USER, randomNumber(), userName, hash(password));
        if (userRepo.save(user)) {
            System.out.println("[User " + userName + " created]");
        } else System.out.println("Something went wrong");
    }

    public User userVerify(String userName, String password) {
        if(hash(password).equals(userRepo.findOne(userName).getPasswordHash())) {
            System.out.println("Welcome " + userName);
            return userRepo.findOne(userName);
        } else {
            System.out.println("Wrong user name or password");
            return null;
        }
    }

    public void userInfo(String userName) {
        System.out.println("User ID is: " + userRepo.findOne(userName).getUserID()
                + "\nUser name is: " + userRepo.findOne(userName).getUserName());
    }

    public void userNameEdit(String newUserName, User user) {
        if (userRepo.userNameUpdate(newUserName, user)) {
            System.out.println("Your new user name is " + newUserName);
        }
    }

    public void passwordEdit(String newPassword, User user) {
        String generatedPassword = hash(newPassword);
        userRepo.passwordUpdate(generatedPassword, user);
    }

    public long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }

    public static String hash(String password) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(USER_SALT.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
}

