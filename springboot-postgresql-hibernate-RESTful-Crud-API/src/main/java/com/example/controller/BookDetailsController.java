package com.example.controller;

import com.example.exception.ResourceNotFoundException;
import com.example.model.BookAvg;
import com.example.model.BookDetails;
import com.example.model.BookRating;
import com.example.repository.BookDetailsRepository;
import com.example.repository.BookRatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class BookDetailsController {

    @Autowired
    private BookDetailsRepository bookDetailsRepository;


    @Autowired
    private BookRatingRepository bookRatingRepository;

    @GetMapping("/{isbn}")
    public List<BookDetails> getGenreBookDetails(@PathVariable long isbn) {

        return this.bookDetailsRepository.findIsbn(isbn);
    }


	@Autowired
	JdbcTemplate jdbcTemplate;
	

    @RequestMapping (path = "/bookdetails", method = RequestMethod.GET)
    public List<BookDetails> getALLBookDetails() {
        return this.bookDetailsRepository.findAll();
    }

    //Genre is case sensitive, the first letter must be uppercase!
    @GetMapping("/genre/{genre}")
    public List<BookDetails> getGenreBookDetails(@PathVariable String genre) {

        return this.bookDetailsRepository.findGenre(genre);
    }

    @GetMapping("/averagerating")
    public List<BookDetails> getAverageRatingBooks() {

        return this.bookDetailsRepository.findAverageRatingBooks();
    }

    //Top 10
    @RequestMapping (path = "/topsellers", method = RequestMethod.GET)
    public List<BookDetails> getTopSellers() {
        List<BookDetails> allBooks = this.bookDetailsRepository.findTop10();

        return allBooks;
    }
    
	@GetMapping("/test1")
	public List<BookAvg> getAll(){
		return jdbcTemplate.query("SELECT isbn,book_name, AVG(rating) AS avg_rating\n"
				+ "FROM\n"
				+ "(SELECT book_details.isbn, book_name, book_rating.rating FROM\n"
				+ "book_details\n"
				+ "FULL JOIN book_rating\n"
				+ "ON book_details.isbn = book_rating.isbn) AS tmp\n"
				+ "WHERE rating IS NOT NULL\n"
				+ "GROUP BY isbn, book_name\n"
				+ "HAVING AVG(rating) > 1", new BeanPropertyRowMapper<BookAvg>(BookAvg.class));
	}

}
