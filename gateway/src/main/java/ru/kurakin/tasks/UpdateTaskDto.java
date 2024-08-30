package ru.kurakin.tasks;

import jakarta.validation.constraints.Size;
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
    @Size(min = 10)
    protected String title;
    @Size(min = 10)
    protected String description;
    protected String status;
    protected String startDate;
    protected Integer duration;
}
