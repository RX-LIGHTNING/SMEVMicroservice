package com.example.smev.util;

import com.example.smev.dto.FineResponse;
import com.example.smev.model.Fine;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FineMapper {
    public static FineResponse fineResponseFromFine(Fine fine, UUID uuid) {
        return FineResponse.builder()
                .uuid(uuid)
                .taxPayerID(fine.getTaxPayerID())
                .vehicleCertificate(fine.getVehicleCertificate())
                .article(fine.getArticle())
                .resolution(fine.getResolution())
                .fineAmount(fine.getFineAmount())
                .accruedAmount(fine.getAccruedAmount())
                .resolutionDate(fine.getResolutionDate()).build();
    }
}
