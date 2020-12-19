package org.omaewa.notastepik.repository;

import org.omaewa.notastepik.domain.Lesson;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query("select l from Lesson l where l.module.id = :moduleId")
    Iterable<Lesson> findAllByModule_Id(final Long moduleId);

    @Override
    @EntityGraph(attributePaths = {"module", "module.announcement", "module.announcement.author"})
    List<Lesson> findAll();

    @Override
    @EntityGraph(attributePaths = {"module", "module.announcement", "module.announcement.author"})
    Optional<Lesson> findById(final Long aLong);

    boolean existsByNumberOrNameAndModule_Id(final short number, final String name, final Long moduleId);
}
