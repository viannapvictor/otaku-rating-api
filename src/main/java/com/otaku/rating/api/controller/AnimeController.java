package com.otaku.rating.api.controller;

import com.otaku.rating.api.request.anime.dto.AnimeCreateDTO;
import com.otaku.rating.api.request.anime.mapper.AnimeRequestMapper;
import com.otaku.rating.api.response.ApiResponse;
import com.otaku.rating.core.anime.service.AnimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/anime")
public class AnimeController {
    private final AnimeService animeService;
    private final AnimeRequestMapper animeRequestMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<?>> getPage() {

    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> getById(@PathVariable("id") String id) {

    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> create(@RequestBody AnimeCreateDTO form) {

    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> update(@PathVariable("id") String id, @RequestBody AnimeCreateDTO form) {

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> delete(@PathVariable("id") String id) {

    }
}
