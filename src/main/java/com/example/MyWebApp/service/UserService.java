package com.example.MyWebApp.service;

import com.example.MyWebApp.entity.MyAppUser;
import com.example.MyWebApp.repository.MyappUserRepository;
import com.example.MyWebApp.security.MyappUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private MyappUserRepository userRepository;

    @Autowired
    public UserService(MyappUserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public MyAppUser getUser(){
        MyappUserDetails userDetails = (MyappUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findMyAppUserByName(userDetails.getUsername());
    }

    public boolean checkUserChanges(MyAppUser user){
        if (user.isChanged()){
            userRepository.setChangedFalse(user.getId());
            return true;
        }
        return false;
    }







}
