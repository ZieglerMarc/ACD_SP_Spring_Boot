package de.springBootExample.bookService.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The BookController class handles HTTP requests related to books.
 * It provides endpoints for retrieving, adding, updating, and deleting books.
 */
// The @RestController annotation indicates that this class is a RESTful controller.
// The @RequestMapping annotation specifies the base URL for all endpoints in this controller.
// In this case, all endpoints will start with "/api/books".
@RestController
@RequestMapping(path = "/api/books")
public class BookController {

    /**
     * The BookService instance used to handle book-related operations.
     */
    private final BookService bookService;

    /**
     * Constructor for BookController.
     *
     * @param bookService the BookService instance to be used
     */

    // The @Autowired annotation is used to inject the BookService dependency
    // into the BookController. This allows Spring to manage the lifecycle of the BookService.
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * This method handles GET requests to retrieve all books.
     *
     * @return a list of all books
     */
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }

    /**
     * This method handles GET requests to retrieve a book by its ID.
     *
     * @param bookId the ID of the book to retrieve
     * @return the book with the specified ID
     */
    @GetMapping(path = "/{bookId}")
    public Book getBookById(@PathVariable("bookId") Long bookId) {
        return bookService.getBookById(bookId);
    }

    /**
     * This method handles GET requests to retrieve a book by its title.
     *
     * @param title the title of the book to retrieve
     * @return the book with the specified title
     */
    @GetMapping(path = "/{title}")
    public List<Book> getBookByTitle(@PathVariable("title") String title) {
        return bookService.getBookByTitle(title);
    }

    /**
     * This method handles GET requests to retrieve all available books.
     *
     * @return a list of available books
     */
    @GetMapping("/available")
    public List<Book> getAvailableBooks() {
        return bookService.getAvailableBooks();
    }

    /**
     * This method handles POST requests to add a new book.
     *
     * @param book the book to add
     */
    @PostMapping
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    /**
     * This method handles DELETE requests to remove a book by its ID.
     *
     * @param bookId the ID of the book to remove
     */
    @DeleteMapping(path = "/{bookId}")
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        bookService.deleteBook(bookId);
    }

    /**
     * This method handles PUT requests to update a book by its ID.
     *
     * @param bookId the ID of the book to update
     * @param book   the updated book information
     */
    @PutMapping(path = "/{bookId}")
    public void updateBook(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        bookService.updateBook(bookId, book);
    }


    /**
     * This method handles PUT requests to update the availability of a book by its ID.
     *
     * @param bookId    the ID of the book to update
     * @param available the new availability status
     */
    @PatchMapping(path = "/{bookId}/available")
    public void updateBookAvailability(@PathVariable("bookId") Long bookId, @RequestBody boolean available) {
        bookService.updateBookAvailability(bookId, available);
    }


}
