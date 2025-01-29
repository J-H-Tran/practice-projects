package org.app.manager.library.service;

import org.app.manager.library.model.BorrowRecord;

import java.util.List;
import java.util.Optional;

public interface BorrowRecordService {
    void addBorrowRecord(BorrowRecord borrowRecord);
    Optional<BorrowRecord> getBorrowRecordById(Long id);
    List<BorrowRecord> getAllBorrowRecords();
    void removeBorrowRecord(Long id);
    Optional<BorrowRecord> findActiveBorrowRecord(Long bookId, Long memberId);
}
