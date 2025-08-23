package com.otaku.rating.api.controller;

import com.otaku.rating.api.request.anime.dto.AnimeCreateDTO;
import com.otaku.rating.api.request.generic.PageRequest;
import com.otaku.rating.api.response.ApiResponse;
import com.otaku.rating.api.response.anime.dto.AnimeViewDTO;
import com.otaku.rating.api.response.generic.PageResponse;
import com.otaku.rating.core.anime.facade.AnimeFacade;
import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.generic.mapper.OutputMapper;
import com.otaku.rating.core.user.decorator.NeedsUserContext;
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
    private final AnimeFacade animeFacade;
    private final InputMapper<Anime, AnimeCreateDTO> animeCreateMapper;
    private final OutputMapper<Anime, AnimeViewDTO> animeViewMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<AnimeViewDTO>>> getPage(@ModelAttribute PageRequest pageRequest) {
        Page<Anime> animePage = animeFacade.getPage(pageRequest.getPage(), pageRequest.getSize());
        List<AnimeViewDTO> animeViewDTOs = animePage.getContent()
                .stream()
                .map(animeViewMapper::toEntity)
                .toList();
        PageResponse<AnimeViewDTO> pageResponse = PageResponse.from(animePage, animeViewDTOs);
        return ApiResponse.success(pageResponse).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AnimeViewDTO>> getById(@PathVariable("id") String id) {
        Anime anime = animeFacade.getById(id);
        AnimeViewDTO animeViewDTO = animeViewMapper.toEntity(anime);

        return ApiResponse.success(animeViewDTO).createResponse(HttpStatus.OK);
    }

    @PostMapping
    @NeedsUserContext
    public ResponseEntity<ApiResponse<AnimeViewDTO>> create(@RequestBody AnimeCreateDTO form) {
        Anime anime = animeCreateMapper.toModel(form);
        Anime createdAnime = animeFacade.add(anime);
        AnimeViewDTO animeViewDTO = animeViewMapper.toEntity(createdAnime);

        return ApiResponse.success(animeViewDTO).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @NeedsUserContext
    public ResponseEntity<ApiResponse<AnimeViewDTO>> update(@PathVariable("id") String id, @RequestBody AnimeCreateDTO form) {
        Anime anime = animeCreateMapper.toModel(form);
        Anime updatedAnime = animeFacade.update(id, anime);
        AnimeViewDTO animeViewDTO = animeViewMapper.toEntity(updatedAnime);

        return ApiResponse.success(animeViewDTO).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @NeedsUserContext
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable("id") String id) {
        Anime anime = animeFacade.getById(id);
        animeFacade.delete(anime);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }
}
