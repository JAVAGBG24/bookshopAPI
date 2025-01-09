package com.java24.bookshop.repositories;

import com.java24.bookshop.models.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
