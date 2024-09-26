package ru.kurakin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kurakin.model.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}
