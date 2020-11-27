package org.niks.commands;

import lombok.Value;
import org.jetbrains.annotations.NotNull;
import org.niks.service.ProjectService;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;

@Value
public class ProjectRemoveCommand extends Command {
    ProjectService projectService;
    UserService userService;

    @NotNull
    public ProjectRemoveCommand(ProjectService projectService, UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    @Override
    public String getName() {
        return "project-remove";
    }

    @Override
    public String getDescription() {
        return "Removes a project";
    }

    @Override
    public void execute(BufferedReader reader) {
        if (userService.getCurrentUser() != null) {
            try {
                System.out.println("Enter project name to remove");
                projectService.remove(reader.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}
