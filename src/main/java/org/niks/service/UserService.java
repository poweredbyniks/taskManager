package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.AccessRoles;
import org.niks.entity.User;
import org.niks.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public final class UserService implements IUserService {

    private final IUserRepository userRepository;

    private User currentUser;
    public static final String USER_SALT = "i(el@ku38SBFLW!kKm?h";

    @Autowired
    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Nullable
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(@Nullable final User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean create(@NotNull final String userName, @NotNull final String password) throws IOException {
        final User user = new User(AccessRoles.USER, userID(), userName, hash(password));
        return userRepository.save(user);
    }

    @Nullable
    public User userVerify(@NotNull final String userName, @NotNull final String password) throws NoSuchElementException {
        User user = null;
        if ((userRepository.findOne(userName).isPresent())) {
            if (hash(password).equals(userRepository.findOne(userName).get().getPasswordHash())) {
                user = userRepository.findOne(userName).get();
            }
        }
        return user;
    }

    @NotNull
    public User userInfo() {
        return getCurrentUser();
    }

    public void userNameEdit(@NotNull final String newUserName) {
        userRepository.userNameUpdate(newUserName, currentUser);
    }

    public void passwordEdit(@NotNull final String newPassword) {
        final String hashPassword = hash(newPassword);
        userRepository.passwordUpdate(hashPassword, currentUser);
    }

    public final long userID() {
        final List<User> users = userRepository.findAll();
        return users.get(users.size() - 1).getUserID() + 1;
    }

    @NotNull
    public static String hash(@NotNull final String password) {
        String hashPassword = "";
        try {
            final MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(USER_SALT.getBytes(StandardCharsets.UTF_8));
            final byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            final StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            hashPassword = sb.toString();
        } catch (NoSuchAlgorithmException | NullPointerException e) {
            e.printStackTrace();
        }
        return hashPassword;
    }
}

