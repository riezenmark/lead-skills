package org.omaewa.notastepik.repository;

import org.omaewa.notastepik.domain.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    @Query("select a from Announcement a where a.user.id = :userId")
    Iterable<Announcement> findAllByUser_Id(final Long userId);

    @Query("select a from Announcement a where a.subject.id = :subjectId")
    Iterable<Announcement> findAllBySubject_Id(final Integer subjectId);

    boolean existsByHeading(final String heading);

    @Override
    @EntityGraph(attributePaths = {"subject", "user", "user.authorities"})
    Page<Announcement> findAll(final Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"subject", "user", "user.authorities"})
    Optional<Announcement> findById(final Long aLong);
}
