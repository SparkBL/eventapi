package ru.cft.starterkit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.text.ParseException;
import java.util.Objects;
import java.util.UUID;

public class User {

    private String login;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private boolean admin;
    @JsonIgnore
    private UUID baz;

    public User(String login, String password, String email, String firstname,String lastname, UUID baz) {
        this.login = login;
        this.email = email;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.admin = false;
        this.baz = baz;
    }

    public User()  {
    }





}
