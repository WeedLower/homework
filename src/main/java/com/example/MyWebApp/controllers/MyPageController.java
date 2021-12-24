package com.example.MyWebApp.controllers;

import com.example.MyWebApp.entity.ContactConfirmationPayload;
import com.example.MyWebApp.entity.Message;
import com.example.MyWebApp.entity.MyAppUser;
import com.example.MyWebApp.repository.MessageRepository;
import com.example.MyWebApp.repository.MyappUserRepository;
import com.example.MyWebApp.security.MyappUserDetails;
import com.example.MyWebApp.service.MessageService;
import com.example.MyWebApp.service.MyappUserRegister;
import com.example.MyWebApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MyPageController {

    private final MyappUserRepository myappUserRepository;
    private final MessageRepository messageRepository;
    private final MessageService messageService;
    private final UserService userService;
    private final MyappUserRegister userRegister;


    @Autowired
    public MyPageController(MyappUserRepository myappUserRepository, MessageRepository messageRepository, MessageService messageService, UserService userService, MyappUserRegister userRegister) {
        this.myappUserRepository = myappUserRepository;
        this.messageRepository = messageRepository;
        this.messageService = messageService;
        this.userService = userService;
        this.userRegister = userRegister;
    }


    @GetMapping("/my")
    public String handleMy(Model model, ContactConfirmationPayload payload){
        MyAppUser myAppUser = userService.getUser();
        if (userService.checkUserChanges(myAppUser)){return "redirect:/logout";}
        model.addAttribute("user",new MyAppUser());
        model.addAttribute("userList",myappUserRepository.findAll());
        MyappUserDetails user=(MyappUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        myappUserRepository.setLoginDate(user.getUsername(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-mm-yyyy hh:mm")));
        model.addAttribute("owner",user);
        return "my";
    }

    @GetMapping("/profile")
    public String myProfile(Model model){
        MyAppUser myAppUser = userService.getUser();
        if (userService.checkUserChanges(myAppUser)){return "redirect:/logout";}
        model.addAttribute("massage", new Message());
        model.addAttribute("messageList", messageRepository.findAllByMyAppUser_Id(myAppUser.getId()));
        return "profile";
    }

    @RequestMapping(value = "/action",params = {"send"})
    public String send(@RequestParam(value = "checkboxName",required = false) List<String> idUser , @RequestParam(value = "mestext")String text){
        MyAppUser myAppUser = userService.getUser();
        if (userService.checkUserChanges(myAppUser)){return "redirect:/logout";}
        List<MyAppUser> userList = new ArrayList<>();
        MyappUserDetails userDetails=(MyappUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyAppUser userSender=myappUserRepository.findMyAppUserByName(userDetails.getUsername());
        if(idUser != null){
            for(String idrateStr : idUser){
                int iduser = Integer.parseInt(idrateStr);
                MyAppUser user= myappUserRepository.findMyAppUserById(iduser);
                messageService.sendMessage(user,userSender.getEmail(),text);
            }
        }
        return "redirect:/my";
    }

    @RequestMapping(value = "/action",params = {"banUser"})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String setStatustrue(@RequestParam(value = "checkboxName",required = false) List<String> idUser){
        MyAppUser myAppUser = userService.getUser();
        if (userService.checkUserChanges(myAppUser)){return "redirect:/logout";}
        if(idUser != null){
            for(String idrateStr : idUser){
                int iduser = Integer.parseInt(idrateStr);
                myappUserRepository.setStatusTrue(iduser);
                myappUserRepository.setChangedTrue(iduser);
                if (myAppUser.getId()==iduser){
                    return "redirect:/logout";
                }
            }
        }
        return "redirect:/my";
    }

    @RequestMapping(value = "/action",params = {"unbanUser"})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String setStatusfalse(@RequestParam(value = "checkboxName",required = false) List<String> idUser){
        MyAppUser myAppUser = userService.getUser();
        if (userService.checkUserChanges(myAppUser)){return "redirect:/logout";}
        if(idUser != null){
            for(String idrateStr : idUser){
                int iduser = Integer.parseInt(idrateStr);
                myappUserRepository.setStatusFalse(iduser);
            }
        }
        return "redirect:/my";
    }

    @RequestMapping(value = "/action",params = {"delete"})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String delete(@RequestParam(value = "checkboxName",required = false) List<String> idUser){
        MyAppUser myAppUser = userService.getUser();
        if (userService.checkUserChanges(myAppUser)){return "redirect:/logout";}
        if(idUser != null){
            for(String idrateStr : idUser){
                int iduser = Integer.parseInt(idrateStr);
                myappUserRepository.deleteById(iduser);
                myappUserRepository.setChangedTrue(iduser);
                if (myAppUser.getId()==iduser){
                    return "redirect:/logout";
                }
            }
        }
        return "redirect:/my";
    }

    @RequestMapping(value = "/action",params = {"setuser"})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String setUser(@RequestParam(value = "checkboxName",required = false) List<String> idUser){
        MyAppUser myAppUser = userService.getUser();
        if (userService.checkUserChanges(myAppUser)){return "redirect:/logout";}
        if(idUser != null){
            for(String idrateStr : idUser){
                int iduser = Integer.parseInt(idrateStr);
                myappUserRepository.setRoleUser(iduser);
                myappUserRepository.setChangedTrue(iduser);
            }
        }
        return "redirect:/my";
    }

    @RequestMapping(value = "/action",params = {"setadmin"})
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String setAdmin(@RequestParam(value = "checkboxName",required = false) List<String> idUser){
        MyAppUser myAppUser = userService.getUser();
        if (userService.checkUserChanges(myAppUser)){return "redirect:/logout";}
        if(idUser != null){
            for(String idrateStr : idUser){
                int iduser = Integer.parseInt(idrateStr);
                myappUserRepository.setRoleAdmin(iduser);
                myappUserRepository.setChangedTrue(iduser);
            }
        }
        return "redirect:/my";
    }
}
