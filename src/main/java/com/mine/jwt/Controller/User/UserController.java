package com.mine.jwt.Controller.User;

import com.mine.jwt.Models.Domain.Users.User;
import com.mine.jwt.Repository.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @PostMapping(value = "/users/add")
    public void addUser(@RequestBody User user) {
        String encoded = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encoded);
        userRepo.save(user);
    }

    @PostMapping(value = "/valid")
    public boolean valid(@RequestBody User user) {
        String userPass = userRepo.findById(user.getId()).get().getPassword();
        return new BCryptPasswordEncoder().matches(user.getPassword(), userPass);
    }

    @DeleteMapping(value = "/users/delete/{id}")
    public void del(@PathVariable String id) {
        userRepo.deleteById(id);
    }
}




