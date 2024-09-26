package ru.kurakin.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kurakin.dto.comment.EpicCommentDtoOut;
import ru.kurakin.dto.comment.TaskCommentDtoOut;
import ru.kurakin.dto.comment.NewCommentDto;
import ru.kurakin.mappers.CommentMapper;
import ru.kurakin.model.Epic;
import ru.kurakin.model.Task;
import ru.kurakin.model.User;
import ru.kurakin.repositories.EpicCommentRepository;
import ru.kurakin.repositories.TaskCommentRepository;
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
    private final TaskCommentRepository taskCommentRepository;
    private final EpicCommentRepository epicCommentRepository;

    @Transactional
    public TaskCommentDtoOut addTaskComment(int authorId, int taskId, NewCommentDto newCommentDto) {
        if (taskId <= 0) {
            throw new IllegalArgumentException("Не указана корректный id задачи, к которой пишется комментарий");
        }
        User author = userRepository.getReferenceById(authorId);
        author.getId();
        Task task = taskRepository.getReferenceById(taskId);
        task.getId();
        return CommentMapper.toTaskCommentDtoOut(taskCommentRepository.save(CommentMapper.toTaskComment(newCommentDto, task, author)));
    }

    @Transactional
    public EpicCommentDtoOut addEpicComment(int authorId, int epicId, NewCommentDto newCommentDto) {
        if (epicId <= 0) {
            throw new IllegalArgumentException("Не задан корректный id эпика, к которому пишется комментарий");
        }
        User author = userRepository.getReferenceById(authorId);
        author.getId();
        Epic epic = epicRepository.getReferenceById(epicId);
        epic.getId();
        return CommentMapper.toEpicCommentDtoOut(epicCommentRepository.save(CommentMapper.toEpicComment(newCommentDto, epic, author)));
    }
}
