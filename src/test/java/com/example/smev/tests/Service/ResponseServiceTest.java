package com.example.smev.tests.Service;

import com.example.smev.dto.FineResponse;
import com.example.smev.repository.FineResponseRepo;
import com.example.smev.service.ResponseQueueService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ResponseServiceTest {

    @Autowired
    ResponseQueueService responseQueueService;

    @Autowired
    FineResponseRepo fineResponseRepo;

    private final UUID uuid = UUID.randomUUID();

    private final FineResponse fineResponse = FineResponse.builder()
            .uuid(uuid)
            .taxPayerID("1234567890")
            .fineAmount(BigDecimal.valueOf(1.0))
            .vehicleCertificate("ETA123567")
            .article("12345")
            .resolutionDate(new Date(123))
            .accruedAmount(BigDecimal.valueOf(1.0))
            .resolution("12345")
            .build();

    @Test
    public void ResponseGetTest(){
        fineResponseRepo.save(fineResponse);
        List<FineResponse> fineRequestFromQueue = responseQueueService.getFineResponse(uuid);
        List<FineResponse> testFineResponse = new ArrayList<>();
        testFineResponse.add(fineResponse);

        //Даже хамкрест мне не помог в сравнении двух списков ;-;
        assertEquals(Arrays.toString(testFineResponse.toArray()), Arrays.toString(fineRequestFromQueue.toArray()));
    }
}
