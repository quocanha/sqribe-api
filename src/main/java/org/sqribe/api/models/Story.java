package org.sqribe.api.models;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Table
public class Story {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = true)
    private String description;

    @OneToMany
    private Collection<Chapter> chapters = new ArrayList<Chapter>();

    public Story() {}

    public Story(String title) {
        this.title = title;
    }

    @ManyToOne
    private User owner;

    @CreatedDate
    @Column
    private Date created;

    @UpdateTimestamp
    @Column
    private Date updated;
}
