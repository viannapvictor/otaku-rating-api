package com.otaku.rating.api.controller;

import com.otaku.rating.api.request.user.dto.UserRegisterDTO;
import com.otaku.rating.api.request.user.mapper.UserRequestMapper;
import com.otaku.rating.api.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserRequestMapper userRequestMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPage() {

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable("id") String id) {

    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody UserRegisterDTO form) {

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable("id") String id, @RequestBody UserRegisterDTO form) {

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") String id) {

    }
}