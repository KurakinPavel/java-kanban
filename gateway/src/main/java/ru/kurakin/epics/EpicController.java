package ru.kurakin.epics;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/epics")
@AllArgsConstructor
@Slf4j
public class EpicController {
    private final EpicClient epicClient;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResponseEntity<Object>> saveEpic(@RequestBody @Valid NewEpicDto newEpicDto) {
        log.info("Контроллер получил запрос на добавление нового эпика");
        return epicClient.saveEpic(newEpicDto);
    }
}
