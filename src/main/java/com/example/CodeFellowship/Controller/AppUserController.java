package com.example.CodeFellowship.Controller;

import com.example.CodeFellowship.Models.ApplicationUser;
import com.example.CodeFellowship.Models.Post;
import com.example.CodeFellowship.Repo.AppUserRepo;
import com.example.CodeFellowship.Repo.PostRepo;
import com.sun.org.apache.xpath.internal.operations.Mod;
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
import java.util.Set;

@Controller
public class AppUserController {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    PostRepo postRepo;

    @Autowired
    AppUserRepo appUserRepo;


    @OneToMany(mappedBy= "appUser")
    private List<Post> userPosts;


    @GetMapping("/")
    public String homPage( Principal principal, Model model){

        ApplicationUser user = appUserRepo.findByUsername(principal.getName());

           model.addAttribute("user",user);

        List<Post> postList = (List<Post>) postRepo.findAll();

        if (user != null) {
            ApplicationUser currentUser = appUserRepo.findByUsername(user.getUsername());
            model.addAttribute("username", currentUser.getUsername());

            List<Post> myFollowingPost = new ArrayList();
            for (Post post : postList) {
                if (!currentUser.getFollowing().contains(post.getAppUser()) && post.getAppUser() != currentUser)  myFollowingPost.add(post);
            }
            model.addAttribute("postList", myFollowingPost);
        }




    return "Home.html";
    }
    @GetMapping("profile/{id}")
    public String profile(@PathVariable Integer id,Model model){
        ApplicationUser loginUser=appUserRepo.findByid(id);
        model.addAttribute("profile",loginUser);
        return "profile";
    }

    @PostMapping("/follow")
    public RedirectView printHi(@RequestParam Integer id, @AuthenticationPrincipal ApplicationUser user, Model model) {
        ApplicationUser currentUser = appUserRepo.findByUsername(user.getUsername());
        ApplicationUser newFollowing = appUserRepo.findById(id).get();
        currentUser.setFollowing(newFollowing);
        appUserRepo.save(currentUser);
        return new RedirectView("/");
    }


    @GetMapping("/feed")
    public String feel(@AuthenticationPrincipal ApplicationUser user , Model model) {
        if (user != null){
            Set<ApplicationUser> myFollowing = appUserRepo.findByUsername(user.getUsername()).getFollowing();
            List<Post> postList = new ArrayList();
            for (ApplicationUser currentFollower : myFollowing) {
                postList.addAll(currentFollower.getAllposts());
            }
            model.addAttribute("postList", postList);
        }
        return "feed.html";
    }


    @GetMapping("/Allusers")
    public String allUsers(Model model){
        ArrayList <ApplicationUser> allUsers=(ArrayList<ApplicationUser>) appUserRepo.findAll();
        model.addAttribute("users",allUsers);
        return "Allusers";
    }

    @GetMapping("/showfollower")
public String showFollowers(Principal p,Model model){
        ApplicationUser appUser=appUserRepo.findByUsername(p.getName());
        model.addAttribute("users",appUser);
//       appUser.getMyFollowers()

      return "follwer";
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
