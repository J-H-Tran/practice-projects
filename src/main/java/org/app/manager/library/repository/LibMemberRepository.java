package org.app.manager.library.repository;

import org.app.manager.library.model.LibMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibMemberRepository extends JpaRepository<LibMember, Long> {
}
