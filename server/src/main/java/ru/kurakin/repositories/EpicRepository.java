package ru.kurakin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kurakin.model.Epic;

public interface EpicRepository extends JpaRepository<Epic, Integer> {
}
