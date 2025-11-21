package com.otakurating.anime.app.request.dto;

import java.time.LocalDate;

public record AnimeUpdateDTO(String title, String description, LocalDate release) {
}
