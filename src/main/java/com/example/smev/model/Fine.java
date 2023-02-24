package com.example.smev.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
public class Fine {

    private Long id;
    private String vehicleCertificate;
    private String taxPayerID;
    private BigDecimal accruedAmount;
    private BigDecimal fineAmount;
    private String resolution;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date resolutionDate;
    private String article;

}
