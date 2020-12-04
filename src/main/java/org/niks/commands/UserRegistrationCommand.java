package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public final class UserRegistrationCommand extends Command {
    private final UserService userService;

    @Override
    public String getName() {
        return "user-reg";
    }

    @Override
    public String getDescription() {
        return "Registration of a new user";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) throws IOException {
        System.out.println("Enter user name");
        final String userName = reader.readLine();
        System.out.println("Enter password");
        final String password = reader.readLine();
        userService.create(userName, password);
    }
}
