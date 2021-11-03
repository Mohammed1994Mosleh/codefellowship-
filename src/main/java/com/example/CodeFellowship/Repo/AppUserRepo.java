package com.example.CodeFellowship.Repo;

import com.example.CodeFellowship.Models.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface AppUserRepo extends CrudRepository<ApplicationUser,Integer> {
    ApplicationUser findByUsername(String username);
    ApplicationUser findByid(Integer id);
}
