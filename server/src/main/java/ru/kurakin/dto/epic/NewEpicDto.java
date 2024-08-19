package ru.kurakin.dto.epic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewEpicDto {
    protected Integer coordinatorId;
    protected List<Integer> tasks;
    protected String title;
    protected String description;
}
