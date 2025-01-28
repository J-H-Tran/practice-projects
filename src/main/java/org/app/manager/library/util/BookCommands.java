package org.app.manager.library.util;

import org.app.manager.library.model.Book;
import org.app.manager.library.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.NoSuchElementException;

@ShellComponent
public class BookCommands {

    private static final Logger logger = LoggerFactory.getLogger(BookCommands.class);

    @Autowired
    private BookService bookService;

    @ShellMethod("List all books")
    public List<Book> listBooks() {
        logger.info("Listing all books");
        return bookService.findAllBooks();
    }

    @ShellMethod("Find a book by ID")
    public Book findBook(@ShellOption int id) {
        try {
            logger.info("Finding book with ID: {}", id);
            return bookService.findBookById(id);
        } catch (NoSuchElementException e) {
            logger.error("Error: Book not found with ID: {}", id, e);
            return null;
        }

    }

    @ShellMethod("Add a new book")
    public Book addBook(
            @ShellOption String title,
            @ShellOption String author,
            @ShellOption String isbn,
            @ShellOption int publicationYear
    ) {
        if (title == null || title.isEmpty() || author == null || author.isEmpty() || isbn == null || isbn.isEmpty()) {
            logger.error("Error: Title, author, and ISBN must not be empty");
            return null;
        }
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);
        book.setPublicationYear(publicationYear);
        book.setAvailable(true);
        logger.info("Adding new book: {}", book.getTitle());
        return bookService.saveBook(book);
    }

    @ShellMethod("Delete a book by ID")
    public String deleteBook(@ShellOption int id) {
        try {
            logger.info("Deleting book with ID: {}", id);
            Book book = bookService.findBookById(id);
            bookService.deleteBookById(id);
            return new StringBuilder()
                    .append("ISBN: ")
                    .append(book.getIsbn())
                    .append(" ")
                    .append(book.getTitle())
                    .append(" deleted successfully")
                    .toString();
        } catch (NoSuchElementException e) {
            logger.error("Error: Book not found with ID: {}", id, e);
            return "Error: Book not found with ID: " + id;
        }
    }
}
