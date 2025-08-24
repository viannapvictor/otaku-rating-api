package com.otaku.rating.api.controller;

import com.otaku.rating.api.request.anime.dto.AnimeCreateDTO;
import com.otaku.rating.api.request.generic.PageRequest;
import com.otaku.rating.api.response.ApiResponse;
import com.otaku.rating.api.response.anime.dto.AnimeViewDTO;
import com.otaku.rating.api.response.generic.PageResponse;
import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.anime.service.AnimeService;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.generic.mapper.OutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/anime")
public class AnimeController {
    private final AnimeService animeService;
    private final InputMapper<Anime, AnimeCreateDTO> animeInputMapper;
    private final OutputMapper<Anime, AnimeViewDTO> animeOutputMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<AnimeViewDTO>>> getPage(@ModelAttribute PageRequest pageRequest) {
        Page<Anime> animePage = animeService.getPage(pageRequest.getPage(), pageRequest.getSize());
        List<AnimeViewDTO> animeViewDTOs = animePage.getContent()
                .stream()
                .map(animeOutputMapper::toEntity)
                .toList();
        PageResponse<AnimeViewDTO> pageResponse = PageResponse.from(animePage, animeViewDTOs);
        return ApiResponse.success(pageResponse).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AnimeViewDTO>> getById(@PathVariable("id") String id) {
        Anime anime = animeService.getById(id);
        AnimeViewDTO animeViewDTO = animeOutputMapper.toEntity(anime);

        return ApiResponse.success(animeViewDTO).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AnimeViewDTO>> create(@RequestBody AnimeCreateDTO form) {
        Anime anime = animeInputMapper.toModel(form);
        Anime createdAnime = animeService.add(anime);
        AnimeViewDTO animeViewDTO = animeOutputMapper.toEntity(createdAnime);

        return ApiResponse.success(animeViewDTO).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<AnimeViewDTO>> update(@PathVariable("id") String id, @RequestBody AnimeCreateDTO form) {
        Anime anime = animeInputMapper.toModel(form);
        Anime updatedAnime = animeService.update(anime);
        AnimeViewDTO animeViewDTO = animeOutputMapper.toEntity(updatedAnime);

        return ApiResponse.success(animeViewDTO).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable("id") String id) {
        Anime anime = animeService.getById(id);
        animeService.delete(anime);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }
}
