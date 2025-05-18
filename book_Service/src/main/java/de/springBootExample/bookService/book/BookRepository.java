package de.springBootExample.bookService.book;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing the Book data in the database.
 * This interface extends {@link JpaRepository} and provides custom queries
 * for retrieving {@link Book} entities.
 *
 * It includes methods to find an Book by name and to find all Books by role ID.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    
    // This methods will return an Optional<Book> object, which may or may not contain a Book object
    @Query("SELECT b FROM Book b WHERE b.title = ?1")
    List<Book> findByTitle(String title);

    // This method will return a list of books that are available or not available based on the boolean parameter
    // The ?1 refers to the first parameter of the method
    @Query("SELECT b FROM Book b WHERE b.available = ?1")
    List<Book> findByAvailable(boolean available);
}
