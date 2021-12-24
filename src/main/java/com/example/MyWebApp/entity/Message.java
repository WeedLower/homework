package com.example.MyWebApp.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String date;
    @Column(columnDefinition = "VARCHAR(255)")
    private String message;

    @ManyToOne
    @OnDelete(action= OnDeleteAction.CASCADE)
    private MyAppUser myAppUser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public MyAppUser getMyAppUser() {
        return myAppUser;
    }

    public void setMyAppUser(MyAppUser myAppUser) {
        this.myAppUser = myAppUser;
    }
}
