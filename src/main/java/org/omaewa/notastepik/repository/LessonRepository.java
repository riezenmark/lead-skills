package org.omaewa.notastepik.repository;

import org.omaewa.notastepik.domain.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {
    @Query("select l from Lesson l where l.module.id = :moduleId")
    Iterable<Lesson> findAllByModule_Id(final Long moduleId);

    @Override
    @EntityGraph(attributePaths = {"module", "module.announcement", "module.announcement.subject", "module.announcement.user"})
    Page<Lesson> findAll(final Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"module", "module.announcement", "module.announcement.subject", "module.announcement.user"})
    Optional<Lesson> findById(final Long aLong);

    boolean existsByNumberOrNameAndModule_Id(final short number, final String name, final Long moduleId);
}
