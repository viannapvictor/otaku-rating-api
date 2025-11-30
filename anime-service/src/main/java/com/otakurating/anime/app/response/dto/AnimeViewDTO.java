package com.otakurating.anime.app.response.dto;

import java.time.LocalDate;
import java.util.UUID;

public record AnimeViewDTO(UUID id, String title, String description, LocalDate release) {
}
