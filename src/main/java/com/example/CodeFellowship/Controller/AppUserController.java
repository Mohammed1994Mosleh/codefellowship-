package com.example.CodeFellowship.Controller;

import com.example.CodeFellowship.Models.ApplicationUser;
import com.example.CodeFellowship.Repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Date;

@Controller
public class AppUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AppUserRepo appUserRepo;

    @GetMapping("/signup")
    public String singUppage(){
        return "signup";
    }

    @PostMapping ("/signup")
    public RedirectView signUpuser(@RequestParam(value ="username") String username, @RequestParam(value = "password") String password , @RequestParam(value = "firstName") String firstName
                              , @RequestParam(value = "lastName") String lastName, @RequestParam(value = "dateOfBirth") String dateOfBirth, @RequestParam(value = "bio") String bio){

        ApplicationUser newUser=new ApplicationUser(username, encoder.encode(password), firstName,lastName,dateOfBirth, bio);
        appUserRepo.save(newUser);
        return new RedirectView("/login");

    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

}
