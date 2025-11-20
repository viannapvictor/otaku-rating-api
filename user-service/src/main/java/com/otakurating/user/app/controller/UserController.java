package com.otakurating.user.app.controller;

import com.otakurating.user.app.request.dto.PageRequest;
import com.otakurating.user.app.request.dto.UserCreateRequestDTO;
import com.otakurating.user.app.request.dto.UserUpdateInfoRequestDTO;
import com.otakurating.user.app.response.dto.ApiResponse;
import com.otakurating.user.app.response.dto.PageResponse;
import com.otakurating.user.core.command.CreateUserCommand;
import com.otakurating.user.core.command.DeleteUserCommand;
import com.otakurating.user.core.command.GetUserCommand;
import com.otakurating.user.core.command.UpdateUserCommand;
import com.otakurating.user.core.model.KeycloakUserRepresentation;
import com.otakurating.user.core.port.in.CreateUserUseCase;
import com.otakurating.user.core.port.in.DeleteUserUseCase;
import com.otakurating.user.core.port.in.GetUserUseCase;
import com.otakurating.user.core.port.in.UpdateUserUseCase;
import com.otakurating.user.core.port.out.UserAdminPort;
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
    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UserAdminPort userAdminPort;

    @PostMapping
    public ResponseEntity<ApiResponse<KeycloakUserRepresentation>> createUser(@RequestBody UserCreateRequestDTO requestDTO) {
        CreateUserCommand command = new CreateUserCommand(
            requestDTO.getUsername(),
            requestDTO.getEmail(),
            requestDTO.getFirstName(),
            requestDTO.getLastName(),
            requestDTO.getPassword(),
            List.of("COMMON"),
            true,
            false
        );

        KeycloakUserRepresentation createdUser = createUserUseCase.execute(command);
        return ApiResponse.success(createdUser).createResponse(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<KeycloakUserRepresentation>>> getUsersByPage(@ModelAttribute PageRequest pageRequest) {
        int first = pageRequest.getPage() * pageRequest.getSize();
        int max = pageRequest.getSize();

        List<KeycloakUserRepresentation> users = userAdminPort.getUsers(first, max);

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
        GetUserCommand command = new GetUserCommand(id);
        KeycloakUserRepresentation user = getUserUseCase.execute(command);
        return ApiResponse.success(user).createResponse(HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<KeycloakUserRepresentation>> getUserByUsername(@PathVariable("username") String username) {
        List<KeycloakUserRepresentation> users = userAdminPort.getUserByUsername(username);
        if (users.isEmpty()) {
            return ApiResponse.<KeycloakUserRepresentation>error("USER_NOT_FOUND", "User not found").createResponse(HttpStatus.NOT_FOUND);
        }
        return ApiResponse.success(users.getFirst()).createResponse(HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse<KeycloakUserRepresentation>> getUserByEmail(@PathVariable("email") String email) {
        List<KeycloakUserRepresentation> users = userAdminPort.getUserByEmail(email);
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
            GetUserCommand command = new GetUserCommand(userId);
            KeycloakUserRepresentation user = getUserUseCase.execute(command);
            return ApiResponse.success(user).createResponse(HttpStatus.OK);
        }
        return ApiResponse.<KeycloakUserRepresentation>error("UNAUTHORIZED", "User not authenticated").createResponse(HttpStatus.UNAUTHORIZED);
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<KeycloakUserRepresentation>> updateSelf(@RequestBody UserUpdateInfoRequestDTO requestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            String userId = jwt.getSubject();

            UpdateUserCommand command = new UpdateUserCommand(
                userId,
                requestDTO.getEmail(),
                requestDTO.getFirstName(),
                requestDTO.getLastName()
            );

            KeycloakUserRepresentation updatedUser = updateUserUseCase.execute(command);
            return ApiResponse.success(updatedUser).createResponse(HttpStatus.OK);
        }
        return ApiResponse.<KeycloakUserRepresentation>error("UNAUTHORIZED", "User not authenticated").createResponse(HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<Object>> deleteSelf() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof Jwt jwt) {
            String userId = jwt.getSubject();
            DeleteUserCommand command = new DeleteUserCommand(userId);
            deleteUserUseCase.execute(command);
            return ApiResponse.success(null).createResponse(HttpStatus.OK);
        }
        return ApiResponse.error("UNAUTHORIZED", "User not authenticated").createResponse(HttpStatus.UNAUTHORIZED);
    }
}