package org.omaewa.notastepik.repository;

import org.omaewa.notastepik.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    @Override
    @EntityGraph(value = "userWithRoles")
    Page<User> findAll(final Pageable pageable);

    User findByUsername(final String username);

    boolean existsByUsernameOrEmail(final String username, final String email);

    boolean existsByUsername(final String username);
}
