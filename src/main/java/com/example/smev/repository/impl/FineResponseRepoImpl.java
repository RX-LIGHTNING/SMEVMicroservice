package com.example.smev.repository.impl;

import com.example.smev.dto.FineResponse;
import com.example.smev.repository.FineResponseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FineResponseRepoImpl implements FineResponseRepo {

    private static final String RESPONSE_FIND_BY_UUID = "SELECT * FROM fine_response";
    private static final String RESPONSE_SAVE = "INSERT INTO fine_response (uuid,accrued_amount,article,fine_amount,resolution,resolution_date,tax_payerid,vehicle_certificate) VALUES (?,?,?,?,?,?,?,?)";
    private static final String RESPONSE_DELETE_BY_UUID = "DELETE FROM fine_response WHERE uuid = ?";
    private static final String RESPONSE_FIND_ALL = "SELECT * FROM fine_response";
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<FineResponse> MAPPER = (resultSet, i) -> FineResponse.builder()
            .uuid(UUID.fromString(resultSet.getString("uuid")))
            .fineAmount(BigDecimal.valueOf(Double.parseDouble(resultSet.getString("fine_amount"))))
            .accruedAmount(BigDecimal.valueOf(Double.parseDouble(resultSet.getString("accrued_amount"))))
            .article(resultSet.getString("article"))
            .resolution(resultSet.getString("resolution"))
            .resolutionDate(Date.valueOf(resultSet.getString("resolution_date")))
            .taxPayerID(resultSet.getString("tax_payerid"))
            .vehicleCertificate(resultSet.getString("vehicle_certificate"))
            .build();

    @Override
    public List<FineResponse> findByUuid(UUID uuid) {
        return jdbcTemplate.query(RESPONSE_FIND_BY_UUID, MAPPER);
    }

    @Override
    public void save(FineResponse response) {
        jdbcTemplate.update(RESPONSE_SAVE,
                response.getUuid(),
                response.getAccruedAmount(),
                response.getArticle(),
                response.getFineAmount(),
                response.getResolution(),
                response.getResolutionDate(),
                response.getTaxPayerID(),
                response.getVehicleCertificate());
    }

    @Override
    public void deleteByUUID(UUID uuid) {
        jdbcTemplate.update(RESPONSE_DELETE_BY_UUID, uuid);
    }

    @Override
    public void saveAll(List<FineResponse> responses) {
        responses.forEach(this::save);
    }

    @Override
    public List<FineResponse> findAll() {
        return jdbcTemplate.query(RESPONSE_FIND_ALL, MAPPER);
    }

    @Override
    public void delete(FineResponse fineResponse) {
        this.deleteByUUID(fineResponse.getUuid());
    }
}
