package ru.kurakin.tasks;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @Positive
    protected Integer performerId;
    protected List<Integer> dependentTaskIds;
    @NotBlank
    @Size(min = 5)
    protected String title;
    @NotBlank
    @Size(min = 5)
    protected String description;
    @Pattern(regexp = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$")
    protected String startDate;
    @Positive
    protected Integer duration;
}
