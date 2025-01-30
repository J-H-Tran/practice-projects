package org.app.manager.library.shell;

import org.app.manager.library.model.LibBook;
import org.app.manager.library.service.LibBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@ShellComponent
public class LibBookCommands {
    private final Logger logger = LoggerFactory.getLogger(LibBookCommands.class);

    @Autowired
    private LibBookService libBookService;

    @ShellMethod(key = "add-book")
    public String addBook(
            @ShellOption String title, 
            @ShellOption String author,
            @ShellOption Long isbn
    ) {
        LibBook book = new LibBook();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        libBookService.addBook(book);

        return "Book added: " + book;
    }

    @ShellMethod(key = "get-book-id")
    public String getBookById(
            @ShellOption Long id
    ) {
        Optional<LibBook> book = libBookService.getBookById(id);

        return book.map(value -> "Book found: " + value).orElse("Book not found");
    }

    @ShellMethod(key = "list-books")
    public List<LibBook> listBooks() {
        return libBookService.getAllBooks();
    }

    @ShellMethod(key = "remove-book")
    public String removeBook(
            @ShellOption Long id
    ) {
        if (libBookService.getBookById(id).isPresent()) {
            logger.info("Searching... {}", getBookById(id));
            libBookService.removeBook(id);
            return "Book removed with ID: " + id;
        } else {
            logger.warn("Book not found with ID: {}", id);
            return "Book not found with ID: " + id;
        }
    }

    @ShellMethod(key = "export-books")
    public String exportBooksToCSV(
            @ShellOption String filePath
    ) {
        try {
            libBookService.exportBooksToCSV(filePath);
            return "Books exported to current directory: " + filePath;
        } catch (IOException e) {
            return "Error exporting books: " + e.getMessage();
        }
    }
}