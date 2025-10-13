package com.otaku.rating.user.app.controller;

import com.otaku.rating.generic.app.request.dto.PageRequest;
import com.otaku.rating.generic.core.mapper.InputMapper;
import com.otaku.rating.generic.core.mapper.OutputMapper;
import com.otaku.rating.generic.core.model.ApiResponse;
import com.otaku.rating.generic.core.model.PageResponse;
import com.otaku.rating.user.app.config.SecurityConfig;
import com.otaku.rating.user.app.request.dto.UserUpdateEmailRequestDTO;
import com.otaku.rating.user.app.request.dto.UserUpdateInfoRequestDTO;
import com.otaku.rating.user.app.request.dto.UserUpdatePasswordRequestDTO;
import com.otaku.rating.user.app.response.dto.UserQueryDTO;
import com.otaku.rating.user.app.response.dto.UserViewDTO;
import com.otaku.rating.user.core.decorator.NeedsUserContext;
import com.otaku.rating.user.core.model.User;
import com.otaku.rating.user.core.model.UserUpdateEmailRequest;
import com.otaku.rating.user.core.model.UserUpdateInfoRequest;
import com.otaku.rating.user.core.model.UserUpdatePasswordRequest;
import com.otaku.rating.user.core.model.valueobject.UserName;
import com.otaku.rating.user.core.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
@NeedsUserContext
public class UserController {
    private final UserService userService;
    private final SecurityConfig securityConfig;
    private final OutputMapper<User, UserViewDTO> userOutputMapper;
    private final OutputMapper<User, UserQueryDTO> userQueryViewMapper;
    private final InputMapper<UserUpdateInfoRequest, UserUpdateInfoRequestDTO> userUpdateInfoRequestMapper;
    private final InputMapper<UserUpdateEmailRequest, UserUpdateEmailRequestDTO> userUpdateEmailRequestMapper;
    private final InputMapper<UserUpdatePasswordRequest, UserUpdatePasswordRequestDTO> userUpdatePasswordRequestMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<UserViewDTO>>> getUsersByPage(@ModelAttribute PageRequest pageRequest) {
        Page<User> userPage = userService.getPage(pageRequest.getPage(), pageRequest.getSize());
        List<UserViewDTO> userViewDTOs = userPage.getContent()
                .stream()
                .map(userOutputMapper::toEntity)
                .toList();
        PageResponse<UserViewDTO> pageResponse = PageResponse.from(userPage, userViewDTOs);
        return ApiResponse.success(pageResponse).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserViewDTO>> getUserById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        UserViewDTO userViewDTO = userOutputMapper.toEntity(user);
        return ApiResponse.success(userViewDTO).createResponse(HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ApiResponse<UserQueryDTO>> getUserByUsername(@PathVariable("username") String username) {
        User targetUser = userService.findByUserName(UserName.valueOf(username));
        UserQueryDTO dto = userQueryViewMapper.toEntity(targetUser);
        return ApiResponse.success(dto).createResponse(HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserViewDTO>> getSelf() {
        User user = userService.getContext().getUserOrThrow();
        UserViewDTO userViewDTO = userOutputMapper.toEntity(user);
        return ApiResponse.success(userViewDTO).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<Object>> deleteSelf(HttpServletResponse response) {
        userService.deleteUser(userService.getContext().getUserOrThrow());
        Cookie refreshCookie = securityConfig.createRefreshTokenCookie(null);
        Cookie accessCookie = securityConfig.createAccessTokenCookie(null);
        response.addCookie(refreshCookie);
        response.addCookie(accessCookie);
        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }

    @PutMapping("/me/info")
    public ResponseEntity<ApiResponse<UserViewDTO>> updateProfile(@RequestBody UserUpdateInfoRequestDTO requestDTO) {
        UserUpdateInfoRequest request = userUpdateInfoRequestMapper.toModel(requestDTO);
        User updatedUser = userService.updateUserProfile(
                request.getName(),
                request.getUserName()
        );
        UserViewDTO userViewDTO = userOutputMapper.toEntity(updatedUser);
        return ApiResponse.success(userViewDTO).createResponse(HttpStatus.OK);
    }

    @PutMapping("/me/email")
    public ResponseEntity<ApiResponse<Object>> updateEmail(@RequestBody UserUpdateEmailRequestDTO requestDTO) {
        UserUpdateEmailRequest request = userUpdateEmailRequestMapper.toModel(requestDTO);
        userService.updateUserEmail(request.getEmail());
        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }

    @PutMapping("/me/password")
    public ResponseEntity<ApiResponse<Object>> updatePassword(
            @RequestBody UserUpdatePasswordRequestDTO requestDTO,
            HttpServletResponse response
    ) {
        UserUpdatePasswordRequest dto = userUpdatePasswordRequestMapper.toModel(requestDTO);
        userService.updateUserPassword(dto.getOldPassword(), dto.getNewPassword());
        Cookie refreshCookie = securityConfig.createRefreshTokenCookie(null);
        Cookie accessCookie = securityConfig.createAccessTokenCookie(null);

        response.addCookie(refreshCookie);
        response.addCookie(accessCookie);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }
}