package com.example.smev.service;

import com.example.smev.dto.FineRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface RequestQueueService {

    // TODO: 21.02.23 Не возвращаем ResponseEntity в Service
    ResponseEntity<HttpStatus> saveFineRequestToQueue(FineRequest fineRequest);

    FineRequest getFineFromQueue(UUID uuid);

    void deleteRequestFromQueue(FineRequest fineRequest);
}
