package com.example.smev.tests.Service;


import com.example.smev.dto.FineRequest;
import com.example.smev.repository.FineRequestRepo;
import com.example.smev.service.RequestQueueService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestServiceTest {

    @Autowired
    RequestQueueService requestQueueService;

    @Autowired
    FineRequestRepo fineRequestRepo;

    private final UUID uuid = UUID.randomUUID();

    private final FineRequest fineRequest = FineRequest.builder().taxPayerID("1234567890").uuid(uuid).build();

    @Test
    public void RequestSaveTest() {
        requestQueueService.saveFineRequestToQueue(fineRequest);
        assertEquals(fineRequestRepo.findByUuid(uuid).get(), fineRequest);
    }

    @Test
    public void RequestGetTest() {
        requestQueueService.saveFineRequestToQueue(fineRequest);
        FineRequest fineFromDB = requestQueueService.getFineFromQueue(uuid);
        assertEquals(fineRequest,fineFromDB);
    }
}
