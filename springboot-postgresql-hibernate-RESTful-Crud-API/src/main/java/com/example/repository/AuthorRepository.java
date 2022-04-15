package com.example.repository;

import com.example.model.Author;
import com.example.model.BookDetails;
import com.example.model.BookRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {

    @Query(value = "SELECT * FROM public.author WHERE first_name = :author", nativeQuery = true)
    List<Author> findAuthor(@Param("author") String author);
}
