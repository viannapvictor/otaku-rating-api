package com.otakurating.anime.app.controller;

import com.otakurating.anime.app.request.dto.PersonCreateDTO;
import com.otakurating.anime.app.request.dto.PersonUpdateDTO;
import com.otakurating.anime.app.response.dto.ApiResponse;
import com.otakurating.anime.app.response.dto.PageResponse;
import com.otakurating.anime.app.response.dto.PersonViewDTO;
import com.otakurating.anime.app.response.mapper.PersonViewMapper;
import com.otakurating.anime.core.command.*;
import com.otakurating.anime.core.model.Person;
import com.otakurating.anime.app.request.dto.PageRequest;
import com.otakurating.anime.core.port.in.*;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/person")
public class PersonController {
    private final CreatePersonUseCase createPersonUseCase;
    private final UpdatePersonUseCase updatePersonUseCase;
    private final DeletePersonUseCase deletePersonUseCase;
    private final GetPersonPageUseCase getPersonPageUseCase;
    private final GetPersonUseCase getPersonUseCase;
    private final PersonViewMapper personViewMapper;

    public PersonController(
            CreatePersonUseCase createPersonUseCase,
            UpdatePersonUseCase updatePersonUseCase,
            DeletePersonUseCase deletePersonUseCase,
            GetPersonPageUseCase getPersonPageUseCase,
            GetPersonUseCase getPersonUseCase,
            PersonViewMapper personViewMapper
    ) {
        this.createPersonUseCase = createPersonUseCase;
        this.updatePersonUseCase = updatePersonUseCase;
        this.deletePersonUseCase = deletePersonUseCase;
        this.getPersonPageUseCase = getPersonPageUseCase;
        this.getPersonUseCase = getPersonUseCase;
        this.personViewMapper = personViewMapper;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<PersonViewDTO>>> getPage(
            @ModelAttribute PageRequest pageRequest
    ) {
        GetPersonPageCommand command = new GetPersonPageCommand(pageRequest.page(), pageRequest.size());
        Page<Person> personPage = getPersonPageUseCase.getPage(command);
        List<PersonViewDTO> views = personPage.getContent()
                .stream()
                .map(personViewMapper::toEntity)
                .toList();
        PageResponse<PersonViewDTO> pageResponse = PageResponse.from(personPage, views);
        return ApiResponse.success(pageResponse).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonViewDTO>> getById(@PathVariable("id") UUID id) {
        GetPersonCommand command = new GetPersonCommand(id);
        Person person = getPersonUseCase.getById(command);
        PersonViewDTO view = personViewMapper.toEntity(person);

        return ApiResponse.success(view).createResponse(HttpStatus.OK);
    }

    @PostMapping
    @RolesAllowed({"ADMIN", "MODERATOR"})
    public ResponseEntity<ApiResponse<PersonViewDTO>> create(@RequestBody PersonCreateDTO form) {
        CreatePersonCommand command = new CreatePersonCommand(form.name(), form.description());
        Person person = createPersonUseCase.create(command);
        PersonViewDTO view = personViewMapper.toEntity(person);

        return ApiResponse.success(view).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ADMIN", "MODERATOR"})
    public ResponseEntity<ApiResponse<PersonViewDTO>> update(
            @PathVariable("id") UUID id,
            @RequestBody PersonUpdateDTO form
    ) {
        UpdatePersonCommand command = new UpdatePersonCommand(id, form.name(), form.description());
        Person person = updatePersonUseCase.update(command);
        PersonViewDTO view = personViewMapper.toEntity(person);

        return ApiResponse.success(view).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN", "MODERATOR"})
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable("id") UUID id) {
        DeletePersonCommand command = new DeletePersonCommand(id);
        deletePersonUseCase.delete(command);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }
}
