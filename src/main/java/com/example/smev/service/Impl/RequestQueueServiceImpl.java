package com.example.smev.service.Impl;

import com.example.smev.dto.FineRequest;
import com.example.smev.exception.EntityNotFoundException;
import com.example.smev.repository.FineRequestRepo;
import com.example.smev.service.RequestQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RequestQueueServiceImpl implements RequestQueueService {

    private final FineRequestRepo fineRequestRepo;

    @Override
    public ResponseEntity<HttpStatus> saveFineRequestToQueue(FineRequest fineRequest) {
        log.info("Saving request to queue: {}", fineRequest);
        fineRequestRepo.save(fineRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public FineRequest getFineFromQueue(UUID uuid) {
        FineRequest request = fineRequestRepo.findByUuid(uuid).orElseThrow(EntityNotFoundException::new);
        log.info("Got request from queue by uuid: {}", uuid);
        return request;
    }

    @Override
    public void deleteRequestFromQueue(FineRequest fineRequest) {
        log.info("Deleted request from queue: {}", fineRequest);
        fineRequestRepo.delete(fineRequest);
    }

}
