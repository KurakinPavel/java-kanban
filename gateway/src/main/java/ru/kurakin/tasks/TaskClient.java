package ru.kurakin.tasks;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TaskClient {
    private final WebClient webClient;

    public Mono<ResponseEntity<Object>> saveTask(NewTaskDto newTaskDto) {
        return webClient.post()
                .uri("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newTaskDto)
                .retrieve()
                .toEntity(Object.class);
    }

    public Mono<ResponseEntity<Object>> updateTask(Integer taskId, UpdateTaskDto updateTaskDto) {
        return webClient.patch()
                .uri("/tasks/{taskId}", taskId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updateTaskDto)
                .retrieve()
                .toEntity(Object.class);
    }
}
