package org.omaewa.notastepik.repository;

import org.omaewa.notastepik.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("select r from Review r where r.announcement.id = :announcementId")
    Iterable<Review> findAllByAnnouncement_Id(final Long announcementId);

    @Query("select r from Review r where r.user.id = :userId")
    Iterable<Review> findAllByUser_Id(final Long userId);

    @Override
    @EntityGraph(attributePaths = {"announcement", "announcement.subject", "user"})
    Page<Review> findAll(final Pageable pageable);

    @Override
    @EntityGraph(attributePaths = {"announcement", "announcement.subject", "user"})
    Optional<Review> findById(final Long id);
}
