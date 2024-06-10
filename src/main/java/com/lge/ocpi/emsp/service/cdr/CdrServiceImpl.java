package com.lge.ocpi.emsp.service.cdr;

import com.lge.ocpi.emsp.mapstruct.CommonMapper;
import com.lge.ocpi.emsp.model.dto.cdr.CdrDto;
import com.lge.ocpi.emsp.model.dto.location.Connector;
import com.lge.ocpi.emsp.model.dto.location.LocationDto;
import com.lge.ocpi.emsp.model.entity.cdr.Cdr;
import com.lge.ocpi.emsp.repository.CdrRepository;
import com.lge.ocpi.emsp.service.location.LocationService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class CdrServiceImpl implements CdrService{

    @Autowired
    CdrRepository cdrRepository;

    @Autowired
    LocationService locationService;

    @Override
    public CdrDto postCdr(Cdr cdr) {
        Cdr cdrEntity = cdrRepository.save(cdr);
        return CommonMapper.INSTANCE.cdrFromEntity(cdrEntity);
    }

    @Override
    public CdrDto getCdr(String cdrId) {
        Cdr cdrEntity = cdrRepository.findCdrById(cdrId);

            CdrDto cdrDto =  CommonMapper.INSTANCE.cdrFromEntity(cdrEntity);
            LocationDto locationDto= locationService.getLocation(cdrDto.getCdrLocation().getLocationId());
            Connector connector = locationService.getConnector(cdrDto.getCdrLocation().getEvseId(),cdrDto.getCdrLocation().getConnectorId());
            log.info("locationDto = "+connector+ " connectorI"+cdrDto.getCdrLocation().getConnectorId());
            cdrDto.setCdrLocation(CommonMapper.INSTANCE.cdrLocationFromLocationDto(locationDto,connector));
            return cdrDto;


    }
}
