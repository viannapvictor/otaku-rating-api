package com.otaku.rating.core.generic.mapper;

public interface Mapper<M, E> {
    M toModel(E entity);
    E toEntity(M model);
}
