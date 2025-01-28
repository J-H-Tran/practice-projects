package org.app.manager.library.util;

import org.app.manager.library.model.BorrowRecord;
import org.app.manager.library.service.BookService;
import org.app.manager.library.service.BorrowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@ShellComponent
public class BorrowRecordCommands {

    @Autowired
    private BorrowRecordService borrowRecordService;

    @Autowired
    private BookService bookService;

    @ShellMethod("List all borrow records")
    public List<BorrowRecord> listBorrowRecords() {
        return borrowRecordService.findAllBorrowRecords();
    }

    @ShellMethod("Find a borrow record by ID")
    public BorrowRecord findBorrowRecord(int id) {
        try {
            return borrowRecordService.findBorrowRecordById(id);
        } catch (NoSuchElementException e) {
            System.err.println("Error: Borrow record not found with ID: " + id);
            return null;
        }
    }

    @ShellMethod("Borrow a book")
    public String borrowBook(int bookId, int memberId) {
        if (!bookService.findBookById(bookId).isAvailable()) {
            return "Book is not available for borrowing";
        }
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBookId(bookId);
        borrowRecord.setMemberId(memberId);
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecordService.saveBorrowRecord(borrowRecord);
        return "Book borrowed successfully";
    }

    @ShellMethod("Return a book")
    public String returnBook(int id) {
        BorrowRecord borrowRecord = borrowRecordService.findBorrowRecordById(id);
        if (borrowRecord == null) {
            return "Borrow record not found";
        }
        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecordService.saveBorrowRecord(borrowRecord);
        return "Book returned successfully";
    }

    @ShellMethod("Delete a borrow record by ID")
    public String deleteBorrowRecord(int id) {
        BorrowRecord borrowRecord = borrowRecordService.findBorrowRecordById(id);
        borrowRecordService.deleteBorrowRecordById(id);
        return "Borrow record with ID " +
                borrowRecord.getId() +
                " deleted successfully";
    }
}
