package com.example.smev.repository.impl;

import com.example.smev.model.Fine;
import com.example.smev.repository.FineRepository;
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
public class FineRepoImpl implements FineRepository {

    private final JdbcTemplate jdbcTemplate;
    private final String FINE_FIND_BY_TAXPAYER = "SELECT * FROM fine WHERE tax_payerid=?";
    private final String FINE_FIND_BY_VEHICLE_CERTIFICATE = "SELECT * FROM fine WHERE vehicle_certificate=?";

    private final RowMapper<Fine> MAPPER = (resultSet, i) -> Fine.builder()
            .fineAmount(BigDecimal.valueOf(Double.parseDouble(resultSet.getString("fine_amount"))))
            .accruedAmount(BigDecimal.valueOf(Double.parseDouble(resultSet.getString("accrued_amount"))))
            .article(resultSet.getString("article"))
            .resolution(resultSet.getString("resolution"))
            .resolutionDate(Date.valueOf(resultSet.getString("resolution_date")))
            .taxPayerID(resultSet.getString("tax_payerid"))
            .vehicleCertificate(resultSet.getString("vehicle_certificate"))
            .build();

    @Override
    public List<Fine> findAllByTaxPayerID(String taxPayerID) {
        return jdbcTemplate.query(FINE_FIND_BY_TAXPAYER, MAPPER, taxPayerID);
    }

    @Override
    public List<Fine> findAllByVehicleCertificate(String vehicleCertificate) {
        return jdbcTemplate.query(FINE_FIND_BY_VEHICLE_CERTIFICATE, MAPPER, vehicleCertificate);
    }

    @Override
    public void save(Fine object) {

    }

    @Override
    public void deleteByUUID(UUID uuid) {

    }

    @Override
    public void saveAll(List<Fine> objects) {

    }

    @Override
    public List<Fine> findAll() {
        return null;
    }

    @Override
    public void delete(Fine fine) {
    }
}
