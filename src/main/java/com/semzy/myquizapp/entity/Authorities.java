package com.semzy.myquizapp.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "authorities")
public class Authorities {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "authority")
    private String name;


    @ManyToMany(mappedBy = "roles")
    private Set<Users> users;

    public Set<Users> getUsers() {
        return users;
    }

    public void setUsers(Set<Users> users) {
        this.users = users;
    }

    public Authorities() {
    }

    public Authorities(String authority) {
        this.name = authority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthority() {
        return name;
    }

    public void setAuthority(String authority) {
        this.name = authority;
    }

    @Override
    public String toString() {
        return "Authorities{" +
                "authority=" + name +
                '}';
    }
}
