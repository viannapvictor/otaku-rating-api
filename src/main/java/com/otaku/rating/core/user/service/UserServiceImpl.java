package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.UserRegister;
import com.otaku.rating.core.user.model.supportobjects.Email;
import com.otaku.rating.core.user.model.supportobjects.UserName;
import com.otaku.rating.core.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    private final PasswordEncoderServiceImpl passwordEncoderService;

    @Override
    public User createUser(UserRegister userRegister) {
        if (userRepository.existsByEmail(userRegister.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }
        if (userRepository.existsByUserName(userRegister.getUserName())) {
            throw new IllegalArgumentException("Username already exists");
        }
        
        User user = new User(passwordEncoderService, userRegister);
        return userRepository.save(user);
    }

    @Override
    public Page<User> getPage(int page, int size) {
        return userRepository.findAll(page, size);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findByEmail(Email email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByUserName(UserName userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public boolean existsByEmail(Email email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByUserName(UserName userName) {
        return userRepository.existsByUserName(userName);
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(() -> new RuntimeException("User not found"));
        
        existingUser.setUserName(user.getUserName());
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}