package org.sqribe.api.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;


@Entity
@Data
@Table(name="user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique=true)
    private String username;

    @Column(nullable = false)
    @JsonIgnore
    private String password;

    @Column(nullable = true)
    private boolean accountExpired = false;

    @Column(nullable = true)
    private boolean accountLocked = false;

    @Column(nullable = true)
    private boolean credentialsExpired = false;

    @Column(nullable = true)
    private boolean enabled = true;

    @Column(nullable = true)
    private String firstName;

    @Column(nullable = true)
    private String lastName;

    @Column(nullable = true)
    private String note;


    @ManyToMany
    private Collection<Authority> authorities = new ArrayList<Authority>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User() {}

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return !this.accountExpired;
    }

    public boolean isAccountNonLocked() {
        return !this.accountLocked;
    }

    public boolean isCredentialsNonExpired() {
        return !this.credentialsExpired;
    }

    public boolean isEnabled() {
        return this.enabled;
    }
}
