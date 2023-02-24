package com.example.smev.repository;

import com.example.smev.dto.FineResponse;

import java.util.List;
import java.util.UUID;


public interface FineResponseRepo extends Repository<FineResponse> {
    List<FineResponse> findByUuid(UUID uuid);

}
