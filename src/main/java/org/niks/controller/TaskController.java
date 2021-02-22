package org.niks.controller;

import org.jetbrains.annotations.NotNull;
import org.niks.entity.Task;
import org.niks.service.ITaskService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/tasks-management")
public class TaskController {

    private final ITaskService taskService;

    public TaskController(ITaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(Task task) {
        taskService.create(task);
    }

    @GetMapping("/tasks")
    @NotNull
    public List<Task> list(@RequestParam(value = "order", required = false) String order) {
        return taskService.list(order);
    }

    @GetMapping("/tasks/{name}")
    @NotNull
    public Task findTask(@PathVariable @NotNull final String name) {
        return taskService.findExactMatch(name);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/tasks/{name}")
    public void remove(@PathVariable String name) {
        taskService.remove(name);
    }
}
