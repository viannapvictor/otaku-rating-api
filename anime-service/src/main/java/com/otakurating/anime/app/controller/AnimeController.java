package com.otakurating.anime.app.controller;

import com.otakurating.anime.app.request.dto.AnimeCreateDTO;
import com.otakurating.anime.app.request.dto.AnimePageRequestDTO;
import com.otakurating.anime.app.request.dto.AnimeUpdateDTO;
import com.otakurating.anime.app.response.dto.AnimeViewDTO;
import com.otakurating.anime.app.response.dto.ApiResponse;
import com.otakurating.anime.app.response.dto.PageResponse;
import com.otakurating.anime.app.response.mapper.AnimeViewMapper;
import com.otakurating.anime.core.command.*;
import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.port.in.*;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/anime")
public class AnimeController {
    private final CreateAnimeUseCase createAnimeUseCase;
    private final UpdateAnimeUseCase updateAnimeUseCase;
    private final DeleteAnimeUseCase deleteAnimeUseCase;
    private final GetAnimePageUseCase getAnimePageUseCase;
    private final GetAnimeUseCase getAnimeUseCase;
    private final AnimeViewMapper animeViewMapper;

    public AnimeController(
            CreateAnimeUseCase createAnimeUseCase,
            UpdateAnimeUseCase updateAnimeUseCase,
            DeleteAnimeUseCase deleteAnimeUseCase,
            GetAnimePageUseCase getAnimePageUseCase,
            GetAnimeUseCase getAnimeUseCase,
            AnimeViewMapper animeViewMapper
    ) {
        this.createAnimeUseCase = createAnimeUseCase;
        this.updateAnimeUseCase = updateAnimeUseCase;
        this.deleteAnimeUseCase = deleteAnimeUseCase;
        this.getAnimePageUseCase = getAnimePageUseCase;
        this.getAnimeUseCase = getAnimeUseCase;
        this.animeViewMapper = animeViewMapper;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<AnimeViewDTO>>> getPage(
            @ModelAttribute AnimePageRequestDTO pageRequest
    ) {
        GetAnimePageCommand command = new GetAnimePageCommand(
                pageRequest.page(),
                pageRequest.size(),
                pageRequest.title(),
                pageRequest.description()
        );
        Page<Anime> animePage = getAnimePageUseCase.getPage(command);
        List<AnimeViewDTO> animeViewDTOs = animePage.getContent()
                .stream()
                .map(animeViewMapper::toEntity)
                .toList();
        PageResponse<AnimeViewDTO> pageResponse = PageResponse.from(animePage, animeViewDTOs);
        return ApiResponse.success(pageResponse).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<AnimeViewDTO>> getById(@PathVariable("id") UUID id) {
        GetAnimeCommand command = new GetAnimeCommand(id);
        Anime anime = getAnimeUseCase.getById(command);
        AnimeViewDTO view = animeViewMapper.toEntity(anime);

        return ApiResponse.success(view).createResponse(HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<ApiResponse<AnimeViewDTO>> create(@RequestBody AnimeCreateDTO form) {
        CreateAnimeCommand command = new CreateAnimeCommand(form.title(), form.description(), form.release());
        Anime anime = createAnimeUseCase.create(command);
        AnimeViewDTO view = animeViewMapper.toEntity(anime);

        return ApiResponse.success(view).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<ApiResponse<AnimeViewDTO>> update(
            @PathVariable("id") UUID id,
            @RequestBody AnimeUpdateDTO form
    ) {
        UpdateAnimeCommand command = new UpdateAnimeCommand(id, form.title(), form.description(), form.release());
        Anime anime = updateAnimeUseCase.update(command);
        AnimeViewDTO view = animeViewMapper.toEntity(anime);

        return ApiResponse.success(view).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable("id") UUID id) {
        DeleteAnimeCommand command = new DeleteAnimeCommand(id);
        deleteAnimeUseCase.delete(command);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }
}
