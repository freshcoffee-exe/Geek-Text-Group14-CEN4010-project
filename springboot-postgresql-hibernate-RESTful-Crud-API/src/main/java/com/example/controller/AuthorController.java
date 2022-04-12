package com.example.controller;


import com.example.model.Author;
import com.example.model.BookDetails;
import com.example.repository.AuthorRepository;
import com.example.repository.BookDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookDetailsRepository bookDetailsRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping (path = "/bookdetails/getallauthors", method = RequestMethod.GET)
    public List<Author> getALLAuthors() {
        return this.authorRepository.findAll();
}

    @GetMapping("/{author}")
    public List<Author> getAuthor(@PathVariable String author){

        return this.authorRepository.findAuthor(author);
    }

    @GetMapping("/allbooks/{author}")
    public List<BookDetails> getAuthorBooks(@PathVariable String author) {

        List<BookDetails> booksOfThatAuthor = new ArrayList<>();

        List<BookDetails> allBooks = this.bookDetailsRepository.findAll();

        Iterator<BookDetails> iterator = allBooks.iterator();

        while (iterator.hasNext()) {

            BookDetails currentBook = iterator.next();

            if(currentBook.getAuthor().equals(author)) {
                booksOfThatAuthor.add(currentBook);
            }
        }

        return booksOfThatAuthor;
    }

    /*//Author is case sensitive, the first letter must be uppercase!
    @GetMapping("/bookdetails/{author}")
    public List<Author> getGenreAuthor(@PathVariable String author) {

        List<Author> booksOfThatAuthor = new ArrayList<>();

        List<Author> allBooks = this.authorRepository.findAll();

        Iterator<Author> iterator = allBooks.iterator();

        while (iterator.hasNext()) {

            Author currentBook = iterator.next();

            if(currentBook.getFirst_name().equals(author)) {
                booksOfThatAuthor.add(currentBook);
            }
        }

        return booksOfThatAuthor;
    }*/

    @PostMapping("/createauthor") //creates author
    public void createAuthor(@RequestBody Author author) {

        this.authorRepository.save(author);
        System.out.println(author);
    }
}