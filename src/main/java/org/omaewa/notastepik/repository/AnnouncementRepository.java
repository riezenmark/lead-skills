package org.omaewa.notastepik.repository;

import org.omaewa.notastepik.domain.Announcement;
import org.omaewa.notastepik.domain.AnnouncementType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {
    @Query("select a from Announcement a where a.author.id = :userId")
    List<Announcement> findAllByUser_Id(final String userId);

    boolean existsByHeading(final String heading);

    @Override
    @EntityGraph(attributePaths = {"author", "author.authorities"})
    List<Announcement> findAll();

    @Override
    @EntityGraph(attributePaths = {"author", "author.authorities"})
    Optional<Announcement> findById(final Long id);

    @Query("SELECT a from Announcement a where upper(a.heading) like %:pattern%")
    List<Announcement> findByHeadingLike(final String pattern);

    List<Announcement> findByType(final AnnouncementType type);
}
