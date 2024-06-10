package com.lge.ocpi.emsp.repository;

import com.lge.ocpi.emsp.model.entity.tariff.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, String> {
}
