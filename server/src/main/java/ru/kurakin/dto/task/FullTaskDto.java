package ru.kurakin.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kurakin.dto.epic.ShortEpicDto;
import ru.kurakin.dto.user.ShortUserDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullTaskDto {
    protected int id;
    protected ShortEpicDto epic;
    protected ShortUserDto performer;
    protected List<ShortTaskDto> dependentTasks;
    protected String title;
    protected String description;
    protected String status;
    protected int duration;
    protected String startDate;
    protected String endDate;
}
