package com.otakurating.anime.core.port.out;

import com.otakurating.anime.core.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface FindPersonPort {
    Optional<Person> findById(UUID id);
    Page<Person> findPersonPage(Pageable pageable);
}
