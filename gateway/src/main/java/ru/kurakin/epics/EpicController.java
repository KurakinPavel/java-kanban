package ru.kurakin.epics;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
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
        log.info("Контроллер gateway получил запрос на добавление нового эпика");
        return epicClient.saveEpic(newEpicDto);
    }

    @PatchMapping("/{epicId}")
    public Mono<ResponseEntity<Object>> updateEpic(@PathVariable Integer epicId,
                                                   @RequestBody UpdateEpicDto updateEpicDto) {
        log.info("Контроллер gateway получил запрос на обновление эпика с id={}", epicId);
        return epicClient.updateEpic(epicId, updateEpicDto);
    }

    @PatchMapping("/{epicId}/add/{taskId}")
    public Mono<ResponseEntity<Object>> addTaskToEpic(@PathVariable Integer epicId,
                                                      @PathVariable Integer taskId) {
        log.info("Контроллер gateway получил запрос на добавление в эпик с id={} задачи с id={}", epicId, taskId);
        return epicClient.addTaskToEpic(epicId, taskId);
    }

    @PatchMapping("/{epicId}/remove/{taskId}")
    public Mono<ResponseEntity<Object>> removeTaskFromEpic(@PathVariable Integer epicId,
                                                           @PathVariable Integer taskId) {
        log.info("Контроллер gateway получил запрос на удаление из эпика с id={} задачи с id={}", epicId, taskId);
        return epicClient.removeTaskFromEpic(epicId, taskId);
    }

    @GetMapping("/{epicId}")
    public Mono<ResponseEntity<Object>> getEpicById(@PathVariable Integer epicId) {
        log.info("Контроллер gateway получил запрос на получение эпика с id={}", epicId);
        return epicClient.getEpicById(epicId);
    }

    @GetMapping("/all")
    public Flux<Object> getAllEpics() {
        log.info("Контроллер gateway получил запрос на получение всех эпиков");
        return epicClient.getAllEpics();
    }

    @GetMapping("/with-tasks")
    public Flux<Object> getEpicsWithTasks() {
        log.info("Контроллер gateway получил запрос на получение эпиков, в которых есть задачи");
        return epicClient.getEpicsWithTasks();
    }

    @GetMapping("/without-tasks")
    public Flux<Object> getEpicsWithoutTasks() {
        log.info("Контроллер gateway получил запрос на получение эпиков, в которых нет задач");
        return epicClient.getEpicsWithoutTasks();
    }
}
