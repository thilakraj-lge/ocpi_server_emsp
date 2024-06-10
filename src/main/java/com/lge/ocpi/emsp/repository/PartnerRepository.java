package com.lge.ocpi.emsp.repository;

import com.lge.ocpi.emsp.model.entity.credentials.PartnerEntity;
import com.lge.ocpi.emsp.model.entity.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartnerRepository extends JpaRepository<PartnerEntity, Long> {
}
