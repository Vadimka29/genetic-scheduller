package com.redkite.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(nullable = false)
    private String login;

    @Column(nullable = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

//    @OneToMany(mappedBy="user")
//    private List<Task> tasks;


    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {}

}
