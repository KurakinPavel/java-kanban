package ru.kurakin.dto.epic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEpicDto {
    protected Integer coordinatorId;
    protected String title;
    protected String description;
}
