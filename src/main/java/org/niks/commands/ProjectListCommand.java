package org.niks.commands;

import org.jetbrains.annotations.NotNull;
import org.niks.ProjectSort;
import org.niks.entity.Project;
import org.niks.entity.Task;
import org.niks.service.IProjectService;
import org.niks.service.IUserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;


public final class ProjectListCommand extends CommandWithUserCheck {
    private final IProjectService projectService;

    public ProjectListCommand(IUserService userService, IProjectService projectService) {
        super(userService);
        this.projectService = projectService;
    }

    @Override
    public String getName() {
        return "project-list";
    }

    @Override
    public String getDescription() {
        return "List of the existing projects";
    }

    @Override
    public void inner(@NotNull final BufferedReader reader) throws IOException {
        System.out.println("Order by:\ncreation date\nstart date\nfinish date\nstatus");
        final String outputOrder = reader.readLine();
        final String order = outputOrder.replace(" ", "_").toUpperCase();
        if (order.equals("")) {
            System.out.println("Ordered by creation date");
            final List<Project> projectList = projectService.list();
            writeList(projectList);
        } else {
            System.out.println("Ordered by " + outputOrder);
            final List<Project> projectList = projectService.list(ProjectSort.valueOf(order));
            writeList(projectList);
        }
    }

    private void writeList(List<Project> projectList) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Project project : projectList) {
            System.out.println("Project Name: " + project.getProjectName()
                    + "\nStatus: " + project.getProjectStatus()
                    + "\nDescription: " + project.getProjectDescription()
                    + "\nStart date: " + dateFormat.format(project.getStartDate())
                    + "\nFinish date: " + dateFormat.format(project.getFinishDate()));
            if (project.getTaskList().size() != 0) {
                for (Task task : project.getTaskList()) {
                    System.out.println("Tasks:"
                            + "\nTask name: " + task.getTaskName()
                            + "\nTask status: " + task.getTaskStatus()
                            + "\nTask description: " + task.getTaskDescription()
                            + "\nStart date: " + dateFormat.format(task.getStartDate())
                            + "\nFinish date: " + dateFormat.format(task.getFinishDate()));
                }
            } else {
                System.out.println("Task list is empty");
            }
        }
    }
}
