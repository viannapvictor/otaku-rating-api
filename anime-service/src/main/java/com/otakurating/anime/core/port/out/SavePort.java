package com.otakurating.anime.core.port.out;

public interface SavePort<E> {
    E save(E item);
}
