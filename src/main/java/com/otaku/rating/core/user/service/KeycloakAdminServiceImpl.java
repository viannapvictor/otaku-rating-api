package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.model.KeycloakUserRepresentation;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeycloakAdminServiceImpl implements KeycloakAdminService {

    private final Keycloak keycloak;

    @Value("${keycloak.realm}")
    private String realm;

    private RealmResource getRealmResource() {
        return keycloak.realm(realm);
    }

    private UsersResource getUsersResource() {
        return getRealmResource().users();
    }

    private KeycloakUserRepresentation convertToCustomRepresentation(UserRepresentation userRep) {
        KeycloakUserRepresentation customRep = new KeycloakUserRepresentation();
        customRep.setId(userRep.getId());
        customRep.setUsername(userRep.getUsername());
        customRep.setEmail(userRep.getEmail());
        customRep.setFirstName(userRep.getFirstName());
        customRep.setLastName(userRep.getLastName());
        customRep.setEnabled(userRep.isEnabled());
        customRep.setEmailVerified(userRep.isEmailVerified());
        customRep.setCreatedTimestamp(userRep.getCreatedTimestamp());

        UserResource userResource = getUsersResource().get(userRep.getId());
        List<String> roles = userResource.roles().realmLevel().listEffective().stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());
        customRep.setRoles(roles);
        customRep.setUpdatedTimestamp(userRep.getCreatedTimestamp());

        return customRep;
    }

    private UserRepresentation convertFromCustomRepresentation(KeycloakUserRepresentation customRep) {
        UserRepresentation userRep = new UserRepresentation();
        if (customRep.getUsername() != null) {
            userRep.setUsername(customRep.getUsername());
        }
        if (customRep.getEmail() != null) {
            userRep.setEmail(customRep.getEmail());
        }
        if (customRep.getFirstName() != null) {
            userRep.setFirstName(customRep.getFirstName());
        }
        if (customRep.getLastName() != null) {
            userRep.setLastName(customRep.getLastName());
        }
        if (customRep.getEnabled() != null) {
            userRep.setEnabled(customRep.getEnabled());
        }
        if (customRep.getEmailVerified() != null) {
            userRep.setEmailVerified(customRep.getEmailVerified());
        }
        return userRep;
    }

    @Override
    public String createUser(KeycloakUserRepresentation user) {
        UserRepresentation userRep = convertFromCustomRepresentation(user);

        Response response = getUsersResource().create(userRep);

        if (response.getStatus() == 201) {
            String locationHeader = response.getHeaderString("Location");
            String userId = locationHeader.substring(locationHeader.lastIndexOf('/') + 1);
            response.close();
            return userId;
        }

        response.close();
        throw new RuntimeException("Failed to create user in Keycloak. Status: " + response.getStatus());
    }

    @Override
    public KeycloakUserRepresentation getUser(String userId) {
        UserResource userResource = getUsersResource().get(userId);
        UserRepresentation userRep = userResource.toRepresentation();
        return convertToCustomRepresentation(userRep);
    }

    @Override
    public List<KeycloakUserRepresentation> getUserByUsername(String username) {
        List<UserRepresentation> users = getUsersResource().search(username, true);
        return users.stream()
                .map(this::convertToCustomRepresentation)
                .collect(Collectors.toList());
    }

    @Override
    public List<KeycloakUserRepresentation> getUserByEmail(String email) {
        List<UserRepresentation> users = getUsersResource().searchByEmail(email, true);
        return users.stream()
                .map(this::convertToCustomRepresentation)
                .collect(Collectors.toList());
    }

    @Override
    public void updateUser(String userId, KeycloakUserRepresentation user) {
        try {
            UserRepresentation userRep = convertFromCustomRepresentation(user);
            UserResource userResource = getUsersResource().get(userId);
            userResource.update(userRep);
        } catch (Exception e) {
            throw new RuntimeException("Failed to update user in Keycloak: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteUser(String userId) {
        getUsersResource().delete(userId);
    }

    @Override
    public void resetPassword(String userId, String newPassword, boolean temporary) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(newPassword);
        credential.setTemporary(temporary);

        UserResource userResource = getUsersResource().get(userId);
        userResource.resetPassword(credential);
    }

    @Override
    public List<KeycloakUserRepresentation> getUsers(int first, int max) {
        List<UserRepresentation> users = getUsersResource().list(first, max);
        return users.stream()
                .map(this::convertToCustomRepresentation)
                .collect(Collectors.toList());
    }
}