package org.omaewa.notastepik.repository;

import org.omaewa.notastepik.domain.Announcement;
import org.omaewa.notastepik.repository.custom.api.CustomAnnouncementRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long>, CustomAnnouncementRepository {
    @Query("select a from Announcement a where a.author.id = :userId")
    Iterable<Announcement> findAllByUser_Id(final Long userId);

    boolean existsByHeading(final String heading);

    @Override
    @EntityGraph(attributePaths = {"subject", "user", "user.authorities"})
    List<Announcement> findAll();

    @Override
    @EntityGraph(attributePaths = {"subject", "user", "user.authorities"})
    Optional<Announcement> findById(final Long aLong);
}
