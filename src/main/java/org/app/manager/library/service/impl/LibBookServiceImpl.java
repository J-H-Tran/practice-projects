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

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LibBookServiceImpl implements LibBookService {

    @Autowired
    private BorrowRecordRepository borrowRecordRepository;

    @Autowired
    private LibBookRepository libBookRepository;

    @Autowired
    private LibMemberRepository libMemberRepository;

    @Override
    public void addBook(LibBook book) {
        libBookRepository.save(book);
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
            borrowRecord.setLibraryBook(book.get());
            borrowRecord.setLibraryMember(member.get());
            borrowRecord.setBorrowDate(LocalDateTime.now());
            borrowRecordRepository.save(borrowRecord);
        } else {
            throw new IllegalArgumentException("Book or Member not found.");
        }
    }

    @Override
    public void exportBooksToCSV(String fileName) throws IOException {
        String currentDir = System.getProperty("user.dir");
        String filePath = Paths.get(currentDir, fileName).toString();

        List<LibBook> books = getAllBooks();

        try (FileWriter fw = new FileWriter(filePath)) {
            fw.write("ID,Title,Author,ISBN\n");

            books.stream()
                .map(book -> {
                    StringBuilder sb = new StringBuilder();

                    sb.append(book.getId()).append(",")
                        .append(book.getTitle()).append(",")
                        .append(book.getAuthor()).append(",")
                        .append(book.getIsbn());

                    return sb.toString();
                })
                .forEach(line -> {
                    try {
                        fw.write(line + "\n");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        }
    }
}