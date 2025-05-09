package com.example.ManagerService.Controllers;
import com.example.ManagerService.Models.Task;
import com.example.ManagerService.DTOS.TaskManagerDto;
import com.example.ManagerService.Services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;

@RestController
@RequestMapping("/api/manager/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @Operation(summary = "Create a new task", description = "This endpoint is used to create a new task for a manager.")
    public ResponseEntity<Task> createTask(@RequestBody TaskManagerDto dto) {
        Task task = taskService.createTask(dto);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    @Operation(summary = "Get tasks by manager", description = "This endpoint retrieves all tasks assigned to a specific manager.")
    public ResponseEntity<List<Task>> getTasksByManager(@RequestParam int managerId) {
        return ResponseEntity.ok(taskService.getTasksByManager(managerId));
    }

    @GetMapping("/filter")
    @Operation(summary = "Get tasks by manager and status", description = "This endpoint retrieves tasks for a specific manager filtered by their status.")
    public ResponseEntity<List<Task>> getTasksByManagerAndStatus(
            @RequestParam int managerId,
            @RequestParam String status) {
        return ResponseEntity.ok(taskService.getTasksByManagerAndStatus(managerId, status));
    }
}
