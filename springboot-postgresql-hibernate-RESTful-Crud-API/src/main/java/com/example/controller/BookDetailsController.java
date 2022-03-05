package com.example.controller;

import com.example.exception.ResourceNotFoundException;
import com.example.model.BookDetails;
import com.example.repository.BookDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class BookDetailsController {

    @Autowired
    private BookDetailsRepository bookDetailsRepository;

    @RequestMapping (path = "/bookdetails", method = RequestMethod.GET)
    public List<BookDetails> getALLBookDetails() {
        return this.bookDetailsRepository.findAll();
    }

    //Genre is case sensitive, the first letter must be uppercase!
    @GetMapping("/bookdetails/{genre}")
    public List<BookDetails> getGenreBookDetails(@PathVariable String genre) {

        List<BookDetails> booksOfThatGenre = new ArrayList<>();

        List<BookDetails> allBooks = this.bookDetailsRepository.findAll();

        Iterator<BookDetails> iterator = allBooks.iterator();

        while (iterator.hasNext()) {

            BookDetails currentBook = iterator.next();

            if(currentBook.getGenre().equals(genre)) {
                booksOfThatGenre.add(currentBook);
            }
        }

        return booksOfThatGenre;
    }

//    @RequestMapping (path = "/genre", method = RequestMethod.GET)
//    public List<BookDetails> getALLBookDetails() {
//        return this.bookDetailsRepository.findAll();
//    }
}
