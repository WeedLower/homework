package com.example.MyWebApp.service;


import com.example.MyWebApp.entity.ContactConfirmationPayload;
import com.example.MyWebApp.entity.ContactConfirmationResponse;
import com.example.MyWebApp.entity.MyAppUser;
import com.example.MyWebApp.entity.Role;
import com.example.MyWebApp.repository.MyappUserRepository;
import com.example.MyWebApp.entity.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class MyappUserRegister{
    private final MyappUserRepository myappUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final MyappUserDetailService myappUserDetailService;




    @Autowired
    public MyappUserRegister(MyappUserRepository myappUserRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserService userService, MyappUserDetailService myappUserDetailService) {
        this.myappUserRepository = myappUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.myappUserDetailService = myappUserDetailService;
    }

    public void registerNewUser(RegistrationForm registrationForm){
        if (myappUserRepository.findMyAppUserByName(registrationForm.getName()) == null){
            MyAppUser user = new MyAppUser();
            user.setName(registrationForm.getName());
            user.setEmail(registrationForm.getEmail());
            user.setLoginDate("Never");
            user.setRegistrationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            user.setStatus(false);
            user.setChanged(false);
            user.setRole(Role.ADMIN);
            user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
            myappUserRepository.save(user);
        }
    }

    public ContactConfirmationResponse login(ContactConfirmationPayload payload){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(payload.getContact(),payload.getCode()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        ContactConfirmationResponse response = new ContactConfirmationResponse();
        response.setResult("true");
        return response;
    }


}
