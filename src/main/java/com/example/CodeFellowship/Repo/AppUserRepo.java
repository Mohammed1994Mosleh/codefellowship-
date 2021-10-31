package com.example.CodeFellowship.Repo;

import com.example.CodeFellowship.Models.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepo extends CrudRepository<ApplicationUser,Integer> {
    ApplicationUser findByUsername(String username);
}
