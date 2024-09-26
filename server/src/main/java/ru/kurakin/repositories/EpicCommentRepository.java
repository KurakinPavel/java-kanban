package ru.kurakin.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kurakin.model.EpicComment;

@Repository
public interface EpicCommentRepository extends JpaRepository<EpicComment, Integer> {
}
