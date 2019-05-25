package org.sqribe.api.models;

import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

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

    @CreatedDate
    @Column
    private Date created;

    @UpdateTimestamp
    @Column
    private Date updated;
}
