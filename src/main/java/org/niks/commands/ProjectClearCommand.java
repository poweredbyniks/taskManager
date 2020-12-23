package org.niks.commands;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;

import java.io.BufferedReader;

@AllArgsConstructor
public final class ProjectClearCommand extends CommandWithUserCheck {
    IProjectService projectService;
    IUserService userService;

    @Override
    public String getName() {
        return "project-clear";
    }

    @Override
    public String getDescription() {
        return "Removes all projects";
    }

    @Override
    public void execute(@NotNull final BufferedReader reader) {
        if (super.inner()) {
            projectService.clear();
            System.out.println("Project list is plain empty");
        }
    }
}
