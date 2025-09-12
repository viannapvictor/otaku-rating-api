package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.model.AuthTokens;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.UserRegister;
import com.otaku.rating.core.user.model.valueobjects.Email;
import com.otaku.rating.core.user.model.valueobjects.Name;
import com.otaku.rating.core.user.model.valueobjects.Password;
import com.otaku.rating.core.user.model.valueobjects.UserName;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    User createUser(UserRegister userRegister);
    Page<User> getPage(int page, int size);
    User findById(long id);
    User findByEmail(Email email);
    User findByUserName(UserName userName);
    User updateUserProfile(Name name, UserName userName);
    void deleteUser(User user);
    void updateUserPassword(Password oldPassword, Password newPassword);
    AuthTokens login(Email email, Password password);
    void resetPassword(String code, Password newPassword);
    void logout();
    void confirmEmail(String code, Email currentEmail);
    void updateUserEmail(Email email);
    ContextService getContext();
}