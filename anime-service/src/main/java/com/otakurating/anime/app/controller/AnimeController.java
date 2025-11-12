package com.otakurating.anime.app.controller;

import com.otakurating.anime.app.request.dto.AnimeCreateDTO;
import com.otakurating.anime.app.request.dto.AnimePageRequestDTO;
import com.otakurating.anime.app.request.mapper.AnimeCreateMapper;
import com.otakurating.anime.app.response.dto.AnimeViewDTO;
import com.otakurating.anime.app.response.dto.ApiResponse;
import com.otakurating.anime.app.response.dto.PageResponse;
import com.otakurating.anime.app.response.mapper.AnimeViewMapper;
import com.otakurating.anime.core.facade.AnimeFacade;
import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.strategy.AnimeSearchStrategy;
import com.otakurating.anime.core.strategy.DescriptionAnimeSearchStrategy;
import com.otakurating.anime.core.strategy.TitleAnimeSearchStrategy;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/anime")
public class AnimeController {
    private final AnimeFacade animeFacade;
    private final AnimeCreateMapper animeCreateMapper;
    private final AnimeViewMapper animeViewMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<AnimeViewDTO>>> getPage(@ModelAttribute AnimePageRequestDTO pageRequest) {
        List<AnimeSearchStrategy> strategies = new ArrayList<>();
        if (pageRequest.getTitle() != null && !pageRequest.getTitle().isBlank()) {
            strategies.add(new TitleAnimeSearchStrategy(pageRequest.getTitle()));
        }
        if (pageRequest.getDescription() != null && !pageRequest.getDescription().isBlank()) {
            strategies.add(new DescriptionAnimeSearchStrategy(pageRequest.getDescription()));
        }
        Page<Anime> animePage = animeFacade.getPage(pageRequest.getPage(), pageRequest.getSize(), strategies);
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
    @RolesAllowed({"ADMIN", "MODERATOR"})
    public ResponseEntity<ApiResponse<AnimeViewDTO>> create(@RequestBody AnimeCreateDTO form) {
        Anime anime = animeCreateMapper.toModel(form);
        Anime createdAnime = animeFacade.add(anime);
        AnimeViewDTO animeViewDTO = animeViewMapper.toEntity(createdAnime);

        return ApiResponse.success(animeViewDTO).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ADMIN", "MODERATOR"})
    public ResponseEntity<ApiResponse<AnimeViewDTO>> update(@PathVariable("id") String id, @RequestBody AnimeCreateDTO form) {
        Anime anime = animeCreateMapper.toModel(form);
        Anime updatedAnime = animeFacade.update(id, anime);
        AnimeViewDTO animeViewDTO = animeViewMapper.toEntity(updatedAnime);

        return ApiResponse.success(animeViewDTO).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN", "MODERATOR"})
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable("id") String id) {
        Anime anime = animeFacade.getById(id);
        animeFacade.delete(anime);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }
}
