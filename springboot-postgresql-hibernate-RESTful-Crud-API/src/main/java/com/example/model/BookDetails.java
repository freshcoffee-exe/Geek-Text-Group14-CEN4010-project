package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "book_details")
public class BookDetails {

    private long isbn;
    private String book_name;
    private String description;
    private double price;
    private String author;
    private String genre;
    private String publisher;
    private int year_published;
    private int copies_sold;

    public BookDetails() {

    }

    public BookDetails(String book_name, String description, double price, String author, String genre, String publisher, int year_published, int copies_sold) {
        this.book_name = book_name;
        this.description = description;
        this.price = price;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.year_published = year_published;
        this.copies_sold = copies_sold;
    }

    public BookDetails(Long isbn, String book_name, String description, double price, String author, String genre, String publisher, int year_published, int copies_sold) {
        this.isbn = isbn;
        this.book_name = book_name;
        this.description = description;
        this.price = price;
        this.author = author;
        this.genre = genre;
        this.publisher = publisher;
        this.year_published = year_published;
        this.copies_sold = copies_sold;
    }

    @Id
    @Column(name = "isbn")
    public long getIsbn() {
        return isbn;
    }
    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    @Column(name = "book_name")
    public String getBook_name() {
        return book_name;
    }
    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price")
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    @Column(name = "author")
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "genre")
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Column(name = "publisher")
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Column(name = "year_published")
    public Integer getYear_published() {
        return year_published;
    }

    public void setYear_published(Integer year_published) {
        this.year_published = year_published;
    }

    @Column(name = "copies_sold")
    public Integer getCopies_sold() {
        return copies_sold;
    }

    public void setCopies_sold(Integer copies_sold) {
        this.copies_sold = copies_sold;
    }

    @Override
    public String toString() {
        return "BookDetails [isbn=" + isbn + ", book_name=" + book_name + "description, =" + description + "price, =" +
                price + "author, =" + author + "genre, =" + genre + "publisher, =" + publisher + "year_published, =" +
                year_published + "copies_sold, =" + copies_sold + "]";
    }
}
