package ru.kurakin.dto.epic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kurakin.dto.task.FullTaskDto;
import ru.kurakin.dto.user.ShortUserDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FullEpicDto {
    protected int id;
    protected ShortUserDto coordinator;
    protected List<FullTaskDto> tasks;
    protected String title;
    protected String description;
    protected String status;
    protected int duration;
    protected String startTime;
    protected String endTime;
}
