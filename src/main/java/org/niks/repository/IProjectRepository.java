package org.niks.repository;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.niks.entity.Project;

import java.util.List;
import java.util.Optional;

public interface IProjectRepository <T> {
    @NotNull
     List <T> findAll();

    @NotNull
    Optional <T> findOne(@NotNull String name);

    boolean save(@NotNull T entity);

    boolean update(@NotNull T entity);

    void remove(@NotNull String name);

    void removeAll();
}