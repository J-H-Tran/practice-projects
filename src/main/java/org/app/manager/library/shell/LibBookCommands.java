package org.app.manager.library.shell;

import org.app.manager.library.model.LibBook;
import org.app.manager.library.service.LibBookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.Optional;

@ShellComponent
public class LibBookCommands {
    private final Logger logger = LoggerFactory.getLogger(LibBookCommands.class);

    private final LibBookService libBookService;

    @Autowired
    public LibBookCommands(LibBookService libBookService) {
        this.libBookService = libBookService;
    }

    @ShellMethod(key = "add-book")
    public String addBook(
            @ShellOption String title, 
            @ShellOption String author,
            @ShellOption Long isbn
    ) {
        LibBook libBook = new LibBook();
        libBook.setTitle(title);
        libBook.setAuthor(author);
        libBook.setIsbn(isbn);
        libBookService.addBook(libBook);
        return "Book added: " + libBook.toString();
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
}
