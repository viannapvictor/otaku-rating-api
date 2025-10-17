package com.otaku.rating.api.controller;

import com.otaku.rating.api.request.person.dto.PersonCreateDTO;
import com.otaku.rating.api.request.generic.PageRequest;
import com.otaku.rating.api.request.person.dto.PersonUpdateDTO;
import com.otaku.rating.api.response.ApiResponse;
import com.otaku.rating.api.response.person.dto.PersonViewDTO;
import com.otaku.rating.api.response.generic.PageResponse;
import com.otaku.rating.core.person.facade.PersonFacade;
import com.otaku.rating.core.person.model.Person;
import com.otaku.rating.core.generic.mapper.InputMapper;
import com.otaku.rating.core.generic.mapper.OutputMapper;
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
@RequestMapping("/person")
public class PersonController {
    private final PersonFacade personFacade;
    private final InputMapper<Person, PersonCreateDTO> personCreateMapper;
    private final InputMapper<Person, PersonUpdateDTO> personUpdateMapper;
    private final OutputMapper<Person, PersonViewDTO> personViewMapper;

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
