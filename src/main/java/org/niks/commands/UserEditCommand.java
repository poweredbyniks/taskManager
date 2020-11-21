package org.niks.commands;

import org.niks.entity.User;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

public class UserEditCommand extends Command {
    private UserService userService;

    public UserEditCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "user-edit";
    }

    @Override
    public String getDescription() {
        return "Edit user name";
    }

    @Override
    public void execute(BufferedReader reader, UserService userService) throws IOException {
        if (userService != null) {
            System.out.println("Enter new userName");
            String newUserName = reader.readLine();
            userService.userNameEdit(newUserName, userService.getCurrentUser());
        } else {
            System.out.println("Log in before working");
        }
    }
}