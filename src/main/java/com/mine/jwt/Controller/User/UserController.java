package com.mine.jwt.Controller.User;

import com.mine.jwt.Models.Domain.Users.User;
import com.mine.jwt.Repository.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String home() {
        return "You did reach home";
    }

    @GetMapping(value = "/adm")
    public String homeAdm() {
        return "You did reach home as ADM";
    }

    @GetMapping(value = "/users")
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public User getSingle(@PathVariable String id) {
        return userRepo.findById(id).get();
    }

    @DeleteMapping(value = "/users/delete/{id}")
    public void del(@PathVariable String id) {
        userRepo.deleteById(id);
    }
}




