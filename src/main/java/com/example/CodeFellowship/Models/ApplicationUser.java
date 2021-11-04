package com.example.CodeFellowship.Models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

@Entity
public class ApplicationUser implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String bio;
    @OneToMany(mappedBy = "appUser")
    List<Post> allposts;

    @ManyToMany (cascade = {CascadeType.ALL})
    @JoinTable(name = "following_follower" , joinColumns = {@JoinColumn(name = "following_id")} , inverseJoinColumns = {@JoinColumn(name = "follower_id")})
    private Set<ApplicationUser> following = new HashSet<>();

    @ManyToMany (cascade = {CascadeType.ALL})
    @JoinTable(name = "following_follower" , joinColumns = {@JoinColumn(name = "follower_id")} , inverseJoinColumns = {@JoinColumn(name = "following_id")})
    private List<ApplicationUser> myFollowers = new ArrayList<>();


    public ApplicationUser(){

    }

    public ApplicationUser(String username, String password){
        this.username = username;
        this.password = password;
    }

    public ApplicationUser(String username, String password, String firstName, String lastName, String dateOfBirth, String bio) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.bio = bio;
    }


    //getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getBio() {
        return bio;
    }

    public List<Post> getAllposts() {
        return allposts;
    }
    public void setAllposts(List<Post> allposts) {
        this.allposts = allposts;
    }

    //override methods
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }


    public List<ApplicationUser> getFoollwer(){
        return myFollowers;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Set<ApplicationUser> getFollowing() {
        return following;
    }

    public void setFollowing(ApplicationUser newFollowing) {
        this.following.add(newFollowing);
    }

    public List<ApplicationUser> getMyFollowers() {
        return myFollowers;
    }

}


