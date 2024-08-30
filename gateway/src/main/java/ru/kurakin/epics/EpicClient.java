package ru.kurakin.epics;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class EpicClient {
    private final WebClient webClient;

    public Mono<ResponseEntity<Object>> saveEpic(NewEpicDto newEpicDto) {
        return webClient.post()
                .uri("/epics")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newEpicDto)
                .retrieve()
                .toEntity(Object.class);
    }

    public Mono<ResponseEntity<Object>> updateEpic(Integer epicId, UpdateEpicDto updateEpicDto) {
        return webClient.post()
                .uri("/epics/{epicId}", epicId)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(updateEpicDto)
                .retrieve()
                .toEntity(Object.class);
    }

    public Mono<ResponseEntity<Object>> addTaskToEpic(Integer epicId, Integer taskId) {
        return webClient.patch()
                .uri("/epics/{epicId}/add/{taskId}", epicId, taskId)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Object.class);
    }

    public Mono<ResponseEntity<Object>> removeTaskFromEpic(Integer epicId, Integer taskId) {
        return webClient.patch()
                .uri("/epics/{epicId}/remove/{taskId}", epicId, taskId)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(Object.class);
    }
}
