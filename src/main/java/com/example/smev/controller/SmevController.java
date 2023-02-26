package com.example.smev.controller;

import com.example.smev.dto.FineRequest;
import com.example.smev.dto.FineResponse;
import com.example.smev.service.RequestQueueService;
import com.example.smev.service.ResponseQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // TODO: 21.02.23 Почему не GET маппинг. Мы тут только получаем ресурс
    @PostMapping("/result")
    public ResponseEntity<List<FineResponse>> getResult(@RequestBody UUID uuid) {
        return fineResponseService.getFineResponse(uuid);
    }

    @PostMapping("/acknowledge")
    public ResponseEntity<HttpStatus> acknowledge(@RequestBody UUID uuid) {
        return fineResponseService.deleteResponseFromQueue(uuid);
    }
}
