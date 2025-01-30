package org.app.manager.library.controller;

import org.app.manager.library.model.LibBook;
import org.app.manager.library.service.LibBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class LibBookController {

    @Autowired
    private LibBookService libBookService;

    @PostMapping
    public void addBook(@RequestBody LibBook book) {
        libBookService.addBook(book);
    }

    @GetMapping("/{id}")
    public Optional<LibBook> getBookById(@PathVariable Long id) {
        return libBookService.getBookById(id);
    }

    @GetMapping
    public List<LibBook> getAllBooks() {
        return libBookService.getAllBooks();
    }

    @DeleteMapping("/{id}")
    public void removeBook(@PathVariable Long id) {
        libBookService.removeBook(id);
    }
}