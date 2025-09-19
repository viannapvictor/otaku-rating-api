package com.otaku.rating.api.request.person.dto;

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
