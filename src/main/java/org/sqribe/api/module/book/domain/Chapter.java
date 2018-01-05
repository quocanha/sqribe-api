package org.sqribe.api.module.book.domain;

import javax.persistence.*;

@Entity
public class Chapter {

    @Id
    private long id;

    @Basic(optional = false)
    private String title;

    @Basic(optional = false)
    private int ordinal;

    @Basic(optional = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "BOOK_ID_FK"))
    private Book book;

    public Chapter() {}

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

    public int getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(int ordinal) {
        this.ordinal = ordinal;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
