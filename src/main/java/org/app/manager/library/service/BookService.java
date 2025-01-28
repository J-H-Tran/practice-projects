package org.app.manager.library.service;

import org.app.manager.library.model.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBooks();
    Book findBookById(int id);
    Book saveBook(Book book);
    void deleteBookById(int id);
}
