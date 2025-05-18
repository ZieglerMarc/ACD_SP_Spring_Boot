package de.springBootExample.bookRentalService.rental;

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

}
