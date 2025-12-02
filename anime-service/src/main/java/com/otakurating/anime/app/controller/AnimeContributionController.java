package com.otakurating.anime.app.controller;

import com.otakurating.anime.app.request.dto.AnimeContributionCreateDTO;
import com.otakurating.anime.app.request.dto.AnimeContributionUpdateDTO;
import com.otakurating.anime.app.response.dto.AnimeContributionViewDTO;
import com.otakurating.anime.app.response.dto.ApiResponse;
import com.otakurating.anime.app.response.mapper.AnimeContributionViewMapper;
import com.otakurating.anime.core.command.CreateAnimeContributionCommand;
import com.otakurating.anime.core.command.DeleteAnimeContributionCommand;
import com.otakurating.anime.core.command.GetAnimeContributionCommand;
import com.otakurating.anime.core.command.UpdateAnimeContributionCommand;
import com.otakurating.anime.core.model.AnimeContribution;
import com.otakurating.anime.core.port.in.CreateAnimeContributionUseCase;
import com.otakurating.anime.core.port.in.DeleteAnimeContributionUseCase;
import com.otakurating.anime.core.port.in.GetAnimeContributionUseCase;
import com.otakurating.anime.core.port.in.UpdateAnimeContributionUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/anime-contribution")
public class AnimeContributionController {
    private final CreateAnimeContributionUseCase createAnimeContributionUseCase;
    private final UpdateAnimeContributionUseCase updateAnimeContributionUseCase;
    private final DeleteAnimeContributionUseCase deleteAnimeContributionUseCase;
    private final GetAnimeContributionUseCase getAnimeContributionUseCase;
    private final AnimeContributionViewMapper animeContributionViewMapper;

    public AnimeContributionController(
            CreateAnimeContributionUseCase createAnimeContributionUseCase, UpdateAnimeContributionUseCase updateAnimeContributionUseCase, DeleteAnimeContributionUseCase deleteAnimeContributionUseCase, GetAnimeContributionUseCase getAnimeContributionUseCase, AnimeContributionViewMapper animeContributionViewMapper
    ) {
        this.createAnimeContributionUseCase = createAnimeContributionUseCase;
        this.updateAnimeContributionUseCase = updateAnimeContributionUseCase;
        this.deleteAnimeContributionUseCase = deleteAnimeContributionUseCase;
        this.getAnimeContributionUseCase = getAnimeContributionUseCase;
        this.animeContributionViewMapper = animeContributionViewMapper;
    }

    @GetMapping("/{animeId}/{personId}")
    public ResponseEntity<ApiResponse<AnimeContributionViewDTO>> getById(
            @PathVariable("animeId") UUID animeId,
            @PathVariable("personId") UUID personId
    ) {
        GetAnimeContributionCommand command = new GetAnimeContributionCommand(animeId, personId);
        AnimeContribution animeContribution = getAnimeContributionUseCase.get(command);
        AnimeContributionViewDTO view = animeContributionViewMapper.toEntity(animeContribution);

        return ApiResponse.success(view).createResponse(HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<ApiResponse<AnimeContributionViewDTO>> create(@RequestBody AnimeContributionCreateDTO form) {
        CreateAnimeContributionCommand command = new CreateAnimeContributionCommand(form.animeId(), form.personId(), form.role());
        AnimeContribution animeContribution = createAnimeContributionUseCase.create(command);
        AnimeContributionViewDTO view = animeContributionViewMapper.toEntity(animeContribution);

        return ApiResponse.success(view).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{animeId}/{personId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<ApiResponse<AnimeContributionViewDTO>> update(
            @PathVariable("animeId") UUID animeId,
            @PathVariable("personId") UUID personId,
            @RequestBody AnimeContributionUpdateDTO form
    ) {
        UpdateAnimeContributionCommand command = new UpdateAnimeContributionCommand(animeId, personId, form.role());
        AnimeContribution animeContribution = updateAnimeContributionUseCase.update(command);
        AnimeContributionViewDTO view = animeContributionViewMapper.toEntity(animeContribution);

        return ApiResponse.success(view).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{animeId}/{personId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MODERATOR')")
    public ResponseEntity<ApiResponse<Object>> delete(
            @PathVariable("animeId") UUID animeId,
            @PathVariable("personId") UUID personId
    ) {
        DeleteAnimeContributionCommand command = new DeleteAnimeContributionCommand(animeId, personId);
        deleteAnimeContributionUseCase.delete(command);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }
}
