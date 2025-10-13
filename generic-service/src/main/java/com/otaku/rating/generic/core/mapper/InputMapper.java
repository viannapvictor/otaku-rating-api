package com.otaku.rating.generic.core.mapper;

public interface InputMapper<M, E> {
    M toModel(E entity);
}
