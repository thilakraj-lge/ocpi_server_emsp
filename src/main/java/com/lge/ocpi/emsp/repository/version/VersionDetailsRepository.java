package com.lge.ocpi.emsp.repository.version;

import com.lge.ocpi.emsp.model.entity.version.VersionDetailsEntity;
import com.lge.ocpi.emsp.model.enums.VersionNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VersionDetailsRepository extends JpaRepository<VersionDetailsEntity, Long> {
    @Query(value = "select * FROM version_details WHERE version_number =:version_number", nativeQuery = true)
    public VersionDetailsEntity findVersionDetails(@Param("version_number") VersionNumber versionNumber);
}
