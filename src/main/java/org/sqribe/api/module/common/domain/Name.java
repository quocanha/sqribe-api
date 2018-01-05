package org.sqribe.api.module.common.domain;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

@Embeddable
public class Name {

    @Basic(optional = false)
    private String firstName;

    private String middleName;

    @Basic(optional = false)
    private String lastName;

    public Name() {}

    /**
     * Getters and setters.
     */

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
