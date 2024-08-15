package ru.kurakin.model;

import java.time.LocalDateTime;
import java.util.Set;

public class Epic {
    protected int id;
    protected Set<Task> tasks;
    protected User coordinator;
    protected String title;
    protected String description;
    protected TaskStatus status;
    protected int duration;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;
}
