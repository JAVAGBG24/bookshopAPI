package com.java24.bookshop.models;

import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class Book {
    @Id
    private String id;
    @NotNull(message = "Title can not be null")
    @NotEmpty(message = "Title can no be empty")
    private String title;

    @Min(value = 20, message = "A book must have at least 20 pages")
    private Integer pages;

    @NotNull(message = "Description can not be null")
    @NotEmpty(message = "Description can no be empty")
    private String description;

    @DBRef
    @NotNull(message = "Author should not be null")
    private Author author;

    @DBRef
    private Author coAuthor;

    @PositiveOrZero(message = "Price can not be negative number")
    @Positive(message = "Price must be greater than 0")
    private Double priceExVat;

    @Pattern(
            regexp = "^\\d{9}[\\d|X]$|^\\d{13}$",
            message = "ISBN must be either 10 digits (with possible 'X' at end) or 13 digits"
    )
    private String isbn;

    @NotNull(message = "Book cover url can not be null")
    @NotEmpty(message = "Book cover url can no be empty")
    private String bookCoverUrl;

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getCoAuthor() {
        return coAuthor;
    }

    public void setCoAuthor(Author coAuthor) {
        this.coAuthor = coAuthor;
    }



    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookCoverUrl() {
        return bookCoverUrl;
    }

    public void setBookCoverUrl(String bookCoverUrl) {
        this.bookCoverUrl = bookCoverUrl;
    }

    public @Min(value = 20, message = "A book must have at least 20 pages") Integer getPages() {
        return pages;
    }

    public void setPages(@Min(value = 20, message = "A book must have at least 20 pages") Integer pages) {
        this.pages = pages;
    }

    public @PositiveOrZero(message = "Price can not be negative number") @Positive(message = "Price must be greater than 0") Double getPriceExVat() {
        return priceExVat;
    }

    public void setPriceExVat(@PositiveOrZero(message = "Price can not be negative number") @Positive(message = "Price must be greater than 0") Double priceExVat) {
        this.priceExVat = priceExVat;
    }
}
