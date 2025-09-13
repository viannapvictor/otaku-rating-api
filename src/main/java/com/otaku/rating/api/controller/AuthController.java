package com.otaku.rating.api.controller;

import com.otaku.rating.api.config.SecurityConfig;
import com.otaku.rating.api.request.user.dto.*;
import com.otaku.rating.api.response.ApiResponse;
import com.otaku.rating.api.response.user.dto.UserViewDTO;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.generic.mapper.OutputMapper;
import com.otaku.rating.core.user.model.AuthTokens;
import com.otaku.rating.core.user.model.EmailConfirmationRequest;
import com.otaku.rating.core.user.model.PasswordResetConfirm;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.UserLogin;
import com.otaku.rating.core.user.model.UserRegister;
import com.otaku.rating.core.user.model.valueobject.Email;
import com.otaku.rating.core.user.service.PasswordResetService;
import com.otaku.rating.core.user.service.UserService;
import com.otaku.rating.core.user.decorator.NeedsUserContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final OutputMapper<User, UserViewDTO> userOutputMapper;
    private final InputMapper<UserRegister, UserRegisterDTO> createUserMapper;
    private final InputMapper<UserLogin, UserLoginDTO> userLoginMapper;
    private final InputMapper<EmailConfirmationRequest, EmailConfirmationRequestDTO> emailConfirmationRequestMapper;
    private final InputMapper<Email, PasswordResetRequestDTO> passwordResetRequestMapper;
    private final InputMapper<PasswordResetConfirm, PasswordResetConfirmDTO> passwordResetConfirmMapper;
    private final SecurityConfig securityConfig;
    private final PasswordResetService passwordResetService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserViewDTO>> register(@RequestBody UserRegisterDTO userRegisterDTO) {
        UserRegister userRegister = createUserMapper.toModel(userRegisterDTO);
        User createdUser = userService.createUser(userRegister);
        UserViewDTO userViewDTO = userOutputMapper.toEntity(createdUser);
        return ApiResponse.success(userViewDTO).createResponse(HttpStatus.CREATED);
    }
    @PostMapping("/confirm-email")
    @NeedsUserContext
    public ResponseEntity<ApiResponse<Object>> confirmEmail(
            @RequestBody EmailConfirmationRequestDTO emailConfirmationRequestDto,
            HttpServletResponse response
    ) {
        EmailConfirmationRequest emailConfirmationRequest = emailConfirmationRequestMapper.toModel(emailConfirmationRequestDto);
        userService.confirmEmail(
                emailConfirmationRequest.getCode(),
                emailConfirmationRequest.getEmail()
        );
        userService.logout();

        Cookie refreshCookie = securityConfig.createRefreshTokenCookie(null);
        Cookie accessCookie = securityConfig.createAccessTokenCookie(null);

        response.addCookie(refreshCookie);
        response.addCookie(accessCookie);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }

    @PostMapping("/login")
    @NeedsUserContext
    public ResponseEntity<ApiResponse<Object>> login(@RequestBody UserLoginDTO userLoginDTO, HttpServletResponse response) {
        UserLogin userLogin = userLoginMapper.toModel(userLoginDTO);
        AuthTokens authTokens = userService.login(
                userLogin.getEmail(),
                userLogin.getPassword()
        );
        Cookie refreshCookie = securityConfig.createRefreshTokenCookie(authTokens.getRefreshToken());
        Cookie accessCookie = securityConfig.createAccessTokenCookie(authTokens.getAccessToken());

        response.addCookie(refreshCookie);
        response.addCookie(accessCookie);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }

    @PostMapping("/logout")
    @NeedsUserContext
    public ResponseEntity<ApiResponse<Object>> logout(HttpServletResponse response) {
        userService.logout();
        Cookie refreshCookie = securityConfig.createRefreshTokenCookie(null);
        Cookie accessCookie = securityConfig.createAccessTokenCookie(null);

        response.addCookie(refreshCookie);
        response.addCookie(accessCookie);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }

    @PostMapping("/refresh-tokens")
    @NeedsUserContext
    public ResponseEntity<ApiResponse<Object>> refreshTokens(HttpServletResponse response) {
        AuthTokens authTokens = userService.getContext().refreshTokens();

        Cookie refreshCookie = securityConfig.createRefreshTokenCookie(authTokens.getRefreshToken());
        Cookie accessCookie = securityConfig.createAccessTokenCookie(authTokens.getAccessToken());

        response.addCookie(refreshCookie);
        response.addCookie(accessCookie);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse<Object>> requestPasswordReset(@RequestBody PasswordResetRequestDTO requestDTO) {
        Email email = passwordResetRequestMapper.toModel(requestDTO);
        User user = userService.findByEmail(email);
        passwordResetService.createPasswordResetRequest(user);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse<Object>> resetPassword(@RequestBody PasswordResetConfirmDTO confirmDTO) {
        PasswordResetConfirm passwordResetConfirm = passwordResetConfirmMapper.toModel(confirmDTO);
        userService.resetPassword(
                passwordResetConfirm.getCode(),
                passwordResetConfirm.getNewPassword()
        );
        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }
}