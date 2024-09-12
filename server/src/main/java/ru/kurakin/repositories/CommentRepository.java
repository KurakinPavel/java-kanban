package ru.kurakin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kurakin.model.TaskComment;

@Repository
public interface CommentRepository extends JpaRepository<TaskComment, Integer> {
}
