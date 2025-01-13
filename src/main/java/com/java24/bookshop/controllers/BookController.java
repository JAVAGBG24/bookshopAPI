package com.java24.bookshop.controllers;

import com.java24.bookshop.models.Author;
import com.java24.bookshop.models.Book;
import com.java24.bookshop.repositories.AuthorRepository;
import com.java24.bookshop.repositories.BookRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    // fixa dbref nästa gång
    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        // om author fylls i - kolla att den finns i db
        if(book.getAuthor() != null && !authorRepository.existsById(book.getAuthor().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author not found");
        }

        // om coAuthor fylls i - kolla att den finns i db
        if(book.getCoAuthor() != null && !authorRepository.existsById(book.getCoAuthor().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CoAuthor not found");
        }

        Book savedBook = bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable String id, @Valid @RequestBody Book book) {
        if(!bookRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found.");
        }

        if(book.getAuthor() != null) {
            Author author = authorRepository.findById(book.getAuthor().getId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "Author not found"
                    ));
            book.setAuthor(author);
        }

        if(book.getCoAuthor() != null) {
            Author coAuthor = authorRepository.findById(book.getCoAuthor().getId())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "CoAuthor not found"
                    ));
            book.setCoAuthor(coAuthor);
        }

        Book updatedBook = bookRepository.save(book);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        if(!bookRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
        bookRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }













}




















































