package de.springBootExample.bookRentalService.rental;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The Rental class represents a rental entity in the book rental service.
 * It contains information about the rental, including the book ID, rental date, and rental time.
 */
@Entity
@Table(name = "rentals")
public class Rental {
    /**
     * The unique identifier for the rental.
     */
    @Id
    @SequenceGenerator(name = "rental_sequence", sequenceName = "rental_sequence", allocationSize = 1)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "rental_sequence")
    private Long id;

    /**
     * The ID of the book being rented.
     */
    @Column(name = "book_id")
    private Long bookId;


    /**
     * The ID of the user who rented the book.
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * The date when the book was rented.
     * This field is automatically set to the current date when the rental is created.
     * The date is stored in the database in the format YYYY-MM-DD.
     * 
     */
    @Column(name = "rental_date")
    private LocalDate rentalDate;
  
    /**
     * The rental time in days.
     * This field indicates the duration of the rental period.
     */
    @Column(name = "rental_time")
    private Long rentalDuration;

    /**
     * Indicates whether the book is currently rented or not.
     * This field is set to true when the book is rented and false when it is returned.
     */
    @Column(name = "is_rented")
    private boolean isRented;

    /**
     * Default constructor for JPA.
     */
    public Rental() {}

    /**
     * Constructor for creating a Rental object with specified parameters.
     *
     * @param bookId      the ID of the book being rented
     * @param userId      the ID of the user renting the book
     * @param rentalDuration  the rental time in days
     */
    public Rental(Long bookId, Long rentalDuration, Long userId) {
        this.bookId = bookId;
        this.userId = userId;
        this.rentalDuration = rentalDuration;
        this.rentalDate = LocalDate.now();
        this.isRented = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Long getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Long rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Rental{" +
            "id=" + id +
            ", bookId=" + bookId +
            ", userId=" + userId +
            ", rentalDate=" + rentalDate +
            ", rentalDuration=" + rentalDuration +
            ", isRented=" + isRented +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rental)) return false;
        Rental rental = (Rental) o;
        return Objects.equals(id, rental.id) &&
            Objects.equals(bookId, rental.bookId) &&
            Objects.equals(userId, rental.userId) &&
            Objects.equals(rentalDate, rental.rentalDate) &&
            Objects.equals(rentalDuration, rental.rentalDuration);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, bookId, userId, rentalDate, rentalDuration);
    }


}
