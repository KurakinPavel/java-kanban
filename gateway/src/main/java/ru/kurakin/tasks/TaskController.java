package ru.kurakin.tasks;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/tasks")
@AllArgsConstructor
@Slf4j
public class TaskController {
    private final TaskClient taskClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Object>> saveUser(@RequestBody @Valid NewTaskDto newTaskDto) {
        log.info("Контроллер получил запрос на добавление новой задачи");
        return taskClient.saveTask(newTaskDto);
    }

    @PatchMapping("/{taskId}")
    public Mono<ResponseEntity<Object>> updateTask(@PathVariable Integer taskId,
                                                   @RequestBody UpdateTaskDto updateTaskDto) {
        log.info("Контроллер получил запрос на обновление задачи с id={}", taskId);
        return taskClient.updateTask(taskId, updateTaskDto);
    }
}
