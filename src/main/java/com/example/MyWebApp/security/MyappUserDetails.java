package com.example.MyWebApp.security;

import com.example.MyWebApp.entity.MyAppUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

public class MyappUserDetails implements UserDetails {
    String ROLE_PREFIX = "ROLE_";
    String userName;
    String password;
    String role;

    private final MyAppUser myAppUser;


    public MyappUserDetails(MyAppUser myAppUser){
        this.myAppUser=myAppUser;
    }

    public MyAppUser getMyAppUser(){
        return myAppUser;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(ROLE_PREFIX + myAppUser.getRole()));
    }

    @Override
    public String getPassword() {
        return myAppUser.getPassword();
    }

    @Override
    public String getUsername() {
        return myAppUser.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
