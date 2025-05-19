package de.springBootExample.bookRentalService.rental;


/**
 * This class helps to deserialize JSON data into a Java object and serialize Java objects into JSON.
 * It is used to transfer data between the client and server.
 */
public class BookDto {


    private Long id;

    private String title;

    private String author;

    private String isbn;

    private boolean available;

    public BookDto() {
    }


    public BookDto(Long id, String title, String author, String isbn, boolean available) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
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


    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", available=" + available +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookDto)) return false;

        BookDto bookDto = (BookDto) o;

        if (available != bookDto.available) return false;
        if (!id.equals(bookDto.id)) return false;
        if (!title.equals(bookDto.title)) return false;
        if (!author.equals(bookDto.author)) return false;
        return isbn.equals(bookDto.isbn);
    }

    
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