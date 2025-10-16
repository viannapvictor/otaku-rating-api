package com.otaku.rating.generic.core.mapper;

public interface OutputMapper<M, E> {
    E toEntity(M model);
}
