package ru.kurakin.mappers;

import ru.kurakin.dto.UserDto;
import ru.kurakin.model.User;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }

    public static User toUser(UserDto userDto) {
        return new User(
                userDto.getId() != null ? userDto.getId() : 0,
                userDto.getName() != null ? userDto.getName() : "",
                userDto.getEmail() != null ? (userDto.getEmail()) : ""
        );
    }
}