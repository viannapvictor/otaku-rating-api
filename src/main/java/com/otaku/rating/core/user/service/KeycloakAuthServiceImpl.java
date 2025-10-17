package com.otaku.rating.core.user.service;

import com.otaku.rating.api.response.auth.dto.TokenResponseDTO;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KeycloakAuthServiceImpl implements KeycloakAuthService {

    private final Keycloak keycloak;
    private final RestTemplate restTemplate = new RestTemplate();
    private final KeycloakAdminService keycloakAdminService;

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id:otaku-rating-client}")
    private String clientId;

    @Value("${keycloak.client-secret:}")
    private String clientSecret;

    private String getTokenEndpoint() {
        return authServerUrl + "/realms/" + realm + "/protocol/openid-connect/token";
    }

    private String getLogoutEndpoint() {
        return authServerUrl + "/realms/" + realm + "/protocol/openid-connect/logout";
    }

    @Override
    public TokenResponseDTO login(String usernameOrEmail, String password) {
        try {
            String tokenEndpoint = getTokenEndpoint();
            System.out.println("=== DEBUG LOGIN ===");
            System.out.println("Token Endpoint: " + tokenEndpoint);
            System.out.println("Client ID: " + clientId);
            System.out.println("Client Secret: " + (clientSecret != null && !clientSecret.isEmpty() ? "***SET***" : "NOT SET"));
            System.out.println("Username: " + usernameOrEmail);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "password");
            body.add("client_id", clientId);

            if (clientSecret != null && !clientSecret.isEmpty()) {
                body.add("client_secret", clientSecret);
            }

            body.add("username", usernameOrEmail);
            body.add("password", password);
            body.add("scope", "openid profile email");

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

            ResponseEntity<TokenResponseDTO> response = restTemplate.exchange(
                    tokenEndpoint,
                    HttpMethod.POST,
                    request,
                    TokenResponseDTO.class
            );

            System.out.println("Login successful!");
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.err.println("=== LOGIN ERROR ===");
            System.err.println("Status: " + e.getStatusCode());
            System.err.println("Response: " + e.getResponseBodyAsString());
            throw new RuntimeException("Failed to authenticate: " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            System.err.println("=== LOGIN EXCEPTION ===");
            e.printStackTrace();
            throw new RuntimeException("Failed to authenticate: " + e.getMessage(), e);
        }
    }

    @Override
    public TokenResponseDTO refreshToken(String refreshToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("grant_type", "refresh_token");
            body.add("client_id", clientId);

            if (clientSecret != null && !clientSecret.isEmpty()) {
                body.add("client_secret", clientSecret);
            }

            body.add("refresh_token", refreshToken);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

            ResponseEntity<TokenResponseDTO> response = restTemplate.exchange(
                    getTokenEndpoint(),
                    HttpMethod.POST,
                    request,
                    TokenResponseDTO.class
            );

            return response.getBody();
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Failed to refresh token: " + e.getResponseBodyAsString(), e);
        }
    }

    @Override
    public void logout(String refreshToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

            MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
            body.add("client_id", clientId);

            if (clientSecret != null && !clientSecret.isEmpty()) {
                body.add("client_secret", clientSecret);
            }

            body.add("refresh_token", refreshToken);

            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);

            restTemplate.exchange(
                    getLogoutEndpoint(),
                    HttpMethod.POST,
                    request,
                    Void.class
            );
        } catch (HttpClientErrorException e) {
            throw new RuntimeException("Failed to logout: " + e.getResponseBodyAsString(), e);
        }
    }

    @Override
    public void sendPasswordResetEmail(String email) {
        try {
            List<com.otaku.rating.core.user.model.KeycloakUserRepresentation> users =
                    keycloakAdminService.getUserByEmail(email);

            if (users.isEmpty()) {
                throw new RuntimeException("User not found with email: " + email);
            }

            String userId = users.get(0).getId();

            UserResource userResource = keycloak.realm(realm).users().get(userId);
            userResource.executeActionsEmail(List.of("UPDATE_PASSWORD"));

        } catch (Exception e) {
            throw new RuntimeException("Failed to send password reset email: " + e.getMessage(), e);
        }
    }

    @Override
    public void sendEmailVerification(String userId) {
        try {
            UserResource userResource = keycloak.realm(realm).users().get(userId);
            userResource.sendVerifyEmail();
        } catch (Exception e) {
            throw new RuntimeException("Failed to send email verification: " + e.getMessage(), e);
        }
    }
}