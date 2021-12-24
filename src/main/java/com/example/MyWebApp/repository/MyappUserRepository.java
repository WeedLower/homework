package com.example.MyWebApp.repository;

import com.example.MyWebApp.entity.MyAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface MyappUserRepository extends JpaRepository<MyAppUser,Integer> {

    MyAppUser findMyAppUserByEmail(String email);
    MyAppUser findMyAppUserByName(String name);
    MyAppUser findMyAppUserById(Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE MyAppUser u SET u.loginDate= :loginDate WHERE u.name= :name")
    void setLoginDate(@Param("name") String name,@Param("loginDate") String loginDate);

    @Transactional
    @Modifying
    @Query("UPDATE MyAppUser u SET u.status=true WHERE u.id= :id")
    void setStatusTrue(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE MyAppUser u SET u.status=false WHERE u.id= :id")
    void setStatusFalse(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE MyAppUser u SET u.role='ADMIN' WHERE u.id= :id")
    void setRoleAdmin(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE MyAppUser u SET u.role='USER' WHERE u.id= :id")
    void setRoleUser(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE MyAppUser u SET u.changed=true WHERE u.id= :id")
    void setChangedTrue(@Param("id") Integer id);

    @Transactional
    @Modifying
    @Query("UPDATE MyAppUser u SET u.changed=false WHERE u.id= :id")
    void setChangedFalse(@Param("id") Integer id);
}
