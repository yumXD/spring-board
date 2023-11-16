package com.board.service;

import com.board.constant.Role;
import com.board.entity.File;
import com.board.entity.User;
import com.board.repository.FileRepository;
import com.board.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final FileRepository fileRepository;
    private final PasswordEncoder passwordEncoder;

    private final FileService fileService;

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

    @Transactional
    public void modify(String password, String email, MultipartFile file) throws IOException {
        User user = getUser(email);
        user.setPassword(passwordEncoder.encode(password));

        fileService.uploadUserImg(file, user);

        this.userRepository.save(user);
    }

    public File getUserImg(Long id) {
        return fileRepository.findByUserId(id);
    }
}
