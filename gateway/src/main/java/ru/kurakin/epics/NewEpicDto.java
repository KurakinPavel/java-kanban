package ru.kurakin.epics;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
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
public class NewEpicDto {
    @Positive
    protected Integer coordinatorId;
    protected List<Integer> taskIds;
    @NotBlank
    @Size(min = 5)
    protected String title;
    @NotBlank
    @Size(min = 5)
    protected String description;
}
