package com.otakurating.anime.app.request.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeCreateDTO {
    private String title;
    private String description;
    private LocalDate release;
}
