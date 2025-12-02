package com.otakurating.anime.app.request.dto;

import java.time.LocalDate;

public record AnimeCreateDTO(String title, String description, LocalDate release) {
}
