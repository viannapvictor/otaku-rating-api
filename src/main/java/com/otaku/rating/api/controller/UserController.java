package com.otaku.rating.api.controller;

import com.otaku.rating.api.request.generic.PageRequest;
import com.otaku.rating.api.request.user.dto.UserRegisterDTO;
import com.otaku.rating.api.response.ApiResponse;
import com.otaku.rating.api.response.generic.PageResponse;
import com.otaku.rating.api.response.user.dto.UserViewDTO;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.generic.mapper.OutputMapper;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final InputMapper<User, UserRegisterDTO> userInputMapper;
    private final OutputMapper<User, UserViewDTO> userOutputMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<UserViewDTO>>> getPage(@ModelAttribute PageRequest pageRequest) {
        Page<User> userPage = userService.getPage(pageRequest.getPage(), pageRequest.getSize());
        List<UserViewDTO> userViewDTOs = userPage.getContent()
                .stream()
                .map(userOutputMapper::toEntity)
                .toList();
        PageResponse<UserViewDTO> pageResponse = PageResponse.from(userPage, userViewDTOs);
        return ApiResponse.success(pageResponse).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserViewDTO>> getById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        UserViewDTO userViewDTO = userOutputMapper.toEntity(user);
        
        return ApiResponse.success(userViewDTO).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UserViewDTO>> create(@RequestBody UserRegisterDTO form) {
        User createdUser = userService.createUser(form.convertToEntity());
        UserViewDTO userViewDTO = userOutputMapper.toEntity(createdUser);

        return ApiResponse.success(userViewDTO).createResponse(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        userService.deleteUser(user);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }
}