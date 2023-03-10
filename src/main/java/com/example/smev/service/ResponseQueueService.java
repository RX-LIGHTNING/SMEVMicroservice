package com.example.smev.service;

import com.example.smev.dto.FineResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ResponseQueueService {
    ResponseEntity<List<FineResponse>> getFineResponse(UUID uuid);

    ResponseEntity<HttpStatus> deleteResponseFromQueue(List<FineResponse> fineResponse);
}
