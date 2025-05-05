package de.springBootExample.bookRentalService.rental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.springBootExample.bookRentalService.book.Book;
import de.springBootExample.bookRentalService.book.BookRepository;


/**
 * The RentalService class provides methods for managing book rentals.
 * It interacts with the RentalRepository to perform CRUD operations on rental data.
 * It also interacts with the BookRepository to update book availability status.
 */
@Service
public class RentalService {

    /**
     * The RentalRepository instance used to access rental data in the database.
     */
    private final RentalRepository rentalRepository;
    
    /**
     * The BookRepository instance used to access book data in the database.
     */
    private final BookRepository bookRepository;

    /**
     * Constructor for RentalService.
     *
     * @param rentalRepository the RentalRepository instance to be used
     */
    @Autowired
    public RentalService(RentalRepository rentalRepository, BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.rentalRepository = rentalRepository;
    }

    /**
     * This method retrieves all rentals from the database.
     *
     * @return a list of all rentals
     */
    public List<Rental> getRentals() {
        return rentalRepository.findAll();
    }

    /**
     * This method retrieves a rental by its book ID.
     *
     * @param bookId the ID of the book for which to retrieve the rental
     * @return the rental associated with the specified book ID
     */
    public Rental getRentalByBookId(Long bookId) {
        return rentalRepository.findByBookId(bookId).orElseThrow(
                () -> new IllegalStateException("Rental not found with book ID: " + bookId));
    }

    /**
     * This method creates a new rental for a book.
     * It also Update the book's availability status.
     * @param bookId      the ID of the book being rented
     * @param rentalTime  the rental time in days
     */
    public void rentBook(Long bookId, Long rentalTime) {

        // Check if the book is already rented
        Rental existingRental = rentalRepository.findByBookId(bookId).orElse(null);
        if (existingRental != null && existingRental.isRented()) {
            throw new IllegalStateException("The book is already rented.");
        }
        // Create a new rental
        Rental rental = new Rental();
        rental.setBookId(bookId);
        rental.setRentalTime(rentalTime);
        rental.setRented(true);
        rentalRepository.save(rental);

        // Update the book's availability status
        bookRepository.findById(bookId).ifPresent(book -> {
            book.setAvailable(false);
            bookRepository.save(book);
        });
    }

    /**
     * This method updates the rental status of a book.
     * It also Update the book's availability status.
     * @param bookId the ID of the book for which to update the rental
     */
    
    public String updateRental(Long bookId) {
        Rental rental = rentalRepository.findByBookId(bookId).orElseThrow(
                () -> new IllegalStateException("Rental not found with book ID: " + bookId));
        
        // Prüfen, ob die Ausleihe bereits zurückgegeben wurde
        if (!rental.isRented()) {
           
            return "The rental is already marked as returned.";
        }
    
        // Setze die Ausleihe auf "nicht vermietet"
        rental.setRented(false);
        rentalRepository.save(rental);
    
        // Update den Verfügbarkeitsstatus des Buches
        bookRepository.findById(bookId).ifPresent(book -> {
            book.setAvailable(true);
            bookRepository.save(book);
        });
    
        return "Rental updated successfully. The book is now available.";
    }


    /**
     * This method checks if a rental is expired based on the book ID.
     *
     * @param bookId the ID of the book for which to check the rental status
     * @return a message indicating whether the rental is expired or not
     */
    public String getExpiredRentalByBookId(Long bookId) {
        Rental rental = rentalRepository.findByBookId(bookId).orElseThrow(
                () -> new IllegalStateException("Rental not found with book ID: " + bookId));

        LocalDate rentalEndDate = rental.getRentalDate().plusDays(rental.getRentalTime());
        LocalDate currentDate = LocalDate.now();

        if (currentDate.isAfter(rentalEndDate)) {
            long daysOverdue = ChronoUnit.DAYS.between(rentalEndDate, currentDate);
            return "The rental is expired by " + daysOverdue + " days.";
        } else if (rental.isRented() == false) {
            int daysPassed = (int) ChronoUnit.DAYS.between(rentalEndDate, currentDate);
            return "The book was returned " + daysPassed + " days ago.";
        }else {
            return "The rental is still valid.";
        }
    }
}
