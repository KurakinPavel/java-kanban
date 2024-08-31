package ru.kurakin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.kurakin.model.Epic;

import java.util.List;

@Repository
public interface EpicRepository extends JpaRepository<Epic, Integer> {

    @Query("select e from Epic e where e.tasks is empty")
    List<Epic> findAllWhereTasksIsEmpty();

    @Query("select e from Epic e where e.tasks is not empty")
    List<Epic> findAllWhereTasksIsNotEmpty();
}
