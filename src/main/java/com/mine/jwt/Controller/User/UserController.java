package com.mine.jwt.Controller.User;

import com.mine.jwt.Models.Domain.Users.User;
import com.mine.jwt.Repository.User.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "")
    public String home() {
        return "You did reach home";
    }

    @GetMapping(value = "/adm")
    public String homeAdm() {
        return "You did reach home as ADM";
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userRepo.findAll());
    }

    @GetMapping(value = "/users/{id}")
    public ResponseEntity<User> getSingle(@PathVariable String id) throws RuntimeException {
        if (userRepo.findById(id).isEmpty()) throw new RuntimeException("user not found");
        return ResponseEntity.ok(userRepo.findById(id).get());
    }

    @DeleteMapping(value = "/users/delete/{id}")
    public ResponseEntity<String> del(@PathVariable String id) throws RuntimeException {
        if (userRepo.findById(id).isEmpty()) throw new RuntimeException("user not found");
        userRepo.deleteById(id);
        return ResponseEntity.status(202).body("successfully deleted");
    }
}




