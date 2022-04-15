package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Author")
public class Author {

    private int authorID;
    private String first_name;
    private String last_name;
    private String biography;
    private String publisher;


    public Author() {

    }

    public Author(int authorID, String first_name, String last_name, String biography, String publisher) {
        this.authorID = authorID;
        this.first_name = first_name;
        this.last_name = last_name;
        this.biography = biography;
        this.publisher = publisher;

    }

    @Id
    @Column(name = "authorID")
    public int getAuthorID() {
        return authorID;
    }
    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    @Column(name = "first_name")
    public String getFirst_name() {
        return first_name;
    }
    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    @Column(name = "last_name")
    public String getLast_name() {
        return last_name;
    }
    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    @Column(name = "biography")
    public String getBiography() {
        return biography;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Column(name = "publisher")
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorID=" + authorID +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", biography='" + biography + '\'' +
                ", publisher='" + publisher + '\'' +
                '}';
    }
}