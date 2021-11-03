package com.example.CodeFellowship.Controller;

import com.example.CodeFellowship.Models.ApplicationUser;
import com.example.CodeFellowship.Models.Post;
import com.example.CodeFellowship.Repo.AppUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.persistence.OneToMany;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AppUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AppUserRepo appUserRepo;


    @OneToMany(mappedBy= "appUser")
    private List<Post> userPosts;


    @GetMapping("/")
    public String homPage( Principal principal, Model model){

        ApplicationUser user = appUserRepo.findByUsername(principal.getName());

           model.addAttribute("user",user);

    return "Home.html";
    }
    @GetMapping("profile/{id}")
    public String profile(@PathVariable Integer id,Model model){
        ApplicationUser loginUser=appUserRepo.findByid(id);
        model.addAttribute("profile",loginUser);
        return "profile";
    }

    @GetMapping("/Allusers")
    public String allUsers(Model model){
        ArrayList <ApplicationUser> allUsers=(ArrayList<ApplicationUser>) appUserRepo.findAll();
        model.addAttribute("users",allUsers);
        return "Allusers";
    }



    @GetMapping("/signup")
    public String singUppage(){
        return "signup";
    }

    @PostMapping ("/signup")
    public RedirectView signUpuser(@RequestParam String username, @RequestParam String password , @RequestParam String firstName
                              , @RequestParam String lastName, @RequestParam String dateOfBirth, @RequestParam String bio){

        ApplicationUser newUser=new ApplicationUser(username, encoder.encode(password), firstName,lastName,dateOfBirth, bio);
        appUserRepo.save(newUser);
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/profile");

    }


    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

}
