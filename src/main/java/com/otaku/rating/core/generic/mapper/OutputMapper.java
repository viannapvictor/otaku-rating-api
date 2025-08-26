package com.otaku.rating.core.generic.mapper;

public interface OutputMapper<M, E> {
    E toEntity(M model);
}
