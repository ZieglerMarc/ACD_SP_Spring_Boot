package de.springBootExample.bookRentalService.rental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * The WebClient instance used to interact with the BookService.
     */
    private final WebClient webClient;
    
    /**
     * Constructor for RentalService.
     *
     * @param rentalRepository the RentalRepository instance to be used
     */
    @Autowired
    public RentalService(RentalRepository rentalRepository, WebClient.Builder webClientBuilder) {
        this.rentalRepository = rentalRepository;
        this.webClient = webClientBuilder.baseUrl("http://bookservice:8080/api/books").build();
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
     * This method retrieves a rental by its ID.
     *
     * @param rentalId the ID of the rental to retrieve
     * @return the rental with the specified ID
     */
    public Rental getRentalById(Long rentalId) {
        return rentalRepository.findById(rentalId).orElseThrow(
                () -> new IllegalStateException("Rental not found with ID: " + rentalId));
    }

    /**
     * This method retrieves a rentals-list given by user ID.
     *
     * @param userId the ID of the user for whom to retrieve rentals
     * @return a list of rentals associated with the specified user ID
     */
    public List<Rental> getRentalsByUserId(Long userId) {
        return rentalRepository.findByUserId(userId);
    }


    /**
     * This method creates a new rental for a book.
     * It also Update the book's availability status.
     * @param rental the Rental object to be created
     * @throws IllegalStateException if the book is already rented
     */
    public void rentBook(Rental rental) {
        Optional<Rental> existingRental = rentalRepository.findByBookId(rental.getBookId());
        if (existingRental.isPresent() && existingRental.get().isRented()) {
            throw new IllegalStateException("The book is already rented.");
        }

        // Set the rental date to the current date
        rental.setRentalDate(LocalDate.now());
        rental.setRented(true);

        // Save the rental to the database
        rentalRepository.save(rental);

        // Update the book's availability in the book_Service via WebClient
        updateBookAvailability(rental.getBookId(), false);
    }


    /**
     * This method updates the rental status of a book.
     * It also Update the book's availability status.
     * @param bookId the ID of the book for which to update the rental
     */
    
    public String returnBook(Long bookId) {
        Rental rental = rentalRepository.findByBookId(bookId).orElseThrow(
            () -> new IllegalStateException("Rental not found with book ID: " + bookId));

        if (!rental.isRented()) {
            return "The rental is already marked as returned.";
        }

        rental.setRented(false);
        rentalRepository.save(rental);

        // Update the book's availability in the book_Service via WebClient
        updateBookAvailability(bookId, true);

        return "Rental updated successfully. The book is now available.";
    }

    /**
     * This method retrieves a Book from the BookService by its ID.
     * It uses WebClient to make a GET request to the BookService.
     * @param bookId
     * @return
     */
    public BookDto getBookById(Long bookId) {
        return webClient.get()
                .uri("/{bookId}", bookId)
                .retrieve()
                .bodyToMono(BookDto.class)
                .block(); 
    }

    /**
     * This method updates the availability status of a book in the BookService.
     * It uses WebClient to make a PATCH request to the BookService.
     * @param bookId
     * @param available
     */
    public void updateBookAvailability(Long bookId, boolean available) {
        try {
            webClient.patch()
                .uri("/{bookId}/available", bookId)
                .bodyValue(available)
                .retrieve()
                .toBodilessEntity()
                .block();
        } catch (WebClientResponseException ex) {
            // Log and handle error, maybe throw a custom exception
            throw new IllegalStateException("Failed to update book availability: " + ex.getMessage());
        }
    }






    
}
