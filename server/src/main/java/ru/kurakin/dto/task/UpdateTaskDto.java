package ru.kurakin.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskDto {
    protected Integer performerId;
    protected List<Integer> dependentTaskIds;
    protected String title;
    protected String description;
    protected String status;
    protected String startDate;
    protected Integer duration;
}