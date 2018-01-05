package org.sqribe.api.module.common.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    private long id;

    private Name name;

    public Person() {}

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
