package com.lge.ocpi.emsp.service.version;

import com.lge.ocpi.emsp.mapstruct.CommonMapper;
import com.lge.ocpi.emsp.model.dto.version.VersionDetailsDTO;
import com.lge.ocpi.emsp.model.dto.version.VersionDto;
import com.lge.ocpi.emsp.model.entity.version.VersionDetailsEntity;
import com.lge.ocpi.emsp.model.entity.version.VersionEntity;
import com.lge.ocpi.emsp.model.enums.VersionNumber;
import com.lge.ocpi.emsp.repository.version.VersionDetailsRepository;
import com.lge.ocpi.emsp.repository.version.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    VersionRepository versionRepository;

    @Autowired
    VersionDetailsRepository versionDetailsRepository;


    @Override
    public List<VersionDto> getVersions() {
        List<VersionEntity> versionEntityList = versionRepository.findAll();
        return CommonMapper.INSTANCE.VersionFromEntity(versionEntityList);
    }

    @Override
    public VersionDetailsDTO putVersionDetails(VersionDetailsEntity versionDetailsEntity) {
        VersionDetailsEntity versionEntityList = versionDetailsRepository.save(versionDetailsEntity);
        return CommonMapper.INSTANCE.VersionDetailsFromEntity(versionEntityList);
    }

    @Override
    public VersionDto putVersion(VersionEntity versionEntity) {
        VersionEntity versionentity = versionRepository.save(versionEntity);
        return CommonMapper.INSTANCE.VersionSingleFromEntity(versionentity);
    }

    @Override
    public VersionDetailsDTO getVersionDetails(VersionNumber version) {
        VersionDetailsEntity versionDetailsEntity = versionDetailsRepository.findVersionDetails(version);
        return CommonMapper.INSTANCE.VersionDetailsFromEntity(versionDetailsEntity);
    }
}
