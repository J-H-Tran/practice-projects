package org.app.manager.library.service.impl;

import org.app.manager.library.model.BorrowRecord;
import org.app.manager.library.model.LibBook;
import org.app.manager.library.model.LibMember;
import org.app.manager.library.repository.BorrowRecordRepository;
import org.app.manager.library.repository.LibBookRepository;
import org.app.manager.library.repository.LibMemberRepository;
import org.app.manager.library.service.LibBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LibBookServiceImpl implements LibBookService {

    @Autowired
    private LibBookRepository libBookRepository;

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private LibMemberRepository libMemberRepository;

    // because we are autowiring the book repo for crud ops
    // we can focus on business logic in the service layer
    // TODO: create/implement meaningful methods for business logic
    public boolean isBookAvailable(Long bookId, Long memberId) {
        Optional<BorrowRecord> activeBorrowRecord =
                borrowRecordRepository.findByBookIdAndLibMemberIdAndReturnDateIsNull(bookId, memberId);
        return activeBorrowRecord.isEmpty();
    }

    @Override
    public void addBook(LibBook libBook) {
        libBookRepository.save(libBook);
    }

    @Override
    public Optional<LibBook> getBookById(Long id) {
        return libBookRepository.findById(id);
    }

    @Override
    public List<LibBook> getAllBooks() {
        return libBookRepository.findAll();
    }

    @Override
    public void removeBook(Long id) {
        libBookRepository.deleteById(id);
    }

    public void borrowBook(
            Long bookId,
            Long memberId
    ) {
        Optional<LibBook> book = libBookRepository.findById(bookId);
        Optional<LibMember> member = libMemberRepository.findById(memberId);

        if (book.isPresent() && member.isPresent()) {
            BorrowRecord borrowRecord = new BorrowRecord();
            borrowRecord.setBook(book.get());
            borrowRecord.setLibMember(member.get());
            borrowRecord.setBorrowDate(LocalDate.now());
            borrowRecordRepository.save(borrowRecord);
        } else {
            throw new IllegalArgumentException("Book or Member not found.");
        }
    }

    public void returnBook(
            Long bookId,
            Long memberId
    ) {
        Optional<BorrowRecord> borrowRecord =
                borrowRecordRepository.findByBookIdAndLibMemberIdAndReturnDateIsNull(bookId, memberId);

        if (borrowRecord.isPresent()) {
            BorrowRecord record = borrowRecord.get();
            record.setReturnDate(LocalDate.now());
            borrowRecordRepository.save(record);
        } else {
            throw new IllegalArgumentException("Borrow record not found.");
        }
    }
}