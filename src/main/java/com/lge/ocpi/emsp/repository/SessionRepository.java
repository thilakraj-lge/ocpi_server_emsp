package com.lge.ocpi.emsp.repository;

import com.lge.ocpi.emsp.model.entity.session.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
}
