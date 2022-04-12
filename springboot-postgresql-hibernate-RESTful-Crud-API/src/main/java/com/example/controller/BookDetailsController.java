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
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/")
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

    @GetMapping("/{isbn}")
    public List<BookDetails> getGenreBookDetails(@PathVariable long isbn){

            return this.bookDetailsRepository.findIsbn(isbn);
        }

    //Genre is case sensitive, the first letter must be uppercase!
    @GetMapping("/genre/{genre}")
    public List<BookDetails> getGenreBookDetails(@PathVariable String genre) {

        return this.bookDetailsRepository.findGenre(genre);
    }

    //Top 10
    @RequestMapping (path = "/topsellers", method = RequestMethod.GET)
    public List<BookDetails> getTopSellers() {
        List<BookDetails> allBooks = this.bookDetailsRepository.findTop10();

        return allBooks;
    }
    
	@GetMapping("/averageratings")
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

    @GetMapping("/averageratings/{avg}")
    public List<BookAvg> getAllParticularAvg(@PathVariable String avg){
        return jdbcTemplate.query("SELECT isbn,book_name, AVG(rating) AS avg_rating\n"
                + "FROM\n"
                + "(SELECT book_details.isbn, book_name, book_rating.rating FROM\n"
                + "book_details\n"
                + "FULL JOIN book_rating\n"
                + "ON book_details.isbn = book_rating.isbn) AS tmp\n"
                + "WHERE rating IS NOT NULL\n"
                + "GROUP BY isbn, book_name\n"
                + "HAVING AVG(rating) > " + avg, new BeanPropertyRowMapper<BookAvg>(BookAvg.class));
    }

    @GetMapping("/index/{index}/{amount}")
    public List<BookDetails> getXBooksFromX(@PathVariable int index, @PathVariable int amount) {
        List<BookDetails> booksByIndex = this.bookDetailsRepository.findBooksByPosition(index, amount);

        return booksByIndex;
    }

    @GetMapping("/index/{index}")
    public List<BookDetails> getXBooksFromX(@PathVariable int index) {
        List<BookDetails> booksByIndex = this.bookDetailsRepository.findBooksByPosition(index, 20);

        return booksByIndex;
    }

    @PostMapping("/createbook") //creates book
    public void createBook(@RequestBody BookDetails book) {

        this.bookDetailsRepository.save(book);
        System.out.println(book);
    }

}
