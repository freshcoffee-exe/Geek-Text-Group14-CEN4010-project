package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.BookAvg;
import com.example.model.BookDetails;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> {

    @Query(value = "SELECT * FROM public.book_details WHERE isbn = :isbn", nativeQuery = true)
    List<BookDetails> findIsbn(@Param("isbn") long isbn);

    @Query(value = "SELECT * FROM public.book_details WHERE genre = :genre", nativeQuery = true)
    List<BookDetails> findGenre(@Param("genre") String genre);

    @Query(value = "SELECT * FROM public.book_details ORDER BY copies_sold DESC LIMIT 10", nativeQuery = true)
    List<BookDetails> findTop10();

    @Query(value = "SELECT * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY isbn) row_num, isbn, book_name, description, price, author, genre, publisher, year_published, copies_sold FROM public.book_details ) x WHERE row_num > :index - 1 AND row_num <= :index - 1 + :amount", nativeQuery = true)
    List<BookDetails> findBooksByPosition(@Param("index") int index, @Param("amount") int amount);

    @Query(value = "SELECT * FROM public.book_rating ", nativeQuery = true)
    List<BookDetails> findAverageRatingBooks();

}
