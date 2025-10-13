package com.otaku.rating.anime.app.controller;

import com.otaku.rating.anime.app.request.dto.AnimeContributionCreateDTO;
import com.otaku.rating.anime.app.request.dto.AnimeContributionUpdateDTO;
import com.otaku.rating.anime.app.response.dto.AnimeContributionViewDTO;
import com.otaku.rating.anime.core.facade.AnimeContributionFacade;
import com.otaku.rating.anime.core.model.AnimeContribution;
import com.otaku.rating.generic.core.mapper.InputMapper;
import com.otaku.rating.generic.core.mapper.OutputMapper;
import com.otaku.rating.generic.core.model.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/anime-contribution")
public class AnimeContributionController {
    private final AnimeContributionFacade animeContributionFacade;
    private final InputMapper<AnimeContribution, AnimeContributionCreateDTO> animeContributionCreateMapper;
    private final OutputMapper<AnimeContribution, AnimeContributionViewDTO> animeContributionViewMapper;

    @GetMapping("/{animeId}/{personId}")
    public ResponseEntity<ApiResponse<AnimeContributionViewDTO>> getById(
            @PathVariable("animeId") String animeId,
            @PathVariable("personId") UUID personId
    ) {
        AnimeContribution animeContribution = animeContributionFacade.getById(animeId, personId);
        AnimeContributionViewDTO animeContributionViewDTO = animeContributionViewMapper.toEntity(animeContribution);

        return ApiResponse.success(animeContributionViewDTO).createResponse(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<AnimeContributionViewDTO>> create(@RequestBody AnimeContributionCreateDTO form) {
        AnimeContribution animeContribution = animeContributionCreateMapper.toModel(form);
        AnimeContribution createdAnimeContribution = animeContributionFacade.add(animeContribution);
        AnimeContributionViewDTO animeContributionViewDTO = animeContributionViewMapper.toEntity(createdAnimeContribution);

        return ApiResponse.success(animeContributionViewDTO).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{animeId}/{personId}")
    public ResponseEntity<ApiResponse<AnimeContributionViewDTO>> update(
            @PathVariable("animeId") String animeId,
            @PathVariable("personId") UUID personId,
            @RequestBody AnimeContributionUpdateDTO form
    ) {
        AnimeContributionCreateDTO createDTO = new AnimeContributionCreateDTO(personId, animeId, form.getRole());
        AnimeContribution animeContribution = animeContributionCreateMapper.toModel(createDTO);
        AnimeContribution updatedAnimeContribution = animeContributionFacade.update(animeContribution);
        AnimeContributionViewDTO animeContributionViewDTO = animeContributionViewMapper.toEntity(updatedAnimeContribution);

        return ApiResponse.success(animeContributionViewDTO).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{animeId}/{personId}")
    public ResponseEntity<ApiResponse<Object>> delete(
            @PathVariable("animeId") String animeId,
            @PathVariable("personId") UUID personId
    ) {
        AnimeContribution animeContribution = animeContributionFacade.getById(animeId, personId);
        animeContributionFacade.delete(animeContribution);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }
}
