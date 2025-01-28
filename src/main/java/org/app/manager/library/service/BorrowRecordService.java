package org.app.manager.library.service;

import org.app.manager.library.model.BorrowRecord;

import java.util.List;

public interface BorrowRecordService {
    List<BorrowRecord> findAllBorrowRecords();
    BorrowRecord findBorrowRecordById(int id);
    BorrowRecord saveBorrowRecord(BorrowRecord borrowRecord);
    void deleteBorrowRecordById(int id);
    List<BorrowRecord> findBorrowRecordsByMemberId(int memberId);
    List<BorrowRecord> findBorrowRecordsByBookId(int bookId);
}
