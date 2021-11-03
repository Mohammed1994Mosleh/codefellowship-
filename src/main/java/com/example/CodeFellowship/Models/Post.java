package com.example.CodeFellowship.Models;


import org.springframework.data.annotation.CreatedBy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Post  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;



    private LocalDateTime createdAt ;

    private String body;

    @ManyToOne
    private ApplicationUser appUser;


    public Post(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public LocalDateTime getCreatedAt() {
        return createdAt;
    }



    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public ApplicationUser getAppUser() {
        return appUser;
    }

    public void setAppUser(ApplicationUser appUser) {
        this.appUser = appUser;
    }

    public Post(String body, ApplicationUser user){
        this.appUser=user;
        this.body=body;
        this.createdAt = LocalDateTime.now();
    }
}
