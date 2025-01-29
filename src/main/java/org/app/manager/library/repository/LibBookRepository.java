package org.app.manager.library.repository;

import org.app.manager.library.model.LibBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibBookRepository extends JpaRepository<LibBook, Long> {
}