package ru.kurakin.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kurakin.dto.comment.TaskCommentDtoOut;
import ru.kurakin.dto.comment.NewCommentDto;
import ru.kurakin.mappers.CommentMapper;
import ru.kurakin.model.Epic;
import ru.kurakin.model.Task;
import ru.kurakin.model.User;
import ru.kurakin.repositories.CommentRepository;
import ru.kurakin.repositories.EpicRepository;
import ru.kurakin.repositories.TaskRepository;
import ru.kurakin.repositories.UserRepository;

@Slf4j
@Service
@AllArgsConstructor
public class CommentService {
    private final TaskRepository taskRepository;
    private final EpicRepository epicRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public TaskCommentDtoOut addComment(int authorId, int epicId, int taskId, NewCommentDto newCommentDto) {
        if (epicId == 0 && taskId == 0) {
            throw new IllegalArgumentException("Не заданы эпик и/или задача, к которым пишется комментарий");
        }
        User author = userRepository.getReferenceById(authorId);
        author.getId();
        Epic epic = null;
        if (epicId > 0) {
            epic = epicRepository.getReferenceById(epicId);
            epic.getId();
        }
        Task task = null;
        if (taskId > 0) {
            task = taskRepository.getReferenceById(taskId);
            task.getId();
        }
        return CommentMapper.toCommentDtoOut(commentRepository.save(CommentMapper.toComment(newCommentDto, epic, task, author)));
    }
}
