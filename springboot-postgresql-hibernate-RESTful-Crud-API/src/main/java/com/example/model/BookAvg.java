package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "book_details")
public class BookAvg {

    private long isbn;
    private String book_name;
    private double avg_rating;

    public BookAvg() {

    }

    public BookAvg(String book_name, double avg_rating) {
        this.book_name = book_name;
       
        this.avg_rating = avg_rating;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
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

   

    @Column(name = "avg_rating")
    public Double getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(Double avg_rating) {
        this.avg_rating = avg_rating;
    }

    @Override
    public String toString() {
        return "Bookavg_rating [isbn=" + isbn + ", book_name=" + book_name + "avg_rating, =" + avg_rating + "]";
    }
}
