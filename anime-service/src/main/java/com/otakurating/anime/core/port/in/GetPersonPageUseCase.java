package com.otakurating.anime.core.port.in;

import com.otakurating.anime.core.command.GetPersonPageCommand;
import com.otakurating.anime.core.model.Person;
import org.springframework.data.domain.Page;

public interface GetPersonPageUseCase {
    Page<Person> getPage(GetPersonPageCommand command);
}
