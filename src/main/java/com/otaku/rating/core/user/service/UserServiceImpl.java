package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.exception.*;
import com.otaku.rating.core.user.model.*;
import com.otaku.rating.core.user.model.valueobject.Email;
import com.otaku.rating.core.user.model.valueobject.Name;
import com.otaku.rating.core.user.model.valueobject.UserName;
import com.otaku.rating.core.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Getter
    private final ContextService context;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(UserRegister userRegister) {
        if (userRepository.existsByEmail(userRegister.getEmail())) {
            throw new EmailAlreadyExistsException();
        }
        if (userRepository.existsByUserName(userRegister.getUserName())) {
            throw new UserNameAlreadyExistsException();
        }

        User user = new User(userRegister);
        return userRepository.save(user);
    }

    @Override
    public Page<User> getPage(int page, int size) {
        return userRepository.findAll(page, size);
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findByEmail(Email email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User findByUserName(UserName userName) {
        return userRepository.findByUserName(userName).orElseThrow(UserNotFoundException::new);
    }

    @Override
    @Transactional
    public User updateUserProfile(Name name, UserName userName) {
        User user = context.getUserOrThrow();
        if (name != null) {
            user.setName(name);
        }
        if (userName != null) {
            if (!user.getUserName().equals(userName) && userRepository.existsByUserName(userName)) {
                throw new UserNameAlreadyExistsException();
            }
            user.setUserName(userName);
        }
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }
}