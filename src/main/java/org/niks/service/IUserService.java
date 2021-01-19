package org.niks.service;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.User;

import java.io.IOException;

public interface IUserService {

    @Nullable
    User getCurrentUser();

    void setCurrentUser(@Nullable final User currentUser);

    boolean create(@NotNull final String userName, @NotNull final String password) throws IOException;

    @Nullable
    User userVerify(@NotNull final String userName, @NotNull final String password);

    @NotNull
    User userInfo(@NotNull final String userName);

    void userNameEdit(@NotNull final String newUserName);

    void passwordEdit(@NotNull final String newPassword);

    long userID();
}
