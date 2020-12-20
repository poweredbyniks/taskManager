package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;

@AllArgsConstructor
public final class ProjectRemoveCommand extends Command {
    private final IProjectService projectService;
    private final IUserService userService;

    @Override
    public String getName() {
        return "project-remove";
    }

    @Override
    public String getDescription() {
        return "Removes a project";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        if (inner()) {
            try {
                System.out.println("Enter project name to remove");
                String projectToRemove = reader.readLine();
                projectService.remove(projectToRemove);
                System.out.println("Project " + projectToRemove + " removed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean inner() {
        if (userService.getCurrentUser() != null) {
            return true;
        } else {
            System.out.println("Log in before working");
            return false;
        }
    }
}
