package com.example.smev.repository;

import com.example.smev.dto.FineRequest;

import java.util.Optional;
import java.util.UUID;


public interface FineRequestRepo extends Repository<FineRequest> {

    Optional<FineRequest> findByUuid(UUID uuid);
}
