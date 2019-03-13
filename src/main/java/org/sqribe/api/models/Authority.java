package org.sqribe.api.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Data
@Table(name="authority")
public class Authority implements GrantedAuthority {

    public Authority() {}

    public Authority(String authority) {
        this.authority = authority;
    }

    @Id
    @GeneratedValue
    private long id;

    private String authority;

    public String toString() {
        return this.authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
