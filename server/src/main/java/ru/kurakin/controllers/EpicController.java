package ru.kurakin.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kurakin.dto.epic.FullEpicDto;
import ru.kurakin.dto.epic.NewEpicDto;
import ru.kurakin.services.EpicService;

@RestController
@RequestMapping(path = "/epics")
@AllArgsConstructor
@Slf4j
public class EpicController {
    private final EpicService epicService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FullEpicDto saveEpic(@RequestBody NewEpicDto newEpicDto) {
        log.info("Контроллер сервера получил запрос на добавление новой подборки задач");
        return epicService.saveEpic(newEpicDto);
    }
}
