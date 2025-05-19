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

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;


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
     * This method handles GET requests to retrieve a rental by its ID.
     *
     * @param rentalId the ID of the rental to retrieve
     * @return the rental with the specified ID
     */
    @GetMapping(path = "/rental/{rentalId}")
    public Rental getRentalById(@PathVariable("rentalId") Long rentalId) {
        return rentalService.getRentalById(rentalId);
    }


    /**
     * This method handles GET requests to retrieve a rental by its book ID.
     *
     * @param bookId the ID of the book for which to retrieve the rental
     * @return the rental associated with the specified book ID
     */
    @GetMapping(path = "/book/{bookId}")
    public Rental getRentalByBookId(@PathVariable("bookId") Long bookId) {
        return rentalService.getRentalByBookId(bookId);
    }


    /**
     * This method handles GET requests to retrieve rentals by user ID.
     *
     * @param userId the ID of the user for whom to retrieve rentals
     * @return a list of rentals associated with the specified user ID
     */
    @GetMapping(path = "/user/{userId}")
    public List<Rental> getRentalsByUserId(@PathVariable("userId") Long userId) {
        return rentalService.getRentalsByUserId(userId);
    }


    /**
     * This method handles POST requests to create a new rental.
     *
     * @param rental the rental to create
     * @return the created rental
     */
    @PostMapping
    public ResponseEntity<Rental> rentBook(@RequestBody Rental rental) {
        Rental created = rentalService.rentBook(rental);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);;
    }

    /**
     * This method handles exceptions related to illegal state.
     * If you try to rent a book that is already rented.
     * It returns a 409 Conflict status code and the error message.
     *
     * @param ex the exception to handle
     * @return a message indicating the error
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleIllegalStateException(IllegalStateException ex) {
        return ex.getMessage();
    }

    /**
     * This method handles PATCH requests to update a rental.
     *
     * @param bookId the ID of the book whose rental is to be updated
     */
    @PatchMapping(path = "/book/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable("bookId") Long bookId) {
        String resultMessage = rentalService.returnBook(bookId);
        return ResponseEntity.ok(resultMessage);
    }

}
