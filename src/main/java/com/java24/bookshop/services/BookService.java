package com.java24.bookshop.services;

import com.java24.bookshop.models.Author;
import com.java24.bookshop.models.Book;
import com.java24.bookshop.repositories.AuthorRepository;
import com.java24.bookshop.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book createBook(Book book) {

        Author mainAuthor = getAuthorAndValidate(book.getAuthor(), "Main Author");

        book.setAuthor(mainAuthor);


        if (book.getCoAuthor() != null) {

            Author coAuthor = getAuthorAndValidate(book.getCoAuthor(), "Co Author");
            book.setCoAuthor(coAuthor);
            validateAuthorCombination(mainAuthor, coAuthor);
        }

        return bookRepository.save(book);

        // om author fylls i - kolla att den finns i db
       /* if(book.getAuthor() != null && !authorRepository.existsById(book.getAuthor().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author not found");
        }*/

        // om coAuthor fylls i - kolla att den finns i db
       /* if(book.getCoAuthor() != null && !authorRepository.existsById(book.getCoAuthor().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CoAuthor not found");
        }*/


    }

    private Author getAuthorAndValidate(Author author, String authorType) {
        // första kolla om id är tomt eller null
        if(author == null || author.getId() == null) {
            throw new IllegalArgumentException(authorType + " ID cannot be null");
        }

        // kolla i collection om author finns, kasta fel om ej finns
        return authorRepository.findById(author.getId())
                .orElseThrow(() -> new IllegalArgumentException(
                        authorType + " not found!"
                ));
    }

    private void validateAuthorCombination(Author mainAuthor, Author coAuthor) {
        if (mainAuthor != null && coAuthor.getId().equals(mainAuthor.getId())) {
            throw new IllegalArgumentException("Main author can not be same as coAuthor");
        }
    }

































}















