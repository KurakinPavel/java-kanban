package ru.kurakin.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.kurakin.model.Epic;
import ru.kurakin.repositories.EpicRepository;

@Slf4j
@Service
@AllArgsConstructor
public class EpicService {
    private final EpicRepository epicRepository;

}
