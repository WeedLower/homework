package com.example.MyWebApp.repository;

import com.example.MyWebApp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message,Integer> {
    @Override
    List<Message> findAllById(Iterable<Integer> integers);

    List<Message> findAllByMyAppUser_Id(Integer id);

}
