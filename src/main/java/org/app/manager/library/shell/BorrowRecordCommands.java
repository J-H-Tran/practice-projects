package org.app.manager.library.shell;

import org.app.manager.library.model.BorrowRecord;
import org.app.manager.library.model.LibBook;
import org.app.manager.library.model.LibMember;
import org.app.manager.library.repository.LibBookRepository;
import org.app.manager.library.repository.LibMemberRepository;
import org.app.manager.library.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class BorrowRecordCommands {

    @Autowired
    private BorrowRecordService borrowRecordService;

    @Autowired
    private LibBookRepository libBookRepository;

    @Autowired
    private LibMemberRepository libMemberRepository;

    @ShellMethod(key = "add-borrow-record")
    public String addBorrowRecord(
            @ShellOption Long bookId,
            @ShellOption Long memberId
    ) {
        Optional<LibBook> book = libBookRepository.findById(bookId);
        Optional<LibMember> member = libMemberRepository.findById(memberId);

        if (book.isEmpty()) {
            return "Book not found with ID: " + bookId;
        }

        if (member.isEmpty()) {
            return "Member not found with ID: " + memberId;
        }

        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setLibraryBook(book.get());
        borrowRecord.setLibraryMember(member.get());
        LocalDateTime borrowDate = LocalDateTime.now();
        borrowRecord.setBorrowDate(borrowDate);
        borrowRecord.setReturnDate(borrowDate);
        borrowRecordService.addBorrowRecord(borrowRecord);

        return "Borrow record added: " + borrowRecord;
    }

    @ShellMethod(key = "get-borrow-record-id")
    public String getBorrowRecordById(
            @ShellOption Long id
    ) {
        Optional<BorrowRecord> borrowRecord = borrowRecordService.getBorrowRecordById(id);

        return borrowRecord.map(value -> "Borrow record found: " + value).orElse("Borrow record not found");
    }

    @ShellMethod(key = "list-borrow-records")
    public List<BorrowRecord> listBorrowRecords() {
        return borrowRecordService.getAllBorrowRecords();
    }

    @ShellMethod(key = "remove-borrow-record")
    public String removeBorrowRecord(
            @ShellOption Long id
    ) {
        if (borrowRecordService.getBorrowRecordById(id).isPresent()) {
            borrowRecordService.removeBorrowRecord(id);
            return "Borrow record removed with ID: " + id;
        } else {
            return "Borrow record not found with ID: " + id;
        }
    }
}