package com.practice.swd392_demo.repository;

public interface IRepository<T, ID> {
    T add(T object);
    boolean remove(ID id);
}
