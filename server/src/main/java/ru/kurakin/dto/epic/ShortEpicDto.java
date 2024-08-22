package ru.kurakin.dto.epic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kurakin.dto.task.ShortTaskDto;
import ru.kurakin.dto.user.ShortUserDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortEpicDto {
    protected int id;
    protected ShortUserDto coordinator;
    protected List<ShortTaskDto> tasks;
    protected String title;
    protected String status;
    protected String startTime;
    protected String endTime;
}
