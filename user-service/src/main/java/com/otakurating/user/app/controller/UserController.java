package com.otakurating.user.app.controller;

import com.otakurating.user.app.request.dto.PageRequest;
import com.otakurating.user.app.request.dto.UserCreateRequestDTO;
import com.otakurating.user.app.request.dto.UserUpdateInfoRequestDTO;
import com.otakurating.user.app.response.dto.ApiResponse;
import com.otakurating.user.app.response.dto.PageResponse;
import com.otakurating.user.core.model.KeycloakUserRepresentation;
import com.otakurating.user.core.service.KeycloakAdminService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final KeycloakAdminService keycloakAdminService;

    @PostMapping
    @RolesAllowed("ADMIN")
    public ResponseEntity<ApiResponse<KeycloakUserRepresentation>> createUser(@RequestBody UserCreateRequestDTO requestDTO) {
        KeycloakUserRepresentation keycloakUser = new KeycloakUserRepresentation();
        keycloakUser.setUsername(requestDTO.getUsername());
        keycloakUser.setEmail(requestDTO.getEmail());
        keycloakUser.setFirstName(requestDTO.getFirstName());
        keycloakUser.setLastName(requestDTO.getLastName());
        keycloakUser.setEnabled(true);
        keycloakUser.setEmailVerified(false);

        String userId = keycloakAdminService.createUser(keycloakUser);

        if (requestDTO.getPassword() != null && !requestDTO.getPassword().isEmpty()) {
            keycloakAdminService.resetPassword(userId, requestDTO.getPassword(), false);
        }

        KeycloakUserRepresentation createdUser = keycloakAdminService.getUser(userId);
        return ApiResponse.success(createdUser).createResponse(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<KeycloakUserRepresentation>>> getUsersByPage(@ModelAttribute PageRequest pageRequest) {
        int first = pageRequest.getPage() * pageRequest.getSize();
        int max = pageRequest.getSize();

        List<KeycloakUserRepresentation> users = keycloakAdminService.getUsers(first, max);

        PageResponse<KeycloakUserRepresentation> pageResponse = new PageResponse<>();
        pageResponse.setContent(users);
        pageResponse.setSize(max);
        pageResponse.setPage(pageRequest.getPage() + 1);
        pageResponse.setTotalElements(users.size());
        pageResponse.setTotalPages(users.isEmpty() ? 0 : (users.size() < max ? pageRequest.getPage() + 1 : pageRequest.getPage() + 2));
        pageResponse.setFirst(pageRequest.getPage() == 0);
        pageResponse.setLast(users.size() < max);

        return ApiResponse.success(pageResponse).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<KeycloakUserRepresentation>> getUserById(@PathVariable("id") String id) {
        KeycloakUserRepresentation user = keycloakAdminService.getUser(id);
        return ApiResponse.success(user).createResponse(HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<KeycloakUserRepresentation>> getUserByUsername(@PathVariable("username") String username) {
        List<KeycloakUserRepresentation> users = keycloakAdminService.getUserByUsername(username);
        if (users.isEmpty()) {
            return ApiResponse.<KeycloakUserRepresentation>error("USER_NOT_FOUND", "User not found").createResponse(HttpStatus.NOT_FOUND);
        }
        return ApiResponse.success(users.getFirst()).createResponse(HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<KeycloakUserRepresentation>> getUserByEmail(@PathVariable("email") String email) {
        List<KeycloakUserRepresentation> users = keycloakAdminService.getUserByEmail(email);
        if (users.isEmpty()) {
            return ApiResponse.<KeycloakUserRepresentation>error("USER_NOT_FOUND", "User not found").createResponse(HttpStatus.NOT_FOUND);
        }
        return ApiResponse.success(users.getFirst()).createResponse(HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<KeycloakUserRepresentation>> getSelf() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            String userId = jwt.getSubject();
            KeycloakUserRepresentation user = keycloakAdminService.getUser(userId);
            return ApiResponse.success(user).createResponse(HttpStatus.OK);
        }
        return ApiResponse.<KeycloakUserRepresentation>error("UNAUTHORIZED", "User not authenticated").createResponse(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<KeycloakUserRepresentation>> updateSelf(@RequestBody UserUpdateInfoRequestDTO requestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            String userId = jwt.getSubject();

            KeycloakUserRepresentation updateRequest = new KeycloakUserRepresentation();
            updateRequest.setFirstName(requestDTO.getFirstName());
            updateRequest.setLastName(requestDTO.getLastName());
            updateRequest.setEmail(requestDTO.getEmail());

            keycloakAdminService.updateUser(userId, updateRequest);
            KeycloakUserRepresentation updatedUser = keycloakAdminService.getUser(userId);
            return ApiResponse.success(updatedUser).createResponse(HttpStatus.OK);
        }
        return ApiResponse.<KeycloakUserRepresentation>error("UNAUTHORIZED", "User not authenticated").createResponse(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<Object>> deleteSelf() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            String userId = jwt.getSubject();
            keycloakAdminService.deleteUser(userId);
            return ApiResponse.success(null).createResponse(HttpStatus.OK);
        }
        return ApiResponse.error("UNAUTHORIZED", "User not authenticated").createResponse(HttpStatus.UNAUTHORIZED);
    }
}