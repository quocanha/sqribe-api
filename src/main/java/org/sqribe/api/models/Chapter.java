package org.sqribe.api.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Chapter {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, unique=true)
    private String title;

    @Column
    private String content;

    public Chapter() {}

    public Chapter(String title) {
        this.title = title;
    }
}
