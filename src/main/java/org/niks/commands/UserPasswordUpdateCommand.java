package org.niks.commands;

import org.niks.entity.User;
import org.niks.service.UserService;

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
    public void execute(BufferedReader reader, UserService userService) throws IOException {
        if (userService != null) {
            System.out.println("Enter new password");
            String newPassword = reader.readLine();
            userService.passwordEdit(newPassword, userService.getCurrentUser());
        } else {
            System.out.println("Log in before working");
        }
    }
}