package ru.kurakin.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kurakin.services.EpicService;

@RestController
@RequestMapping(path = "/epics")
@AllArgsConstructor
@Slf4j
public class EpicController {
    private final EpicService epicService;
}
