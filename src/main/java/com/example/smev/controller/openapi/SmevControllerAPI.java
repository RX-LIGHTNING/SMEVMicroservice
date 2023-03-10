package com.example.smev.controller.openapi;

import com.example.smev.dto.FineRequest;
import com.example.smev.dto.FineResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface SmevControllerAPI {

    ResponseEntity<HttpStatus> requestFine(FineRequest fineRequest);

    ResponseEntity<List<FineResponse>> getResult(UUID uuid);

    ResponseEntity<HttpStatus> acknowledge(UUID uuid);

}
