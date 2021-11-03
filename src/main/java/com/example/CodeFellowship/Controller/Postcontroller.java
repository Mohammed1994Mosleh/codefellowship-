package com.example.CodeFellowship.Controller;

import com.example.CodeFellowship.Models.ApplicationUser;
import com.example.CodeFellowship.Models.Post;
import com.example.CodeFellowship.Repo.AppUserRepo;
import com.example.CodeFellowship.Repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/post")
    public String getprofile(Principal p, Model model){
        model.addAttribute("usernamePrincipal",p.getName());

        model.addAttribute("profile",appRepo.findByUsername(p.getName()));
        return "profile.html";
    }
    @PostMapping("/post")
    public RedirectView postprofile(Principal p, @RequestParam String post){
        Post newpost= new Post(post,appRepo.findByUsername(p.getName()));
        postRepo.save(newpost);
        return new RedirectView("/post");
    }

}
