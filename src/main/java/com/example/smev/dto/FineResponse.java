package com.example.smev.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@Builder
public class FineResponse {

    private UUID uuid;
    private String vehicleCertificate;
    private String taxPayerID;
    private BigDecimal accruedAmount;
    private BigDecimal fineAmount;
    private String resolution;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date resolutionDate;
    private String article;

}
