package com.otakurating.user.core.service;

import com.otakurating.user.core.model.KeycloakUserRepresentation;

import java.util.List;

public interface KeycloakAdminService {
    String createUser(KeycloakUserRepresentation user);
    KeycloakUserRepresentation getUser(String userId);
    List<KeycloakUserRepresentation> getUserByUsername(String username);
    List<KeycloakUserRepresentation> getUserByEmail(String email);
    void updateUser(String userId, KeycloakUserRepresentation user);
    void deleteUser(String userId);
    void resetPassword(String userId, String newPassword, boolean temporary);
    List<KeycloakUserRepresentation> getUsers(int first, int max);
}