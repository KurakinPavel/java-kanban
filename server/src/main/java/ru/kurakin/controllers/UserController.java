package ru.kurakin.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kurakin.dto.user.FullUserDto;
import ru.kurakin.dto.user.NewUserDto;
import ru.kurakin.services.UserService;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FullUserDto saveUser(@RequestBody NewUserDto newUserDto) {
        log.info("Контроллер сервера получил запрос на добавление нового пользователя");
        return userService.saveUser(newUserDto);
    }
}
