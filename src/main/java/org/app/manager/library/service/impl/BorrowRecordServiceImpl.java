package org.app.manager.library.service.impl;

import org.app.manager.library.model.BorrowRecord;
import org.app.manager.library.repository.BorrowRecordRepository;
import org.app.manager.library.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepo;

    @Override
    public List<BorrowRecord> findAllBorrowRecords() {
        return borrowRecordRepo.findAll();
    }

    @Override
    public BorrowRecord findBorrowRecordById(int id) {
        return borrowRecordRepo.findById(id).orElseThrow(() ->
                new NoSuchElementException("Borrow record not found with id: " + id));
    }

    @Override
    public BorrowRecord saveBorrowRecord(BorrowRecord borrowRecord) {
        return borrowRecordRepo.save(borrowRecord);
    }

    @Override
    public void deleteBorrowRecordById(int id) {
        if (!borrowRecordRepo.existsById(id)) {
            throw new NoSuchElementException("Borrow record not found with id: " + id);
        }
        borrowRecordRepo.deleteById(id);
    }

    @Override
    public List<BorrowRecord> findBorrowRecordsByMemberId(int memberId) {
        return borrowRecordRepo.findBorrowRecordsByMemberId(memberId);
    }

    @Override
    public List<BorrowRecord> findBorrowRecordsByBookId(int bookId) {
        return borrowRecordRepo.findBorrowRecordsByBookId(bookId);
    }

}
