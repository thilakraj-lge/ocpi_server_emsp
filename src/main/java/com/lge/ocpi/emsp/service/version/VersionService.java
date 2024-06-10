package com.lge.ocpi.emsp.service.version;

import com.lge.ocpi.emsp.model.dto.version.VersionDetailsDTO;
import com.lge.ocpi.emsp.model.dto.version.VersionDto;
import com.lge.ocpi.emsp.model.entity.version.VersionDetailsEntity;
import com.lge.ocpi.emsp.model.entity.version.VersionEntity;
import com.lge.ocpi.emsp.model.enums.VersionNumber;

import java.util.List;

public interface VersionService {
    List<VersionDto> getVersions();
   VersionDetailsDTO putVersionDetails(VersionDetailsEntity versionDetailsEntity);

    VersionDto putVersion(VersionEntity versionEntity);

    VersionDetailsDTO getVersionDetails(VersionNumber version);
}
