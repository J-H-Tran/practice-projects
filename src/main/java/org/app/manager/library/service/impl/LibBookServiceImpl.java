package org.app.manager.library.service.impl;

import org.app.manager.library.model.LibBook;
import org.app.manager.library.repository.LibBookRepository;
import org.app.manager.library.service.LibBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibBookServiceImpl implements LibBookService {
    private final LibBookRepository libBookRepository;

    @Autowired
    public LibBookServiceImpl(LibBookRepository libBookRepository) {
        this.libBookRepository = libBookRepository;
    }

    // because we are autowiring the book repo for crud ops
    // we can focus on business logic in the service layer
    // TODO: create/implement meaningful methods for business logic
    @Override
    public void addBook(LibBook libBook) {
        // Business logic can be added here
        libBookRepository.save(libBook);
    }

    @Override
    public Optional<LibBook> getBookById(Long id) {
        // Business logic can be added here
        return libBookRepository.findById(id);
    }

    @Override
    public List<LibBook> getAllBooks() {
        // Business logic can be added here
        return libBookRepository.findAll();
    }

    @Override
    public void removeBook(Long id) {
        // Business logic can be added here
        libBookRepository.deleteById(id);
    }
}