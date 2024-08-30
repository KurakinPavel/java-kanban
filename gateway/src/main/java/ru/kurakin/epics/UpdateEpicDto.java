package ru.kurakin.epics;

import jakarta.validation.constraints.Size;
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
    @Size(min = 10)
    protected String title;
    @Size(min = 10)
    protected String description;
}
