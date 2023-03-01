package com.example.smev.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Data
@AllArgsConstructor
public class FineRequest {

    @NotNull
    private UUID uuid;
    @Pattern(regexp = "^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$",
            message = "Incorrect data")
    private String vehicleCertificate;
    @Pattern(regexp = "^[\\d+]{10}$", message = "Incorrect data")
    private String taxPayerID;

}
