package de.springBootExample.bookRentalService.book;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;



// The @Entity annotation indicates that this class is an entity and is mapped to a database table.
@Entity
@Table(name = "books")
public class Book {
        
    // The @Id annotation specifies the primary key of the entity.
    // The @SequenceGenerator annotation is used to define a sequence generator for the primary key.
    // The @GeneratedValue annotation specifies that the primary key value will be generated automatically.
    @Id
    @SequenceGenerator(name = "book_sequence", sequenceName = "book_sequence", allocationSize = 1)
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.SEQUENCE, generator = "book_sequence")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "available")
    private boolean available;

    public Book() {
        // Default constructor for JPA
        id = 0L;
        title = "";
        author = "";
        isbn = "";
        available = true;
    }

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    // Override toString method for better readability when printing book objects
    // This is useful for debugging and logging purposes.
    // It provides a string representation of the book object, including its id, title, author, isbn, and availability status.
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", available=" + available +
                '}';
    }

    // Override equals and hashCode methods for proper comparison
    // This is important for collections and when using the book as a key in maps
    // or when checking for equality in tests.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;

        if (available != book.available) return false;
        if (!id.equals(book.id)) return false;
        if (!title.equals(book.title)) return false;
        if (!author.equals(book.author)) return false;
        return isbn.equals(book.isbn);
    }

    // Override hashCode to ensure that two equal objects have the same hash code
    // This is important for collections and when using the book as a key in maps
    // or when checking for equality in tests.
    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + isbn.hashCode();
        result = 31 * result + (available ? 1 : 0);
        return result;
    }
}
