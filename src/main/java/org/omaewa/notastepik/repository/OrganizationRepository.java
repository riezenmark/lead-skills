package org.omaewa.notastepik.repository;

import org.omaewa.notastepik.domain.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    boolean existsByName(final String name);
}
