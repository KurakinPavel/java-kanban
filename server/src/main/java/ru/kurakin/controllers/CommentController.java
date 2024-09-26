package ru.kurakin.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.kurakin.dto.comment.EpicCommentDtoOut;
import ru.kurakin.dto.comment.TaskCommentDtoOut;
import ru.kurakin.dto.comment.NewCommentDto;
import ru.kurakin.services.CommentService;

@RestController
@RequestMapping(path = "/comments")
@AllArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/tasks")
    public TaskCommentDtoOut addTaskComment(@RequestHeader("X-Sharer-User-Id") Integer authorId,
                                        @RequestParam(defaultValue = "0") Integer taskId,
                                        @RequestBody NewCommentDto newCommentDto) {
        return commentService.addTaskComment(authorId, taskId, newCommentDto);
    }

    @PostMapping("/epics")
    public EpicCommentDtoOut addEpicComment(@RequestHeader("X-Sharer-User-Id") Integer authorId,
                                            @RequestParam(defaultValue = "0") Integer epicId,
                                            @RequestBody NewCommentDto newCommentDto) {
        return commentService.addEpicComment(authorId, epicId, newCommentDto);
    }
}
