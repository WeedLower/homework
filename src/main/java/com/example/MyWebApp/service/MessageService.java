package com.example.MyWebApp.service;

import com.example.MyWebApp.entity.Message;
import com.example.MyWebApp.entity.MyAppUser;
import com.example.MyWebApp.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class MessageService {


    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {

        this.messageRepository = messageRepository;
    }

    public void sendMessage(MyAppUser myAppUser,String email,String textMessage){
        Message messages = new Message();
        messages.setMessage(textMessage);
        messages.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-mm-yyyy hh:mm")));
        messages.setEmail(email);
        messages.setMyAppUser(myAppUser);
        messageRepository.save(messages);
    }
}
