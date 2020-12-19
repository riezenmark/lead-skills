package org.omaewa.notastepik.repository;

import org.omaewa.notastepik.domain.Module;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    @Query("select m from Module m where m.announcement.id = :announcementId")
    Iterable<Module> findAllByAnnouncement_Id(final Long announcementId);

    @Override
    @EntityGraph(attributePaths = {"announcement", "announcement.author", "announcement.author.authorities"})
    List<Module> findAll();

    @Override
    @EntityGraph(attributePaths = {"announcement", "announcement.author", "announcement.author.authorities"})
    Optional<Module> findById(final Long id);

    boolean existsByNumberOrNameAndAnnouncement_Id(final short number, final String name, final Long announcementId);
}
