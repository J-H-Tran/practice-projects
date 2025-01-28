package org.app.manager.library.service.impl;

import org.app.manager.library.model.Book;
import org.app.manager.library.repository.BookRepository;
import org.app.manager.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepo;

    @Override
    public List<Book> findAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Book findBookById(int id) {
        return bookRepo.findById(id).orElseThrow(() ->
                new NoSuchElementException("Book not found with id: " + id));
    }

    @Transactional
    @Override
    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    @Transactional
    @Override
    public void deleteBookById(int id) {
        if (!bookRepo.existsById(id)) {
            throw new NoSuchElementException("Book not found with id: " + id);
        }
        bookRepo.deleteById(id);
    }
}
