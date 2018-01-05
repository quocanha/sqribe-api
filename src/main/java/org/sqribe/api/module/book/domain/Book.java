package org.sqribe.api.module.book.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Book
{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Basic(optional = false)
    private String title;

    @Basic(optional = true)
    private String isbn;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chapter> chapters = new ArrayList<>();

    @ManyToMany(mappedBy = "books")
    private List<Author> authors;

    public Book() {}

    /**
     * Getters and setters.
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}