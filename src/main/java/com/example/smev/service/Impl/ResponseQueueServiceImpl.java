package com.example.smev.service.Impl;

import com.example.smev.dto.FineResponse;
import com.example.smev.repository.FineResponseRepo;
import com.example.smev.service.ResponseQueueService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class ResponseQueueServiceImpl implements ResponseQueueService {

    private final FineResponseRepo fineResponseRepo;

    public ResponseEntity<List<FineResponse>> getFineResponse(UUID uuid) {
        List<FineResponse> fineResponse = fineResponseRepo.findByUuid(uuid);
        return new ResponseEntity<>(fineResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteResponseFromQueue(List<FineResponse> fineResponse) {
        fineResponse.forEach(i -> fineResponseRepo.deleteByUUID(i.getUuid()));
        log.info("{} Deleted from ResponseQueue", fineResponse);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
