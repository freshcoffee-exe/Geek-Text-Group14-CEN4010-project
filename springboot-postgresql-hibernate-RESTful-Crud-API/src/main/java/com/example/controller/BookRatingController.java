package com.example.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.exception.ResourceNotFoundException;
import com.example.model.BookRating;
import com.example.model.BookAvg;
import com.example.model.BookDetails;
import com.example.repository.BookRatingRepository;


@RestController
@RequestMapping("/api/")
public class BookRatingController {

	@Autowired
	private BookRatingRepository bookRatingRepository;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@GetMapping("/bookrating")
	@RequestMapping(path = "/bookrating", method = RequestMethod.GET)
	public List<BookRating> getALLBookRating(){
		return this.bookRatingRepository.findAll();
	}

	//Query the ratings by the input value, also any higher values
	@GetMapping("/querybyrating/{rating}")
	public List<BookRating> getRatingsAndHigher(@PathVariable int rating) {
		return this.bookRatingRepository.findRatingOrHigher(rating);
	}
	
	//get book ratings by isbn
	@GetMapping("/bookrating/{isbn}")
	public ResponseEntity<List<BookRating>> getALLByID(@PathVariable(value = "isbn")Long isbn){
		return new ResponseEntity<List<BookRating>>(bookRatingRepository.findByIsbn(isbn), HttpStatus.OK);
	}

	// save rating
	@PostMapping("/bookrating")
	public BookRating createBookRating(@RequestBody BookRating bookrating) {
		return this.bookRatingRepository.save(bookrating);
	}
	
	//finds alls the ratings sorted by highes rating first
	@GetMapping("/findrating")
	public List<BookRating> getAll(){
		return jdbcTemplate.query("SELECT * FROM book_rating ORDER BY rating desc", new BeanPropertyRowMapper<BookRating>(BookRating.class));
	}
	
	//finds the ratings of a particular isbn and sorted by highest rating first
	@GetMapping("/findrating/{isbn}")
	public List<BookRating> getAllByIsbn(@PathVariable("isbn") Long isbn){
		return jdbcTemplate.query("SELECT * FROM book_rating WHERE isbn =" + isbn + " ORDER BY rating desc", new BeanPropertyRowMapper<BookRating>(BookRating.class));
	}
	
	
	//pulls up the average rating by isbn 
	@GetMapping("/findavgrating/{isbn}")
	public List<BookAvg> getAllAverageByID(@PathVariable("isbn") Long isbn){
		return jdbcTemplate.query("SELECT isbn,book_name, AVG(rating) AS avg_rating\r\n"
				+ "FROM\r\n"
				+ "(SELECT book_details.isbn, book_name, book_rating.rating FROM\r\n"
				+ "book_details\r\n"
				+ "FULL JOIN book_rating\r\n"
				+ "ON book_details.isbn = book_rating.isbn) AS tmp\r\n"
				+ "WHERE rating IS NOT NULL AND ISBN = " + isbn + " GROUP BY isbn, book_name", new BeanPropertyRowMapper<BookAvg>(BookAvg.class));
	}
}