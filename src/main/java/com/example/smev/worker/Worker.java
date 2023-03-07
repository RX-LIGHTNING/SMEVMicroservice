package com.example.smev.worker;

import com.example.smev.dto.FineRequest;
import com.example.smev.dto.FineResponse;
import com.example.smev.model.Fine;
import com.example.smev.repository.FineRepository;
import com.example.smev.repository.FineRequestRepo;
import com.example.smev.repository.FineResponseRepo;
import com.example.smev.util.FineMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@Slf4j
@Transactional(readOnly = true) //?
@RequiredArgsConstructor
public class Worker implements Runnable {

    private final FineRequestRepo fineRequestRepo;
    private final FineRepository fineRepo;
    private final FineResponseRepo fineResponseRepo;

    @Override
    @Transactional
    public void run() {
        fineRequestRepo.findAll().forEach(fineRequest -> {
            log.info("Processing request {}", fineRequest);
            List<FineResponse> fineResponses = getFineFromGISMP(fineRequest)
                    .stream()
                    .map(x -> FineMapper.fineResponseFromFine(x, fineRequest.getUuid()))
                    .collect(toList());
            fineResponseRepo.saveAll(fineResponses);
            fineRequestRepo.delete(fineRequest);
        });
    }

    public List<Fine> getFineFromGISMP(FineRequest fineRequest) {
        List<Fine> fineFromGismp = new ArrayList<>();
        if (fineRequest.getVehicleCertificate() != null) {
            fineFromGismp = fineRepo.findAllByVehicleCertificate(fineRequest.getVehicleCertificate());
        } else if (fineRequest.getTaxPayerID() != null) {
            fineFromGismp = fineRepo.findAllByTaxPayerID(fineRequest.getTaxPayerID());
        }
        return fineFromGismp;
    }
}
