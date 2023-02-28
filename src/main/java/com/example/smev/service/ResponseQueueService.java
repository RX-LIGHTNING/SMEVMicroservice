package com.example.smev.service;

import com.example.smev.dto.FineResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ResponseQueueService {
    List<FineResponse> getFineResponse(UUID uuid);

    HttpStatus deleteResponseFromQueue(UUID uuid);
}
