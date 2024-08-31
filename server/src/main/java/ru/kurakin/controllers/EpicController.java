package ru.kurakin.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.kurakin.dto.epic.FullEpicDto;
import ru.kurakin.dto.epic.NewEpicDto;
import ru.kurakin.dto.epic.ShortEpicDto;
import ru.kurakin.dto.epic.UpdateEpicDto;
import ru.kurakin.services.EpicService;

import java.util.List;

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

    @PatchMapping("/{epicId}")
    public FullEpicDto updateEpic(@PathVariable Integer epicId,
                                  @RequestBody UpdateEpicDto updateEpicDto) {
        log.info("Запрос на обновление задачи с id={}", epicId);
        return epicService.updateEpic(epicId, updateEpicDto);
    }

    @PatchMapping("/{epicId}/add/{taskId}")
    public FullEpicDto addTaskToEpic(@PathVariable Integer epicId,
                                     @PathVariable Integer taskId) {
        log.info("Запрос на добавление в эпик с id={} задачи с id={}", epicId, taskId);
        return epicService.addTaskToEpic(epicId, taskId);
    }

    @PatchMapping("/{epicId}/remove/{taskId}")
    public FullEpicDto removeTaskFromEpic(@PathVariable Integer epicId,
                                          @PathVariable Integer taskId) {
        log.info("Запрос на удаление из эпика с id={} задачи с id={}", epicId, taskId);
        return epicService.removeTaskFromEpic(epicId, taskId);
    }

    @GetMapping("/{epicId}")
    public FullEpicDto getEpicById(@PathVariable Integer epicId) {
        log.info("Запрос на получение эпика с id={}", epicId);
        return epicService.getEpicById(epicId);
    }

    @GetMapping("/all")
    public List<ShortEpicDto> getAllEpics() {
        log.info("Запрос на получение всех эпиков");
        return epicService.getAllEpics();
    }

    @GetMapping("/with-tasks")
    public List<ShortEpicDto> getEpicsWithTasks() {
        log.info("Запрос на получение эпиков, в которых есть задачи");
        return epicService.getEpicsWithTasks();
    }

    @GetMapping("/without-tasks")
    public List<ShortEpicDto> getEpicsWithoutTasks() {
        log.info("Запрос на получение эпиков, в которых нет задач");
        return epicService.getEpicsWithoutTasks();
    }
}
