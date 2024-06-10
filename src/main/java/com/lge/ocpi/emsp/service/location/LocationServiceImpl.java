package com.lge.ocpi.emsp.service.location;

import com.lge.ocpi.emsp.mapstruct.CommonMapper;
import com.lge.ocpi.emsp.model.dto.location.Connector;
import com.lge.ocpi.emsp.model.dto.location.EVSE;
import com.lge.ocpi.emsp.model.dto.location.LocationDto;
import com.lge.ocpi.emsp.model.entity.location.ConnectorEntity;
import com.lge.ocpi.emsp.model.entity.location.EvsesEntity;
import com.lge.ocpi.emsp.model.entity.location.LocationEntity;
import com.lge.ocpi.emsp.repository.location.ConnectorRepository;
import com.lge.ocpi.emsp.repository.location.EVSERepository;
import com.lge.ocpi.emsp.repository.location.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private EVSERepository evseRepository;
    @Autowired
    private ConnectorRepository connectorRepository;


    @Override
    public List<LocationDto> getAllLocation() {

        List<LocationEntity> locationEntities = locationRepository.findAll();
        return CommonMapper.INSTANCE.fromEntity(locationEntities);

    }

    @Override
    public List<LocationDto> getAllLocationRadius(double lat, double lon, double distance) {
        List<LocationEntity> locationEntities = locationRepository.findByLocationWithinRadius(lat, lon, distance);
        return CommonMapper.INSTANCE.fromEntity(locationEntities);
    }


    @Override
    public LocationDto pushLocation(LocationEntity location) {

        LocationEntity locationEntity = locationRepository.save(location);
        return CommonMapper.INSTANCE.fromEntity(locationEntity);
    }

    @Override
    public EVSE pushEvse(String LocationId, EvsesEntity evse) {
        LocationEntity locationEntity = locationRepository.findLocation(LocationId);
        if (locationEntity != null) {
            locationEntity.getEvses().add(evse);
            locationRepository.save(locationEntity);
        }
        EvsesEntity evsesEntity = evseRepository.findEvses(evse.getUid());
        return CommonMapper.INSTANCE.fromEntity(evsesEntity);
    }

    @Override
    public Connector pushConnector(String evseId, ConnectorEntity connector) {
        EvsesEntity evsesEntity = evseRepository.findEvses(evseId);
        if (evsesEntity != null) {
            evsesEntity.getConnectors().add(connector);
            evseRepository.save(evsesEntity);
        }
        ConnectorEntity connectorEntity = connectorRepository.findConnector(connector.getId());
        return CommonMapper.INSTANCE.fromEntity(connectorEntity);
    }


    @Override
    public LocationDto getLocation(String loc_id) {
        LocationEntity locationEntities = locationRepository.findLocation(loc_id);
        return CommonMapper.INSTANCE.fromEntity(locationEntities);
    }

    @Override
    public EVSE getEvses(String evse_id) {
        EvsesEntity evsesEntity = evseRepository.findEvses(evse_id);
        return CommonMapper.INSTANCE.fromEntity(evsesEntity);
    }

    @Override
    public Connector getConnector(String id) {
        ConnectorEntity connectorEntity = connectorRepository.findConnector(id);
        return CommonMapper.INSTANCE.fromEntity(connectorEntity);
    }

    @Override
    public Connector getConnector(String evse_id, String connector_id) {
        ConnectorEntity connector = connectorRepository.findConnectorByEvse(evse_id, connector_id);
        log.info("connector = " + connector + " evse_id = " + evse_id + " connector_id = " + connector_id);
        return CommonMapper.INSTANCE.fromEntity(connector);
    }

    @Override
    public LocationDto updateLocation(String locationId, LocationDto locationDTO) {
        LocationEntity locationEntity = locationRepository.findLocation(locationId);
        log.info("locationDto = " + locationDTO.toString());
        if (locationEntity != null) {
            locationEntity = CommonMapper.INSTANCE.updateLocation(locationDTO, locationEntity);
            return CommonMapper.INSTANCE.fromEntity(locationRepository.save(locationEntity));
        } else {
            return null;
        }
    }

    @Override
    public EVSE updateEvse(String locationId, String evseUid, EVSE evsesDto) {
        EvsesEntity evsesEntity = evseRepository.findEvses(evseUid);
        if (evsesEntity != null) {
            evsesEntity = CommonMapper.INSTANCE.updateEvse(evsesDto, evsesEntity);
            LocationEntity locationEntity = locationRepository.findLocation(locationId);
            locationEntity.setLast_updated(evsesDto.getLast_updated());
            locationRepository.save(locationEntity);
            return CommonMapper.INSTANCE.fromEntity(evseRepository.save(evsesEntity));
        } else {
            return null;
        }
    }

    @Override
    public Connector updateConnector(String locationId, String evseUid, String connectorId, Connector connectorDto) {
        log.info("evseid = " + evseUid + " connectroId = " + connectorId);
        ConnectorEntity connectorEntity = connectorRepository.findConnectorByEvse(evseUid, connectorId);
        if (connectorEntity != null) {
            connectorEntity = CommonMapper.INSTANCE.updateConnector(connectorDto, connectorEntity);
            LocationEntity locationEntity = locationRepository.findLocation(locationId);
            locationEntity.setLast_updated(connectorDto.getLast_updated());
            locationRepository.save(locationEntity);

            EvsesEntity evsesEntity = evseRepository.findEvses(evseUid);
            evsesEntity.setLast_updated(connectorDto.getLast_updated());
            evseRepository.save(evsesEntity);
            return CommonMapper.INSTANCE.fromEntity(connectorRepository.save(connectorEntity));
        } else {
            return null;
        }
    }


}
