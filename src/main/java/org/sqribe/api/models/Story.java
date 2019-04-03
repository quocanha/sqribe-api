package org.sqribe.api.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@Table
public class Story {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @OneToMany
    private Collection<Chapter> chapters = new ArrayList<Chapter>();

    public Story() {}

    public Story(String title) {
        this.title = title;
    }

    @ManyToOne
    private User owner;
}
