package org.app.manager.library.service.impl;

import org.app.manager.library.model.BorrowRecord;
import org.app.manager.library.repository.BorrowRecordRepository;
import org.app.manager.library.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Override
    public void addBorrowRecord(BorrowRecord borrowRecord) {
        LocalDateTime borrowDate = borrowRecord.getBorrowDate();
        borrowRecord.setReturnDate(borrowDate);
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
}