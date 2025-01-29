package org.app.manager.library.service.impl;

import org.app.manager.library.model.BorrowRecord;
import org.app.manager.library.repository.BorrowRecordRepository;
import org.app.manager.library.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Override
    public void addBorrowRecord(BorrowRecord borrowRecord) {
        borrowRecordRepository.save(borrowRecord);
    }

    @Override
    public Optional<BorrowRecord> getBorrowRecordById(Long id) {
        return borrowRecordRepository.findById(id);
    }

    @Override
    public List<BorrowRecord> getAllBorrowRecords() {
        return borrowRecordRepository.findAll();
    }

    @Override
    public void removeBorrowRecord(Long id) {
        borrowRecordRepository.deleteById(id);
    }

    @Override
    public Optional<BorrowRecord> findActiveBorrowRecord(
            Long bookId,
            Long memberId
    ) {
        return borrowRecordRepository.findByBookIdAndLibMemberIdAndReturnDateIsNull(bookId, memberId);
    }
}