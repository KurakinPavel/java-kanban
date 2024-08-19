package ru.kurakin.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kurakin.repositories.TaskRepository;

@Slf4j
@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
}
