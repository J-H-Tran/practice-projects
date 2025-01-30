package org.app.manager.library.service;

import org.app.manager.library.model.LibBook;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface LibBookService {
    void addBook(LibBook book);
    Optional<LibBook> getBookById(Long id);
    List<LibBook> getAllBooks();
    void removeBook(Long id);
    void exportBooksToCSV(String filePath) throws IOException;
}