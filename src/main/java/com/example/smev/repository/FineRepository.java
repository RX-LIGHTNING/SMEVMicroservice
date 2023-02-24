package com.example.smev.repository;

import com.example.smev.model.Fine;

import java.util.List;
import java.util.Optional;


public interface FineRepository extends Repository<Fine> {
    // TODO: 21.02.23 Optional и лист не совместимые вещи, переделать нахуй
    List<Fine> findAllByTaxPayerID(String taxPayerID);

    List<Fine> findAllByVehicleCertificate(String vehicleCertificate);
}
