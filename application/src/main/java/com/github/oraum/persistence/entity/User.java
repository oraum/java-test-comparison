package com.github.oraum.persistence.entity;

import javax.persistence.*;

@Entity
@NamedQuery(name = "User.getAll", query = "select u from User u")
public class User {
    private Long id;
    private String name;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
