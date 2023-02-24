package com.example.smev.repository;

import java.util.List;
import java.util.UUID;

// TODO: 21.02.23 ДВойная параметризация 
public interface Repository<T> {
    void save(T object);

    void deleteByUUID(UUID uuid);

    void saveAll(List<T> objects);

    List<T> findAll();

    void delete(T t);
}
