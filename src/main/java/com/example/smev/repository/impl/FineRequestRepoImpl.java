package com.example.smev.repository.impl;

import com.example.smev.dto.FineRequest;
import com.example.smev.repository.FineRequestRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FineRequestRepoImpl implements FineRequestRepo {

    private static final String FINE_SAVE = "INSERT INTO fine_request (uuid, tax_payerid, vehicle_certificate) VALUES (?,?,?)";
    private static final String FINE_DELETE_BY_UUID = "DELETE FROM fine_request WHERE uuid = ?";
    private static final String FINE_FIND_BY_UUID = "SELECT * FROM fine_request WHERE uuid = ?";
    private static final String FINE_FIND_ALL = "SELECT * FROM fine_request";
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<FineRequest> MAPPER = (resultSet, i) -> FineRequest.builder()
            .uuid(UUID.fromString(resultSet.getString("uuid")))
            .taxPayerID(resultSet.getString("tax_payerid"))
            .vehicleCertificate(resultSet.getString("vehicle_certificate"))
            .build();

    @Override
    public Optional<FineRequest> findByUuid(UUID uuid) {
        return Optional.ofNullable(jdbcTemplate.queryForObject(FINE_FIND_BY_UUID, MAPPER, uuid));
    }

    @Override
    public void save(FineRequest request) {
        jdbcTemplate.update(FINE_SAVE,
                request.getUuid(),
                request.getTaxPayerID(),
                request.getVehicleCertificate());
    }

    @Override
    public void deleteByUUID(UUID uuid) {
        jdbcTemplate.update(FINE_DELETE_BY_UUID, uuid);
    }

    @Override
    public void saveAll(List<FineRequest> requests) {
        requests.forEach(this::save);
    }

    @Override
    public List<FineRequest> findAll() {
        return jdbcTemplate.query(FINE_FIND_ALL, MAPPER);
    }

    @Override
    public void delete(FineRequest request) {
        jdbcTemplate.update(FINE_DELETE_BY_UUID, request.getUuid());
    }

}
