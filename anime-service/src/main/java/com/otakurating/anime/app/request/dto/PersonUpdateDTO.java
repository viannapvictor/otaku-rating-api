package com.otakurating.anime.app.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonUpdateDTO {
    private UUID id;
    private String name;
    private String description;
}
