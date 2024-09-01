package ru.kurakin.comments;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CommentClient {
    private final WebClient webClient;

    public Mono<ResponseEntity<Object>> addComment(NewCommentDto newCommentDto, Integer authorId, Integer epicId, Integer taskId) {
        return webClient.post()
                .uri(uriBuilder -> uriBuilder
                        .path("/comments")
                        .queryParam("epicId", epicId)
                        .queryParam("taskId", taskId)
                        .build())
                .header("X-Sharer-User-Id", String.valueOf(authorId))
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(newCommentDto)
                .retrieve()
                .toEntity(Object.class);
    }
}
