package com.practice.swd392_demo.repository;

public interface IRepository<T, ID> {
    void add(T object);
}
