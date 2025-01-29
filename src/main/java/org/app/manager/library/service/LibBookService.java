package org.app.manager.library.service;

import org.app.manager.library.model.LibBook;

import java.util.List;
import java.util.Optional;

public interface LibBookService {
    void addBook(LibBook libBook);
    Optional<LibBook> getBookById(Long id);
    List<LibBook> getAllBooks();
    void removeBook(Long id);
}