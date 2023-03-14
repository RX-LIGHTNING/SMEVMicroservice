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

    public List<FineResponse> getFineResponse(UUID uuid) {
        return fineResponseRepo.findByUuid(uuid);
    }

    @Override
    public HttpStatus deleteResponseFromQueue(UUID uuid) {
        fineResponseRepo.deleteByUUID(uuid);
        log.info("{} Deleted from ResponseQueue", uuid);
        return HttpStatus.OK;
    }
}
