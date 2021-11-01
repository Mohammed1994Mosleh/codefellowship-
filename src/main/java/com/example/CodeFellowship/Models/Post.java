package com.example.CodeFellowship.Models;


import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import java.time.Instant;
import java.time.LocalDateTime;

@Entity
public class Post extends AuditableBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @CreatedBy
    private ApplicationUser user;

    private LocalDateTime createdAt = LocalDateTime.now();

    private String body;

    @ManyToOne
    private ApplicationUser appUser;


    public Post(){

    }

    public Post(ApplicationUser user,String body,LocalDateTime createdAt){
        this.user=user;
        this.body=body;
        this.createdAt=createdAt;
    }
}
