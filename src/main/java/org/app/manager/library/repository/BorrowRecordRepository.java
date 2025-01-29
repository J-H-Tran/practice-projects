package org.app.manager.library.repository;

import org.app.manager.library.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
    Optional<BorrowRecord> findByBookIdAndLibMemberIdAndReturnDateIsNull(Long bookId, Long libMemberId);
}
