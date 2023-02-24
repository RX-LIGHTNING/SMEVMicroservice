package com.example.smev.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@AllArgsConstructor
public class FineRequest {

    private UUID uuid;
    private String vehicleCertificate;
    private String taxPayerID;

}
