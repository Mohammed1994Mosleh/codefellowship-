package com.example.CodeFellowship.Controller;

import com.example.CodeFellowship.Models.ApplicationUser;
import com.example.CodeFellowship.Repo.AppUserRepo;
import com.example.CodeFellowship.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;

@Controller
public class Postcontroller {

    @Autowired
    PostRepo postRepo;

    @Autowired
    AppUserRepo appRepo;

//    @PostMapping("/addpost")
//    public RedirectView addPost(Principal principal, @RequestParam String post){
//        ApplicationUser userAddpost=appRepo.findByUsername(principal.getName());
//
//
//
//
//
//
//
//    }

}
