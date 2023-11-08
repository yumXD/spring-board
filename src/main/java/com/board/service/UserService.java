package com.board.service;

import com.board.constant.Role;
import com.board.entity.User;
import com.board.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User create(String username, String email, String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
        return user;
    }

    public User getUser(String username) {
        Optional<User> siteUser = this.userRepository.findByEmail(username);
        if (siteUser.isPresent()) {
            return siteUser.get();
        } else {
            throw new EntityNotFoundException("siteUser not found");
        }
    }

    public void modify(String password, String email) {
        User user = getUser(email);
        user.setPassword(passwordEncoder.encode(password));
        this.userRepository.save(user);
    }
}
