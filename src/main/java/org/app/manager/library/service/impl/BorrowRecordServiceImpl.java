package org.app.manager.library.service.impl;

import org.app.manager.library.model.BorrowRecord;
import org.app.manager.library.repository.BorrowRecordRepository;
import org.app.manager.library.service.BorrowRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {

    private static final Logger logger = LoggerFactory.getLogger(BorrowRecordServiceImpl.class);

    @Autowired
    private BorrowRecordRepository borrowRecordRepo;

    @Override
    public List<BorrowRecord> findAllBorrowRecords() {
        logger.info("Fetching all borrow records from the database");
        return borrowRecordRepo.findAll();
    }

    @Override
    public BorrowRecord findBorrowRecordById(int id) {
        logger.info("Fetching borrow record with id: {}", id);
        return borrowRecordRepo.findById(id).orElseThrow(() -> {
            logger.error("Borrow record not found with id: {}", id);
            return new NoSuchElementException("Borrow record not found with id: " + id);
        });
    }

    @Override
    public BorrowRecord saveBorrowRecord(BorrowRecord borrowRecord) {
        logger.info("Saving borrow record for book ID: {}", borrowRecord.getBookId());
        return borrowRecordRepo.save(borrowRecord);
    }

    @Override
    public void deleteBorrowRecordById(int id) {
        if (!borrowRecordRepo.existsById(id)) {
            logger.error("Borrow record not found with ID: {}", id);
            throw new NoSuchElementException("Borrow record not found with id: " + id);
        }
        logger.info("Deleting borrow record with ID: {}", id);
        borrowRecordRepo.deleteById(id);
    }

    @Override
    public List<BorrowRecord> findBorrowRecordsByMemberId(int memberId) {
        logger.info("Finding borrow records for member ID: {}", memberId);
        return borrowRecordRepo.findBorrowRecordsByMemberId(memberId);
    }

    @Override
    public List<BorrowRecord> findBorrowRecordsByBookId(int bookId) {
        logger.info("Finding borrow records for book ID: {}", bookId);
        return borrowRecordRepo.findBorrowRecordsByBookId(bookId);
    }

}
