package com.example.smev.controller;

import com.example.smev.dto.FineRequest;
import com.example.smev.dto.FineResponse;
import com.example.smev.service.RequestQueueService;
import com.example.smev.service.ResponseQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/fine/")
public class SmevController {

    private final RequestQueueService fineRequireService;
    private final ResponseQueueService fineResponseService;

    @PostMapping("/request")
    public ResponseEntity<HttpStatus> requestFine(@RequestBody FineRequest fineRequest) throws InterruptedException {
        return fineRequireService.saveFineRequestToQueue(fineRequest);
    }
    @GetMapping("/result/{uuid}")
    public ResponseEntity<List<FineResponse>> getResult(@PathVariable UUID uuid) {
        return fineResponseService.getFineResponse(uuid);
    }

    @DeleteMapping("/acknowledge/{uuid}")
    public ResponseEntity<HttpStatus> acknowledge(@PathVariable UUID uuid) {
        return fineResponseService.deleteResponseFromQueue(uuid);
    }
}
