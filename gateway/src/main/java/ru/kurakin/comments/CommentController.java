package ru.kurakin.comments;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/comments")
@AllArgsConstructor
@Slf4j
public class CommentController {
    private final CommentClient commentClient;

    @PostMapping
    public Mono<ResponseEntity<Object>> addComment(@RequestHeader("X-Sharer-User-Id") Integer authorId,
                                                   @RequestParam(defaultValue = "0") Integer epicId,
                                                   @RequestParam(defaultValue = "0") Integer taskId,
                                                   @RequestBody NewCommentDto newCommentDto) {
        return commentClient.addComment(newCommentDto, authorId, epicId, taskId);
    }
}
