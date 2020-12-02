package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.AccessRoles;
import org.niks.entity.User;
import org.niks.repository.UserRepository;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.NoSuchElementException;

public interface UserService {

    User currentUser = null;
    public static final String USER_SALT = "i(el@ku38SBFLW!kKm?h";

    @Nullable
    public static User getCurrentUser() {
        return currentUser;
    }

    public static User setCurrentUser(User currentUser) {
        User currentUser1 = currentUser;
        return currentUser1;
    }

    public static void create(String userName, String password) {
        if (currentUser != null) {
            System.out.println(currentUser.getUserName() + " logged out");
            setCurrentUser(null);
        }
        if (!userName.equals("")) {
            User user = new User(AccessRoles.USER, randomNumber(), userName, hash(password));
            if (UserRepository.save(user)) {
                System.out.println("[User " + userName + " created]");
            } else {
                System.out.println("Something went wrong");
            }
        } else {
            System.out.println("Enter valid user name and try again");
        }
    }

    @Nullable
    public static User userVerify(String userName, String password) {
        if (currentUser != null) {
            System.out.println(currentUser.getUserName() + " logged out");
            setCurrentUser(null);
        }
        try {
            if (hash(password).equals(UserRepository.findOne(userName).get().getPasswordHash())) {
                System.out.println("Welcome " + userName);
                return UserRepository.findOne(userName).get();
            } else {
                System.out.println("Wrong user name or password");
                return null;
            }
        } catch (NoSuchElementException e) {
            System.out.println("User not found");
        }
        return null;
    }

    public static void userInfo(String userName) {
        System.out.println("User ID is: " + UserRepository.findOne(userName).get().getUserID()
                + "\nUser name is: " + UserRepository.findOne(userName).get().getUserName());
    }

    public static void userNameEdit(String newUserName) {
        if(!newUserName.equals("")) {
            if (UserRepository.userNameUpdate(newUserName, currentUser)) {
                System.out.println("Your new user name is " + newUserName);
            }
        } else {
            System.out.println("Enter valid user name and try again");
        }
    }

    public static void passwordEdit(String newPassword) {
        if(!newPassword.equals("")){
        String hashPassword = hash(newPassword);
        if (UserRepository.passwordUpdate(hashPassword, currentUser)) {
            System.out.println("Password updated");
        }
        } else {
            System.out.println("Enter valid password and try again");
        }
    }

    public static long randomNumber() {
        SecureRandom random = new SecureRandom();
        return random.nextInt();
    }

    @NotNull
    public static String hash(String password) {
        String hashPassword = "";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(USER_SALT.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashPassword = sb.toString();
        } catch (NoSuchAlgorithmException | NullPointerException e) {
            e.printStackTrace();
        }
        return hashPassword;
    }
}

