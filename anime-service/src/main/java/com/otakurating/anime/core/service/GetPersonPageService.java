package com.otakurating.anime.core.service;

import com.otakurating.anime.core.command.GetPersonPageCommand;
import com.otakurating.anime.core.model.Person;
import com.otakurating.anime.core.port.in.GetPersonPageUseCase;
import com.otakurating.anime.core.port.out.FindPersonPort;
import com.otakurating.anime.core.utils.PageUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GetPersonPageService implements GetPersonPageUseCase {
    public static final int MAX_PAGE_SIZE = 20;

    private final FindPersonPort findPersonPort;

    public GetPersonPageService(FindPersonPort findPersonPort) {
        this.findPersonPort = findPersonPort;
    }

    @Override
    public Page<Person> getPage(GetPersonPageCommand command) {
        Pageable pageable = PageUtils.createPageable(command.getPage(), command.getSize(), MAX_PAGE_SIZE);
        return findPersonPort.findPersonPage(pageable);
    }
}
