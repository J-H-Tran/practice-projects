package org.app.manager.library.util;

import org.app.manager.library.model.BorrowRecord;
import org.app.manager.library.service.BookService;
import org.app.manager.library.service.BorrowRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@ShellComponent
public class BorrowRecordCommands {

    private static final Logger logger = LoggerFactory.getLogger(BorrowRecordCommands.class);

    @Autowired
    private BorrowRecordService borrowRecordService;

    @Autowired
    private BookService bookService;

    @ShellMethod("List all borrow records")
    public List<BorrowRecord> listBorrowRecords() {
        logger.info("Listing all borrow records");
        return borrowRecordService.findAllBorrowRecords();
    }

    @ShellMethod("Find a borrow record by ID")
    public BorrowRecord findBorrowRecord(int id) {
        try {
            logger.info("Finding borrow record with ID: {}", id);
            return borrowRecordService.findBorrowRecordById(id);
        } catch (NoSuchElementException e) {
            logger.error("Error: Borrow record not found with ID: {}", id, e);
            return null;
        }
    }

    @ShellMethod("Borrow a book")
    public String borrowBook(int bookId, int memberId) {
        if (!bookService.findBookById(bookId).isAvailable()) {
            logger.error("Book is not available for borrowing");
            return "Book is not available for borrowing";
        }
        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBookId(bookId);
        borrowRecord.setMemberId(memberId);
        borrowRecord.setBorrowDate(LocalDate.now());
        borrowRecordService.saveBorrowRecord(borrowRecord);
        logger.info("Book borrowed successfully");
        return "Book borrowed successfully";
    }

    @ShellMethod("Return a book")
    public String returnBook(int id) {
        BorrowRecord borrowRecord = borrowRecordService.findBorrowRecordById(id);
        if (borrowRecord == null) {
            logger.error("Borrow record not found");
            return "Borrow record not found";
        }
        borrowRecord.setReturnDate(LocalDate.now());
        borrowRecordService.saveBorrowRecord(borrowRecord);
        logger.info("Book returned successfully");
        return "Book returned successfully";
    }

    @ShellMethod("Delete a borrow record by ID")
    public String deleteBorrowRecord(int id) {
        try {
            logger.info("Deleting borrow record with ID: {}", id);
            BorrowRecord borrowRecord = borrowRecordService.findBorrowRecordById(id);
            borrowRecordService.deleteBorrowRecordById(id);
            return "Borrow record with ID " +
                    borrowRecord.getId() +
                    " deleted successfully";
        } catch (NoSuchElementException e) {
            logger.error("Error: Borrow record not found with ID: {}", id, e);
            return "Error: Borrow record not found with ID: " + id;
        }
    }
}
