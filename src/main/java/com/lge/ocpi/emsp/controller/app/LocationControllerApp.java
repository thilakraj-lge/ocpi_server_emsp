package com.lge.ocpi.emsp.controller.app;

import com.lge.ocpi.emsp.helper.OcpiStatusCode;
import com.lge.ocpi.emsp.helper.ResponseFormat;
import com.lge.ocpi.emsp.mapstruct.CommonMapper;
import com.lge.ocpi.emsp.model.dto.location.Connector;
import com.lge.ocpi.emsp.model.dto.location.EVSE;
import com.lge.ocpi.emsp.model.dto.location.LocationDto;
import com.lge.ocpi.emsp.model.entity.location.ConnectorEntity;
import com.lge.ocpi.emsp.model.entity.location.EvsesEntity;
import com.lge.ocpi.emsp.model.entity.location.LocationEntity;
import com.lge.ocpi.emsp.service.location.LocationService;
import com.lge.ocpi.emsp.util.Constants;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(Constants.Endponints.emsp_endpoint_url_app + "locations")
public class LocationControllerApp {
    @Autowired
    private LocationService locationService;
    @Autowired
    CommonMapper mapper;


    @GetMapping("/all-location")
    public ResponseEntity<ResponseFormat<List<LocationDto>>> getAllLocation() {
        List<LocationDto> locationEntitylist = locationService.getAllLocation();

        ResponseFormat<List<LocationDto>> responseFormat;
        if (locationEntitylist.isEmpty()) {
            responseFormat = new ResponseFormat<List<LocationDto>>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            responseFormat = new ResponseFormat<List<LocationDto>>()
                    .build(OcpiStatusCode.SUCCESS, locationEntitylist);
        }
        return ResponseEntity.ok(responseFormat);
    }

    @GetMapping("/location-with-radius/{lat}/{lon}/{distance}")
    public ResponseEntity<ResponseFormat<List<LocationDto>>> getAllLocation(@PathVariable(value = "lat") double lat, @PathVariable(value = "lon") double lon, @PathVariable(value = "distance") double distance) {
        List<LocationDto> locationEntitylist = locationService.getAllLocationRadius(lat, lon, distance);

        ResponseFormat<List<LocationDto>> responseFormat;
        if (locationEntitylist.isEmpty()) {
            responseFormat = new ResponseFormat<List<LocationDto>>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            responseFormat = new ResponseFormat<List<LocationDto>>()
                    .build(OcpiStatusCode.SUCCESS, locationEntitylist);
        }
        return ResponseEntity.ok(responseFormat);
    }

    @GetMapping(value = "/{country_code}/{party_id}/{location_id}", produces = "application/json")
    public ResponseEntity<LocationDto> getLocation(
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId) {
        LocationDto locationEntity = locationService.getLocation(locationId);
        return ResponseEntity.ok(locationEntity);
    }

    @GetMapping(value = "/{country_code}/{party_id}/{location_id}/{evse_uid}", produces = "application/json")
    public ResponseEntity<EVSE> getEvse(
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId,
            @PathVariable(value = "evse_uid") String evseUid) {
        log.info("evse_uid = " + evseUid);
        EVSE evse = locationService.getEvses(evseUid);
        return ResponseEntity.ok(evse);
    }

    @GetMapping(value = "/{country_code}/{party_id}/{location_id}/{evse_uid}/{connector_id}", produces = "application/json")
    public ResponseEntity<Connector> getConnector(
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId,
            @PathVariable(value = "evse_uid") String evseUid,
            @PathVariable(value = "connector_id") String connectorId) {
        Connector evse = locationService.getConnector(evseUid, connectorId);
        return ResponseEntity.ok(evse);
    }


    @PutMapping("/{country_code}/{party_id}/{location_id}")
    public ResponseEntity<ResponseFormat<LocationDto>> pushLocation(@RequestBody @Valid LocationDto location,
                                                                    @PathVariable(value = "country_code") String countryCode,
                                                                    @PathVariable(value = "party_id") String partyId,
                                                                    @PathVariable(value = "location_id") String locationId) {
        ResponseFormat<LocationDto> responseFormat;
        if (countryCode.isEmpty() || partyId.isEmpty()) {
            responseFormat = new ResponseFormat<LocationDto>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            LocationEntity locationEntity = mapper.toEntity(location);
            LocationDto locationDTO = locationService.pushLocation(locationEntity);
            responseFormat = new ResponseFormat<LocationDto>()
                    .build(OcpiStatusCode.SUCCESS, locationDTO);
        }
        return ResponseEntity.ok(responseFormat);

    }

    @PutMapping("/evse/{country_code}/{party_id}/{location_id}/{evse_uid}")
    public ResponseEntity<ResponseFormat<EVSE>> pushEvse(
            @RequestBody @Valid EVSE evse,
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId,
            @PathVariable(value = "evse_uid") String evseUid) {

        ResponseFormat<EVSE> responseFormat;
        if (countryCode.isEmpty() || partyId.isEmpty()) {
            responseFormat = new ResponseFormat<EVSE>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            EvsesEntity evsesEntity = mapper.toEntity(evse);
            EVSE evseDTO = locationService.pushEvse(locationId, evsesEntity);
            responseFormat = new ResponseFormat<EVSE>()
                    .build(OcpiStatusCode.SUCCESS, evseDTO);
        }
        return ResponseEntity.ok(responseFormat);

    }

    @PutMapping("/evse/connector/{country_code}/{party_id}/{location_id}/{evse_uid}/{connector_id}")
    public ResponseEntity<ResponseFormat<Connector>> pushConnector(
            @RequestBody @Valid Connector connector,
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId,
            @PathVariable(value = "evse_uid") String evseUid,
            @PathVariable(value = "connector_id") String connectorId) {
        ResponseFormat<Connector> responseFormat;
        if (countryCode.isEmpty() || partyId.isEmpty()) {
            responseFormat = new ResponseFormat<Connector>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            ConnectorEntity connectorEntity = mapper.toEntity(connector);
            ;
            Connector connectorDTO = locationService.pushConnector(evseUid, connectorEntity);
            responseFormat = new ResponseFormat<Connector>()
                    .build(OcpiStatusCode.SUCCESS, connectorDTO);
        }
        return ResponseEntity.ok(responseFormat);

    }


    @PatchMapping("/{country_code}/{party_id}/{location_id}")
    public ResponseEntity<ResponseFormat<LocationDto>> patchLocation(
            @RequestBody @Valid LocationDto locationDTO,
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId) {
        ResponseFormat<LocationDto> responseFormat;
        if (countryCode.isEmpty() || partyId.isEmpty()) {
            responseFormat = new ResponseFormat<LocationDto>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            LocationDto locationdto = locationService.updateLocation(locationId, locationDTO);
            responseFormat = new ResponseFormat<LocationDto>()
                    .build(OcpiStatusCode.SUCCESS, locationdto);
        }
        return ResponseEntity.ok(responseFormat);
    }

    @PatchMapping("/evse/{country_code}/{party_id}/{location_id}/{evse_uid}")
    public ResponseEntity<ResponseFormat<EVSE>> patchEvse(
            @RequestBody @Valid EVSE evse,
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId,
            @PathVariable(value = "evse_uid") String evseUid) {
        ResponseFormat<EVSE> responseFormat;
        if (countryCode.isEmpty() || partyId.isEmpty()) {
            responseFormat = new ResponseFormat<EVSE>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            EVSE evseDTO = locationService.updateEvse(locationId, evseUid, evse);
            responseFormat = new ResponseFormat<EVSE>()
                    .build(OcpiStatusCode.SUCCESS, evseDTO);
        }
        return ResponseEntity.ok(responseFormat);
    }

    @PatchMapping("/evse/connector/{country_code}/{party_id}/{location_id}/{evse_uid}/{connector_id}")
    public ResponseEntity<ResponseFormat<Connector>> patchConnector(
            @RequestBody @Valid Connector connector,
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "location_id") String locationId,
            @PathVariable(value = "evse_uid") String evseUid,
            @PathVariable(value = "connector_id") String connectorId) {
        ResponseFormat<Connector> responseFormat;
        if (countryCode.isEmpty() || partyId.isEmpty()) {
            responseFormat = new ResponseFormat<Connector>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            Connector connectorDTO = locationService.updateConnector(locationId, evseUid, connectorId, connector);
            responseFormat = new ResponseFormat<Connector>()
                    .build(OcpiStatusCode.SUCCESS, connectorDTO);
        }
        return ResponseEntity.ok(responseFormat);
    }
}
