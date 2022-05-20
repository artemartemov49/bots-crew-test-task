package com.artem.university.mapper;

public interface Mapper<F, T> {

    T map(F object);
}
