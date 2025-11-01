package com.otakurating.anime.app.controller;

import com.otakurating.anime.app.request.dto.PersonCreateDTO;
import com.otakurating.anime.app.request.dto.PersonUpdateDTO;
import com.otakurating.anime.app.request.mapper.PersonCreateMapper;
import com.otakurating.anime.app.request.mapper.PersonUpdateMapper;
import com.otakurating.anime.app.response.dto.ApiResponse;
import com.otakurating.anime.app.response.dto.PageResponse;
import com.otakurating.anime.app.response.dto.PersonViewDTO;
import com.otakurating.anime.app.response.mapper.PersonViewMapper;
import com.otakurating.anime.core.facade.PersonFacade;
import com.otakurating.anime.core.model.Person;
import com.otakurating.anime.app.request.dto.PageRequest;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/person")
public class PersonController {
    private final PersonFacade personFacade;
    private final PersonCreateMapper personCreateMapper;
    private final PersonUpdateMapper personUpdateMapper;
    private final PersonViewMapper personViewMapper;

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<PersonViewDTO>>> getPage(@ModelAttribute PageRequest pageRequest) {
        Page<Person> personPage = personFacade.getPage(pageRequest.getPage(), pageRequest.getSize());
        List<PersonViewDTO> personViewDTOs = personPage.getContent()
                .stream()
                .map(personViewMapper::toEntity)
                .toList();
        PageResponse<PersonViewDTO> pageResponse = PageResponse.from(personPage, personViewDTOs);
        return ApiResponse.success(pageResponse).createResponse(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PersonViewDTO>> getById(@PathVariable("id") UUID id) {
        Person person = personFacade.getById(id);
        PersonViewDTO personViewDTO = personViewMapper.toEntity(person);

        return ApiResponse.success(personViewDTO).createResponse(HttpStatus.OK);
    }

    @PostMapping
    @RolesAllowed({"ADMIN", "MODERATOR"})
    public ResponseEntity<ApiResponse<PersonViewDTO>> create(@RequestBody PersonCreateDTO form) {
        Person person = personCreateMapper.toModel(form);
        Person createdPerson = personFacade.add(person);
        PersonViewDTO personViewDTO = personViewMapper.toEntity(createdPerson);

        return ApiResponse.success(personViewDTO).createResponse(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @RolesAllowed({"ADMIN", "MODERATOR"})
    public ResponseEntity<ApiResponse<PersonViewDTO>> update(@PathVariable("id") UUID id, @RequestBody PersonCreateDTO form) {
        PersonUpdateDTO personUpdateDTO = new PersonUpdateDTO(id, form.getName(), form.getDescription());
        Person person = personUpdateMapper.toModel(personUpdateDTO);
        Person updatedPerson = personFacade.update(person);
        PersonViewDTO personViewDTO = personViewMapper.toEntity(updatedPerson);

        return ApiResponse.success(personViewDTO).createResponse(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ADMIN", "MODERATOR"})
    public ResponseEntity<ApiResponse<Object>> delete(@PathVariable("id") UUID id) {
        Person person = personFacade.getById(id);
        personFacade.delete(person);

        return ApiResponse.success(null).createResponse(HttpStatus.OK);
    }
}
