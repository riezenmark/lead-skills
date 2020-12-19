package org.omaewa.notastepik.repository;

import org.omaewa.notastepik.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    boolean existsByName(final String name);
}
