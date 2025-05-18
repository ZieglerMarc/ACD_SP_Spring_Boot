package de.springBootExample.bookRentalService.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The BookService class provides methods to manage books in the book rental service.
 * It interacts with the BookRepository to perform CRUD operations on books.
 */
// The @Service annotation indicates that this class is a service component in the Spring application.
@Service
public class BookService {


    /**
     * The BookRepository instance used to access book data in the database.
     */
    private final BookRepository bookRepository;


    /**
     * Constructor for BookService.
     *
     * @param bookRepository the BookRepository instance to be used
     */
    // The @Autowired annotation is used to inject the BookRepository dependency
    // into the BookService. This allows Spring to manage the lifecycle of the BookRepository.
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    
    /**
     * This method retrieves all books from the database.
     *
     * @return a list of all books
     */
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    /**
     * This method retrieves a book by its ID.
     *
     * @param id the ID of the book to retrieve
     * @return the book with the specified ID
     */
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Book not found with id: " + id));
    }

    /**
     * This method retrieves a book by its title.
     *
     * @param title the title of the book to retrieve
     * @return the book with the specified title
     */
    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title).orElseThrow(
                () -> new IllegalStateException("Books not found with title: " + title));
    }


    /**
     * This method retrieves all books that are available.
     */
    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailable(true);
    }


    /**
     * This method adds a new book to the database.
     * @param book the book to add
     * @throws IllegalStateException if a book with the same title already exists
     */
    public void addBook(Book book) {
        bookRepository.save(book);
    }


    /**
     * This method updates an existing book in the database.
     * @param id the ID of the book to update
     * @param book the updated book information
     * @throws IllegalStateException if the book with the specified ID does not exist
     */
    public void updateBook(Long id, Book book) {
        Book newBook = bookRepository.findById(id).orElseThrow(
                () -> new IllegalStateException("Book not found with id: " + id));

        if (book.getTitle() != null && !book.getTitle().isEmpty()) {
            newBook.setTitle(book.getTitle());
        }
        if (book.getAuthor() != null && !book.getAuthor().isEmpty()) {
            newBook.setAuthor(book.getAuthor());
        }
        if (book.getIsbn() != null && !book.getIsbn().isEmpty()) {
            newBook.setIsbn(book.getIsbn());
        }
        if (book.isAvailable()) {
            newBook.setAvailable(book.isAvailable());
        }
        bookRepository.save(newBook);
    }

    /**
     * This method deletes a book by its ID.
     * @param id the ID of the book to delete
     * @throws IllegalStateException if the book with the specified ID does not exist
     */
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new IllegalStateException("Book with id " + id + " does not exist");
        }
        bookRepository.deleteById(id);
    }

}
