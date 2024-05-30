package com.mine.jwt.Controller.Auth;

import com.mine.jwt.Models.Domain.Users.AuthDTO;
import com.mine.jwt.Models.Domain.Users.RegisterDTO;
import com.mine.jwt.Models.Domain.Users.User;
import com.mine.jwt.Models.Domain.Users.UserRole;
import com.mine.jwt.Repository.User.UserRepo;
import com.mine.jwt.Services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody AuthDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generate((User) auth.getPrincipal());

        return ResponseEntity.ok().body(token);
    }

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterDTO> register(@RequestBody RegisterDTO registerDTO) throws RuntimeException {
        if (userRepo.findByEmail(registerDTO.email()) != null) throw new RuntimeException("E-mail already exists");

        String encoded = this.passwordEncoder.encode(registerDTO.password());
        User newUser = new User(registerDTO.email(), encoded, UserRole.valueOf(registerDTO.role()));

        userRepo.save(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value = "/valid")
    public boolean valid(@RequestBody User user) throws RuntimeException {
        if (userRepo.findById(user.getId()).isEmpty()) throw new RuntimeException("user not found");

        String userPass = userRepo.findById(user.getId()).get().getPassword();
        boolean isRight = passwordEncoder.matches(user.getPassword(), userPass);

        if (!isRight) throw new RuntimeException("password wrong");

        return true;
    }
}


