package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.model.AuthTokens;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.UserRegister;
import com.otaku.rating.core.user.model.valueobject.Email;
import com.otaku.rating.core.user.model.valueobject.Name;
import com.otaku.rating.core.user.model.valueobject.Password;
import com.otaku.rating.core.user.model.valueobject.UserName;
import org.springframework.data.domain.Page;

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