package ru.kurakin.dto.epic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kurakin.enums.TaskStatus;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EpicParametersDto {
    protected int duration;
    protected TaskStatus calculatedStatus;
    protected LocalDate epicStart;
    protected LocalDate epicEnd;
}
