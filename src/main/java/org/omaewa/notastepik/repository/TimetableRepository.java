package org.omaewa.notastepik.repository;

import org.omaewa.notastepik.domain.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {
    Iterable<Timetable> findByUser_Id(final String userId);
}
