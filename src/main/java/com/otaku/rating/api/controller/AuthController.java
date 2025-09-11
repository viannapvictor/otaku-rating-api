package com.otaku.rating.api.controller;

import com.otaku.rating.api.config.SecurityConfig;
import com.otaku.rating.api.request.user.dto.EmailConfirmationRequestDTO;
import com.otaku.rating.api.request.user.dto.UserLoginDTO;
import com.otaku.rating.api.request.user.dto.UserRegisterDTO;
import com.otaku.rating.api.response.ApiResponse;
import com.otaku.rating.api.response.user.dto.UserViewDTO;
import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.generic.mapper.OutputMapper;
import com.otaku.rating.core.user.model.AuthTokens;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.UserRegister;
import com.otaku.rating.core.user.model.valueobjects.Email;
import com.otaku.rating.core.user.model.valueobjects.Password;
import com.otaku.rating.core.user.service.UserService;
import com.otaku.rating.core.user.service.decorators.NeedsUserContext;
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
    private final SecurityConfig securityConfig;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserViewDTO>> register(
            @RequestBody UserRegisterDTO userRegisterDTO
    ) throws ValidationException {
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
    ) throws ValidationException {
        boolean hasEmail = emailConfirmationRequestDto.getEmail() != null;
        userService.confirmEmail(
                emailConfirmationRequestDto.getCode(),
                hasEmail ? new Email(emailConfirmationRequestDto.getEmail()) : null
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
    public ResponseEntity<ApiResponse<Object>> login(
            @RequestBody UserLoginDTO userLoginDTO,
            HttpServletResponse response
    ) throws ValidationException {
        AuthTokens authTokens = userService.login(
                new Email(userLoginDTO.getEmail()),
                new Password(userLoginDTO.getPassword())
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
}