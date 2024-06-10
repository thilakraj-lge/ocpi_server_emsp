package com.lge.ocpi.emsp.service.location;

import com.lge.ocpi.emsp.model.dto.location.Connector;
import com.lge.ocpi.emsp.model.dto.location.EVSE;
import com.lge.ocpi.emsp.model.dto.location.LocationDto;
import com.lge.ocpi.emsp.model.entity.location.ConnectorEntity;
import com.lge.ocpi.emsp.model.entity.location.EvsesEntity;
import com.lge.ocpi.emsp.model.entity.location.LocationEntity;

import java.util.List;

public interface LocationService {
    List<LocationDto> getAllLocation();

    List<LocationDto> getAllLocationRadius(double lat, double lon, double distance);

    //push location evse and connector
    LocationDto pushLocation(LocationEntity locationEntity);

    EVSE pushEvse(String LocationId, EvsesEntity evse);

    Connector pushConnector(String evseId, ConnectorEntity evse);


    //get location evse and connector
    LocationDto getLocation(String id);

    EVSE getEvses(String evse_id);

    Connector getConnector(String loc_id);

    Connector getConnector(String evse_id, String connector_id);


    LocationDto updateLocation(String locationId, LocationDto locationDTO);

    EVSE updateEvse(String locationId, String evseUid, EVSE evsesEntity);

    Connector updateConnector(String locationId, String evseUid, String connectorId, Connector connectorDto);
}
