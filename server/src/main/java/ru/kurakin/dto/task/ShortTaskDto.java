package ru.kurakin.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kurakin.dto.user.ShortUserDto;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShortTaskDto {
    protected int id;
    protected ShortUserDto performer;
    protected String title;
    protected String status;
    protected String startDate;
    protected String endDate;
}
