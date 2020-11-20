package org.example.commands;

import org.example.entity.User;
import org.example.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class UserPasswordUpdateCommand extends Command {
    private UserService userService;

    public UserPasswordUpdateCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "password-update";
    }

    @Override
    public String getDescription() {
        return "Current password update";
    }

    @Override
    public void execute(BufferedReader reader, User user) throws IOException {
        System.out.println("Enter new password");
        String newPassword = reader.readLine();
        userService.passwordEdit(newPassword, user);
    }
}
