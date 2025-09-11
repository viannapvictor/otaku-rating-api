package com.otaku.rating.api.controller;

import com.otaku.rating.api.request.user.dto.UserRegisterDTO;
import com.otaku.rating.api.response.ApiResponse;
import com.otaku.rating.api.response.user.dto.UserViewDTO;
import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.generic.mapper.OutputMapper;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.UserRegister;
import com.otaku.rating.core.user.service.UserService;
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

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserViewDTO>> register(
            @RequestBody UserRegisterDTO userRegisterDTO
    ) throws ValidationException {
        UserRegister userRegister = createUserMapper.toModel(userRegisterDTO);
        User createdUser = userService.createUser(userRegister);
        UserViewDTO userViewDTO = userOutputMapper.toEntity(createdUser);
        return ApiResponse.success(userViewDTO).createResponse(HttpStatus.CREATED);
    }
}
