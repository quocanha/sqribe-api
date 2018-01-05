package org.sqribe.api.module.user.domain;

import org.sqribe.api.module.common.domain.Person;

import javax.persistence.*;

@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String email;
    private String password;

    @OneToOne
    private Person person;

    public User() {}

    public User(long id, String email, String password)
    {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    /**
     * Getters and setters.
     */

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}