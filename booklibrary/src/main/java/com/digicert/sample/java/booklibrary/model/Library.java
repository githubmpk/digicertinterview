package com.digicert.sample.java.booklibrary.model;

import javax.persistence.*;

@Entity
@Table(name="books")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="publisher")
    private String publisher;

    @Column(name="issued")
    private boolean issued;

    public Library() {

    }

    public Library(String title, String description, String publisher, boolean issued) {
        this.title = title;
        this.description = description;
        this.publisher = publisher;
        this.issued = issued;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public boolean isIssued() {
        return issued;
    }

    public void setIssued(boolean issued) {
        this.issued = issued;
    }

    @Override
    public String toString() {
        return "Library{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", publisher='" + publisher + '\'' +
                ", issued=" + issued +
                '}';
    }
}
