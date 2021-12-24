package com.example.MyWebApp.entity;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class RegistrationForm {

    @NotEmpty(message = "Name should not be empty")
    @Size(min=2,max=30,message="Name should be between 2 and 30 characters")
    private String name;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email shoul be valid")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    @Size(min=1,message="Password must be at least 1 character long ")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "RegistrationForm{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
