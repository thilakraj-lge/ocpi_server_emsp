package com.lge.ocpi.emsp.repository.version;

import com.lge.ocpi.emsp.model.entity.cdr.Cdr;
import com.lge.ocpi.emsp.model.entity.version.VersionDetailsEntity;
import com.lge.ocpi.emsp.model.entity.version.VersionEntity;
import com.lge.ocpi.emsp.model.enums.VersionNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface VersionRepository extends JpaRepository<VersionEntity, Long> {

}
