package com.otaku.rating.core.generic.mapper;

public interface InputMapper<M, E> {
    M toModel(E entity);
}
