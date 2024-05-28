package com.mine.jwt.Repository.User;

import com.mine.jwt.Models.Domain.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    UserDetails findByEmail(String email);
}


