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
}
