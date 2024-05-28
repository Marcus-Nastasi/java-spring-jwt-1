package com.mine.jwt.Controller;

import com.mine.jwt.Models.Domain.Users.User;
import com.mine.jwt.Repository.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class HomeController {

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

    @GetMapping(value = "/api/users")
    public List<User> getAll() {
        return userRepo.findAll();
    }

    @PostMapping(value = "/api/users/add")
    public void addUser(@RequestBody User user) {
        userRepo.save(user);
    }
}




