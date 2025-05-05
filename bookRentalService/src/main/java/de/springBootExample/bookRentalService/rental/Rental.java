package de.springBootExample.bookRentalService.rental;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Column;

import java.time.LocalDate;

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
    private int rentalTime;

    private boolean isRented;

    /**
     * Default constructor for JPA.
     */
    public Rental() {
        // Default constructor for JPA
        id = 0L;
        bookId = 0L;
        rentalDate = LocalDate.now();
        rentalTime = 0;
        isRented = true;
    }

    /**
     * Constructor for creating a Rental object with specified parameters.
     *
     * @param bookId      the ID of the book being rented
     * @param userId      the ID of the user renting the book
     * @param rentalDate  the date when the book was rented
     * @param rentalTime  the rental time in days
     */
    public Rental(Long bookId, int rentalTime) {
        this.bookId = bookId;
        this.rentalTime = rentalTime;
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
        this.rentalDate = LocalDate.now();
    }

    public int getRentalTime() {
        return rentalTime;
    }

    public void setRentalTime(int rentalTime) {
        this.rentalTime = rentalTime;
    }

    /**
     * Checks if the book is currently rented.
     *
     * @return true if the book is rented, false otherwise
     */
    public boolean isRented() {
        return isRented;
    }

    /**
     * Sets the rental status of the book.
     *
     * @param rented true if the book is rented, false otherwise
     */
    public void setRented(boolean rented) {
        isRented = rented;
    }

    /**
     * Returns a string representation of the Rental object.
     *
     * @return a string representation of the Rental object
     */
    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", bookId=" + bookId +
                ", rentalDate='" + rentalDate + '\'' +
                ", rentalTime=" + rentalTime +
                '}';
    }

    /**
     * Compares this Rental object with another object for equality.
     *
     * @param o the object to compare with
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rental)) return false;

        Rental rental = (Rental) o;

        if (rentalTime != rental.rentalTime) return false;
        if (!id.equals(rental.id)) return false;
        if (!bookId.equals(rental.bookId)) return false;
        return rentalDate.equals(rental.rentalDate);
    }

    /**
     * Returns a hash code value for the Rental object.
     *
     * @return a hash code value for the Rental object
     */
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + bookId.hashCode();
        result = 31 * result + rentalDate.hashCode();
        result = 31 * result + rentalTime;
        return result;
    }


}
