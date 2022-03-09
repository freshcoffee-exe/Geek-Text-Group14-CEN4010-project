package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.BookRating;

import java.util.List;


@Repository
public interface BookRatingRepository extends JpaRepository<BookRating, Long> {

    @Query(value = "SELECT * FROM public.book_rating WHERE rating >= :rating", nativeQuery = true)
    List<BookRating> findRatingOrHigher(@Param("rating") int rating);
}
