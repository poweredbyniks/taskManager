package org.niks.commands;

import org.niks.entity.User;
import org.niks.repository.ProjectRepo;
import org.niks.service.TaskService;
import org.niks.service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TaskCreateCommand extends Command {
    private TaskService taskService;

    public TaskCreateCommand(TaskService taskService, ProjectRepo projectRepo) {
        this.taskService = taskService;
        this.projectRepo = projectRepo;
    }

    private ProjectRepo projectRepo;


    @Override
    public String getName() {
        return "task-create";
    }

    @Override
    public String getDescription() {
        return "Creation of a task";
    }

    @Override
    public void execute(BufferedReader reader, UserService userService) {
        if (userService.getCurrentUser() != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
            try {
                System.out.println("[Enter project to include to]");
                String projectName = reader.readLine();
                System.out.println("[Enter task name]");
                String taskName = reader.readLine();
                System.out.println("[Enter task description]");
                String taskDescription = reader.readLine();
                System.out.println("[Enter starting date dd.MM.yyyy]");
                String startingDate = reader.readLine();
                System.out.println("[Enter finishing date dd.MM.yyyy]");
                String finishingDate = reader.readLine();
                taskService.taskCreate(projectName, taskName, taskDescription, dateFormat.parse(startingDate),
                        dateFormat.parse(finishingDate), projectRepo, userService.getCurrentUser());
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Log in before working");
        }
    }
}