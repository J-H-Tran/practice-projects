package org.app.manager.library.service;

import org.app.manager.library.model.BorrowRecord;

import java.util.List;

public interface BorrowRecordService {
    List<BorrowRecord> findAllBorrowRecords();
    BorrowRecord findBorrowRecordById(Long id);
    BorrowRecord saveBorrowRecord(BorrowRecord borrowRecord);
    void deleteBorrowRecordById(Long id);
    List<BorrowRecord> findBorrowRecordsByMemberId(Long memberId);
    List<BorrowRecord> findBorrowRecordsByBookId(Long bookId);
}
