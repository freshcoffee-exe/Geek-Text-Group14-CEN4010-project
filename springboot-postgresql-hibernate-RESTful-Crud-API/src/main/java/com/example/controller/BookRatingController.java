package com.example.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import com.example.exception.ResourceNotFoundException;
import com.example.model.BookRating;
import com.example.repository.BookRatingRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class BookRatingController {

	
	
	@Autowired
	private BookRatingRepository bookRatingRepository;
	
	
	//@GetMapping("/bookrating")
	@RequestMapping(path = "/bookrating", method = RequestMethod.GET)
	public List<BookRating> getALLBookRating(){
		return this.bookRatingRepository.findAll();
	}

	//Query the ratings by the input value, also any higher values
	@GetMapping("/querybyrating/{rating}")
	public List<BookRating> getRatingsAndHigher(@PathVariable int rating) {
		return this.bookRatingRepository.findRatingOrHigher(rating);
	}
	/*
	@RequestMapping(path = "/helloAPI", method = RequestMethod.GET)
	public String getGreeting() {
		return "Hello World, this is a test using RestAPI on Java Spring Boot";
	}
	
	// get book ratings api
	
	@GetMapping("bookrating")
	public List<BookRating> getALLBookRating(){
		return this.bookRatingRepository.findAll();
	}
	
	
	
	// get book ratings by isbn
	@GetMapping("/bookrating/{isbn}")
		public ResponseEntity<BookRating> getBookRatingByIsbn(@PathVariable(value = "isbn") Long isbn)
			throws ResourceNotFoundException{
		BookRating bookrating = bookRatingRepository.findById(isbn)
				.orElseThrow(() -> new ResourceNotFoundException("ISBN not found for :: " + isbn));
		return ResponseEntity.ok().body(bookrating);
	}
	
	// save rating
	@PostMapping("bookrating")
	public BookRating createBookRating(@RequestBody BookRating bookrating) {
		return this.bookRatingRepository.save(bookrating);
	}
	
	// update rating
	@PutMapping("bookRating/{isbn}")
	public ResponseEntity<BookRating> updateBookRatingByIsbn(@PathVariable(value = "isbn") Long isbn,
			@Valid @RequestBody BookRating bookRatingDetails) throws ResourceNotFoundException{
		
		BookRating bookrating = bookRatingRepository.findById(isbn)
				.orElseThrow(() -> new ResourceNotFoundException("ISBN not found for :: " + isbn));
		bookrating.setEmail(bookRatingDetails.getEmail());
		bookrating.setRating(bookRatingDetails.getRating());
		bookrating.setDatestamp(bookRatingDetails.getDatestamp());
		bookrating.setRating_comment(bookRatingDetails.getRating_comment());
		
		return ResponseEntity.ok(this.bookRatingRepository.save(bookrating));
		
		
	}
	// delete rating
	
	*/
}
