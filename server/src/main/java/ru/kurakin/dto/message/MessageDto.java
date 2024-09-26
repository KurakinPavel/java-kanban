package ru.kurakin.dto.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.kurakin.dto.user.ShortUserDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    protected int id;
    protected String message;
    protected List<ShortUserDto> recipients;
    protected ShortUserDto sender;
    protected LocalDate created;
}
