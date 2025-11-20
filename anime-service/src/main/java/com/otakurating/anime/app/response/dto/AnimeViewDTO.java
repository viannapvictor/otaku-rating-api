package com.otakurating.anime.app.response.dto;

import java.time.LocalDate;

public record AnimeViewDTO(String id, String title, String description, LocalDate release) {
}
