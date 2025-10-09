package com.otaku.rating.api.controller;

import com.otaku.rating.api.request.generic.PageRequest;
import com.otaku.rating.api.response.ApiResponse;
import com.otaku.rating.api.response.generic.PageResponse;
import com.otaku.rating.api.response.user.dto.UserQueryDTO;
import com.otaku.rating.api.request.user.dto.UserUpdateInfoRequestDTO;
import com.otaku.rating.api.response.user.dto.UserViewDTO;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.generic.mapper.OutputMapper;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.UserUpdateInfoRequest;
import com.otaku.rating.core.user.model.valueobject.UserName;
import com.otaku.rating.core.user.service.UserService;
import com.otaku.rating.core.user.decorator.NeedsUserContext;
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
    private final OutputMapper<User, UserViewDTO> userOutputMapper;
    private final OutputMapper<User, UserQueryDTO> userQueryViewMapper;
    private final InputMapper<UserUpdateInfoRequest, UserUpdateInfoRequestDTO> userUpdateInfoRequestMapper;

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
    public ResponseEntity<ApiResponse<Object>> deleteSelf() {
        userService.deleteUser(userService.getContext().getUserOrThrow());
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
}