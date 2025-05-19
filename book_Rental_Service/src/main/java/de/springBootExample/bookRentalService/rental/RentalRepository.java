package de.springBootExample.bookRentalService.rental;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The RentalRepository interface provides methods for accessing rental data in the database.
 * It extends JpaRepository to leverage Spring Data JPA's capabilities.
 */
@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    /**
     * This method retrieves a rental by its book ID.
     *
     * @param bookId the ID of the book for which to retrieve the rental
     * @return an Optional containing the rental associated with the specified book ID, or empty if not found
     */
    Optional<Rental> findByBookId(Long bookId);

    /**
     * This method retrieves a rental by its user ID.
     *
     * @param userId the ID of the user for which to retrieve the rental
     * @return a list of rentals associated with the specified user ID
     */
    @Query("SELECT r FROM Rental r WHERE r.userId = ?1")
    List<Rental> findByUserId(Long userId);

}
