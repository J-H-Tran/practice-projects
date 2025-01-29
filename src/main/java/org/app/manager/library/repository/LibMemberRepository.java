package org.app.manager.library.repository;

import org.app.manager.library.model.LibMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibMemberRepository extends JpaRepository<LibMember, Long> {
    Optional<LibMember> findByEmail(String email);
}
