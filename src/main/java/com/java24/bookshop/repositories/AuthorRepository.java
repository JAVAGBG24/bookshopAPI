package com.java24.bookshop.repositories;

import com.java24.bookshop.models.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
