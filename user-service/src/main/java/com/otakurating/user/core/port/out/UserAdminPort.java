package com.otakurating.user.core.port.out;

import com.otakurating.user.core.model.KeycloakUserRepresentation;

import java.util.List;

public interface UserAdminPort {
    String createUser(KeycloakUserRepresentation user);
    KeycloakUserRepresentation getUser(String userId);
    List<KeycloakUserRepresentation> getUserByUsername(String username);
    List<KeycloakUserRepresentation> getUserByEmail(String email);
    void updateUser(String userId, KeycloakUserRepresentation user);
    void deleteUser(String userId);
    void resetPassword(String userId, String newPassword, boolean temporary);
    List<KeycloakUserRepresentation> getUsers(int first, int max);
}