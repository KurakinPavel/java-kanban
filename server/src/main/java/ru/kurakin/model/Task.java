package ru.kurakin.model;

import java.time.LocalDateTime;
import java.util.Set;

public class Task {
    protected int id;
    protected int epicId;
    protected Set<User> employees;
    protected String title;
    protected String description;
    protected TaskStatus status;
    protected int duration;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
}
