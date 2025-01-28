package org.app.manager.library.service.impl;

import org.app.manager.library.model.Book;
import org.app.manager.library.repository.BookRepository;
import org.app.manager.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    private BookRepository bookRepo;

    @Override
    public List<Book> findAllBooks() {
        logger.info("Fetching all books from the database");
        return bookRepo.findAll();
    }

    @Override
    public Book findBookById(int id) {
        return bookRepo.findById(id).orElseThrow(() -> {
            logger.error("Book not found with id: {}", id);
            return new NoSuchElementException("Book not found with id: " + id);
        });
    }

    @Transactional
    @Override
    public Book saveBook(Book book) {
        logger.info("Saving book: {}", book.getTitle());
        return bookRepo.save(book);
    }

    @Transactional
    @Override
    public void deleteBookById(int id) {
        if (!bookRepo.existsById(id)) {
            logger.error("Book not found with ID: {}", id);
            throw new NoSuchElementException("Book not found with id: " + id);
        }
        logger.info("Deleting book with ID: {}", id);
        bookRepo.deleteById(id);
    }
}
