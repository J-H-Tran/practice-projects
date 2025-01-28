package org.app.manager.library.repository;

import org.app.manager.library.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Integer> {
    List<BorrowRecord> findBorrowRecordsByMemberId(int memberId);
    List<BorrowRecord> findBorrowRecordsByBookId(int bookId);
}
