package com.otakurating.anime.app.response.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimeViewDTO {
    private String id;
    private String title;
    private String description;
    private LocalDate release;
}
