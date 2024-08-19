package ru.kurakin.dto.task;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewTaskDto {
    protected Integer epicId;
    protected List<Integer> performers;
    protected String title;
    protected String description;
    protected LocalDate startDate;
    protected Integer duration;
}
