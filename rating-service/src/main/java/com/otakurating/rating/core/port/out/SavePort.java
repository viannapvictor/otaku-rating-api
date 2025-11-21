package com.otakurating.rating.core.port.out;

public interface SavePort<E> {
    E save(E item);
}
