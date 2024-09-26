package ru.kurakin.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kurakin.dto.epic.SuperShortEpicDto;
import ru.kurakin.dto.user.ShortUserDto;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EpicCommentDtoOut {
    protected int id;
    protected String comment;
    protected SuperShortEpicDto epic;
    protected ShortUserDto author;
    protected LocalDate created;
}
