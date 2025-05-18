package de.springBootExample.bookRentalService.rental;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The RentalController class handles HTTP requests related to rentals.
 * It provides endpoints for managing book rentals.
 */
@RestController
@RequestMapping(path = "/api/rentals")
public class RentalController {

    // The RentalService instance used to handle rental-related operations.
    private final RentalService rentalService;

    /**
     * Constructor for RentalController.
     *
     * @param rentalService the RentalService instance to be used
     */
    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    /**
     * This method handles GET requests to retrieve a rental by its ID.
     *
     * @param rentalId the ID of the rental to retrieve
     * @return the rental with the specified ID
     */
    @GetMapping
    public List<Rental> getAllRentals() {
        return rentalService.getRentals();
    }

    /**
     * This method handles GET requests to retrieve a rental by its book ID.
     *
     * @param bookId the ID of the book for which to retrieve the rental
     * @return the rental associated with the specified book ID
     */
    @GetMapping(path = "/{bookId}")
    public Rental getRentalByBookId(@PathVariable("bookId") Long bookId) {
        return rentalService.getRentalByBookId(bookId);
    }


    /**
     * This method handles GET requests to retrieve expired rentals by book ID.
     *
     * @param bookId the ID of the book for which to retrieve expired rentals
     * @return a message indicating whether the rental is expired or not
     */
    @GetMapping(path = "/expired/{bookId}")
    public ResponseEntity<String> getExpiredRentalByBookId(@PathVariable("bookId") Long bookId) {
        String resultMessage = rentalService.getExpiredRentalByBookId(bookId);
        return ResponseEntity.ok(resultMessage);
    }

    /**
     * This method handles POST requests to create a new rental.
     *
     * @param bookId      the ID of the book being rented
     * @param rentalTime  the rental time in days
     */
    @PostMapping
    public void rentBook(@RequestBody Rental rental) {
        rentalService.rentBook(rental.getBookId(), rental.getRentalTime());
    }

    /**
     * This method handles PATCH requests to update a rental.
     *
     * @param bookId the ID of the book for which to update the rental
     */
    @PatchMapping(path = "/{bookId}")
    public ResponseEntity<String> updateRental(@PathVariable("bookId") Long bookId) {
        String resultMessage = rentalService.updateRental(bookId);
        return ResponseEntity.ok(resultMessage);
    }

}
