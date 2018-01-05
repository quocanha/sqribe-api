package org.sqribe.api.module.book.domain;

import org.sqribe.api.module.common.domain.Name;
import org.sqribe.api.module.common.domain.Person;

import javax.persistence.*;
import java.util.List;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Name pseudonym;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Book> books;

    public Author() {}

    /**
     * Getters and setters.
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Name getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(Name pseudonym) {
        this.pseudonym = pseudonym;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
