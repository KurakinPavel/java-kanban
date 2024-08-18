package ru.kurakin.mappers;

import ru.kurakin.dto.FullUserDto;
import ru.kurakin.dto.NewUserDto;
import ru.kurakin.dto.ShortUserDto;
import ru.kurakin.model.User;

public class UserMapper {
    public static FullUserDto toFullUserDto(User user) {
        return new FullUserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static ShortUserDto toShortUserDto(User user) {
        return new ShortUserDto(
                user.getId(),
                user.getName()
        );
    }

    public static User toUser(NewUserDto newUserDto) {
        return new User(
                0,
                newUserDto.getName() != null ? newUserDto.getName() : "",
                newUserDto.getEmail() != null ? (newUserDto.getEmail()) : ""
        );
    }
}
