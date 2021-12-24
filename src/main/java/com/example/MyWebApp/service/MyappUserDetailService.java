package com.example.MyWebApp.service;

import com.example.MyWebApp.entity.MyAppUser;
import com.example.MyWebApp.repository.MyappUserRepository;
import com.example.MyWebApp.security.MyappUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyappUserDetailService implements UserDetailsService {

    private final MyappUserRepository myappUserRepository;

    @Autowired
    public MyappUserDetailService(MyappUserRepository myappUserRepository) {
        this.myappUserRepository = myappUserRepository;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyAppUser myAppUser = myappUserRepository.findMyAppUserByName(username);
        if (myAppUser.isStatus()){
            throw new UsernameNotFoundException("user baned");
        }
        if(myAppUser !=null){
            return new MyappUserDetails(myAppUser);
        }else{
            throw new UsernameNotFoundException("user not found!");
        }
    }


}
