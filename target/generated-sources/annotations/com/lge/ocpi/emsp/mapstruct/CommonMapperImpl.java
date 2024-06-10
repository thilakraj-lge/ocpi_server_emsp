package com.lge.ocpi.emsp.mapstruct;

import com.lge.ocpi.emsp.model.dto.Token.TokenDto;
import com.lge.ocpi.emsp.model.dto.cdr.CdrDto;
import com.lge.ocpi.emsp.model.dto.cdr.CdrLocation;
import com.lge.ocpi.emsp.model.dto.cdr.CdrToken;
import com.lge.ocpi.emsp.model.dto.credentials.partner.PartnerDTO;
import com.lge.ocpi.emsp.model.dto.location.Connector;
import com.lge.ocpi.emsp.model.dto.location.EVSE;
import com.lge.ocpi.emsp.model.dto.location.LocationDto;
import com.lge.ocpi.emsp.model.dto.session.ChargingPeriod;
import com.lge.ocpi.emsp.model.dto.session.SessionDto;
import com.lge.ocpi.emsp.model.dto.tariff.TariffDto;
import com.lge.ocpi.emsp.model.dto.tariff.TariffElementDto;
import com.lge.ocpi.emsp.model.dto.userDetails.UserResponse;
import com.lge.ocpi.emsp.model.dto.version.VersionDetailsDTO;
import com.lge.ocpi.emsp.model.dto.version.VersionDto;
import com.lge.ocpi.emsp.model.entity.autentication.UserInfo;
import com.lge.ocpi.emsp.model.entity.autentication.UserRole;
import com.lge.ocpi.emsp.model.entity.cdr.Cdr;
import com.lge.ocpi.emsp.model.entity.credentials.PartnerEntity;
import com.lge.ocpi.emsp.model.entity.location.ConnectorEntity;
import com.lge.ocpi.emsp.model.entity.location.EvsesEntity;
import com.lge.ocpi.emsp.model.entity.location.LocationEntity;
import com.lge.ocpi.emsp.model.entity.session.Session;
import com.lge.ocpi.emsp.model.entity.tariff.Tariff;
import com.lge.ocpi.emsp.model.entity.tariff.TariffElement;
import com.lge.ocpi.emsp.model.entity.token.Token;
import com.lge.ocpi.emsp.model.entity.version.VersionDetailsEntity;
import com.lge.ocpi.emsp.model.entity.version.VersionEntity;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-05T15:53:14+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class CommonMapperImpl implements CommonMapper {

    @Override
    public LocationEntity toEntity(LocationDto locationDto) {
        if ( locationDto == null ) {
            return null;
        }

        LocationEntity locationEntity = new LocationEntity();

        locationEntity.setId( locationDto.getId() );
        locationEntity.setCountry_code( locationDto.getCountry_code() );
        locationEntity.setParty_id( locationDto.getParty_id() );
        locationEntity.setPublish( locationDto.isPublish() );
        locationEntity.setName( locationDto.getName() );
        locationEntity.setAddress( locationDto.getAddress() );
        locationEntity.setCity( locationDto.getCity() );
        locationEntity.setPostal_code( locationDto.getPostal_code() );
        locationEntity.setCountry( locationDto.getCountry() );
        locationEntity.setState( locationDto.getState() );
        Map<String, String> map = locationDto.getCoordinates();
        if ( map != null ) {
            locationEntity.setCoordinates( new LinkedHashMap<String, String>( map ) );
        }
        List<Map<String, Object>> list = locationDto.getRelated_locations();
        if ( list != null ) {
            locationEntity.setRelated_locations( new ArrayList<Map<String, Object>>( list ) );
        }
        locationEntity.setParking_type( locationDto.getParking_type() );
        Map<String, String> map1 = locationDto.getOpening_times();
        if ( map1 != null ) {
            locationEntity.setOpening_times( new LinkedHashMap<String, String>( map1 ) );
        }
        List<Map<String, Object>> list1 = locationDto.getDirections();
        if ( list1 != null ) {
            locationEntity.setDirections( new ArrayList<Map<String, Object>>( list1 ) );
        }
        locationEntity.setEvses( eVSEListToEvsesEntityList( locationDto.getEvses() ) );
        Map<String, String> map2 = locationDto.getOperator();
        if ( map2 != null ) {
            locationEntity.setOperator( new LinkedHashMap<String, String>( map2 ) );
        }
        Map<String, String> map3 = locationDto.getSuboperator();
        if ( map3 != null ) {
            locationEntity.setSuboperator( new LinkedHashMap<String, String>( map3 ) );
        }
        Map<String, String> map4 = locationDto.getOwner();
        if ( map4 != null ) {
            locationEntity.setOwner( new LinkedHashMap<String, String>( map4 ) );
        }
        List<String> list3 = locationDto.getFacilities();
        if ( list3 != null ) {
            locationEntity.setFacilities( new ArrayList<String>( list3 ) );
        }
        locationEntity.setTime_zone( locationDto.getTime_zone() );
        locationEntity.setLast_updated( locationDto.getLast_updated() );
        Map<String, String> map5 = locationDto.getEnergy_mix();
        if ( map5 != null ) {
            locationEntity.setEnergy_mix( new LinkedHashMap<String, String>( map5 ) );
        }
        List<Map<String, Object>> list4 = locationDto.getPublish_allowed_to();
        if ( list4 != null ) {
            locationEntity.setPublish_allowed_to( new ArrayList<Map<String, Object>>( list4 ) );
        }
        locationEntity.setCharging_when_closed( locationDto.isCharging_when_closed() );

        return locationEntity;
    }

    @Override
    public LocationDto fromEntity(LocationEntity entity) {
        if ( entity == null ) {
            return null;
        }

        LocationDto locationDto = new LocationDto();

        locationDto.setId( entity.getId() );
        locationDto.setCountry_code( entity.getCountry_code() );
        locationDto.setParty_id( entity.getParty_id() );
        locationDto.setPublish( entity.isPublish() );
        List<Map<String, Object>> list = entity.getPublish_allowed_to();
        if ( list != null ) {
            locationDto.setPublish_allowed_to( new ArrayList<Map<String, Object>>( list ) );
        }
        locationDto.setName( entity.getName() );
        locationDto.setAddress( entity.getAddress() );
        locationDto.setCity( entity.getCity() );
        locationDto.setPostal_code( entity.getPostal_code() );
        locationDto.setState( entity.getState() );
        locationDto.setCountry( entity.getCountry() );
        Map<String, String> map = entity.getCoordinates();
        if ( map != null ) {
            locationDto.setCoordinates( new LinkedHashMap<String, String>( map ) );
        }
        List<Map<String, Object>> list1 = entity.getRelated_locations();
        if ( list1 != null ) {
            locationDto.setRelated_locations( new ArrayList<Map<String, Object>>( list1 ) );
        }
        locationDto.setParking_type( entity.getParking_type() );
        locationDto.setEvses( evsesEntityListToEVSEList( entity.getEvses() ) );
        List<Map<String, Object>> list3 = entity.getDirections();
        if ( list3 != null ) {
            locationDto.setDirections( new ArrayList<Map<String, Object>>( list3 ) );
        }
        Map<String, String> map1 = entity.getOperator();
        if ( map1 != null ) {
            locationDto.setOperator( new LinkedHashMap<String, String>( map1 ) );
        }
        Map<String, String> map2 = entity.getSuboperator();
        if ( map2 != null ) {
            locationDto.setSuboperator( new LinkedHashMap<String, String>( map2 ) );
        }
        Map<String, String> map3 = entity.getOwner();
        if ( map3 != null ) {
            locationDto.setOwner( new LinkedHashMap<String, String>( map3 ) );
        }
        List<String> list4 = entity.getFacilities();
        if ( list4 != null ) {
            locationDto.setFacilities( new ArrayList<String>( list4 ) );
        }
        locationDto.setTime_zone( entity.getTime_zone() );
        Map<String, String> map4 = entity.getOpening_times();
        if ( map4 != null ) {
            locationDto.setOpening_times( new LinkedHashMap<String, String>( map4 ) );
        }
        locationDto.setCharging_when_closed( entity.isCharging_when_closed() );
        Map<String, String> map5 = entity.getEnergy_mix();
        if ( map5 != null ) {
            locationDto.setEnergy_mix( new LinkedHashMap<String, String>( map5 ) );
        }
        locationDto.setLast_updated( entity.getLast_updated() );

        return locationDto;
    }

    @Override
    public List<LocationDto> fromEntity(List<LocationEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<LocationDto> list = new ArrayList<LocationDto>( entity.size() );
        for ( LocationEntity locationEntity : entity ) {
            list.add( fromEntity( locationEntity ) );
        }

        return list;
    }

    @Override
    public LocationEntity updateLocation(LocationDto locationDTO, LocationEntity locationEntity) {
        if ( locationDTO == null ) {
            return locationEntity;
        }

        if ( locationDTO.getCountry_code() != null ) {
            locationEntity.setCountry_code( locationDTO.getCountry_code() );
        }
        if ( locationDTO.getParty_id() != null ) {
            locationEntity.setParty_id( locationDTO.getParty_id() );
        }
        if ( locationDTO.getId() != null ) {
            locationEntity.setId( locationDTO.getId() );
        }
        locationEntity.setPublish( locationDTO.isPublish() );
        if ( locationDTO.getName() != null ) {
            locationEntity.setName( locationDTO.getName() );
        }
        if ( locationDTO.getAddress() != null ) {
            locationEntity.setAddress( locationDTO.getAddress() );
        }
        if ( locationDTO.getCity() != null ) {
            locationEntity.setCity( locationDTO.getCity() );
        }
        if ( locationDTO.getPostal_code() != null ) {
            locationEntity.setPostal_code( locationDTO.getPostal_code() );
        }
        if ( locationDTO.getCountry() != null ) {
            locationEntity.setCountry( locationDTO.getCountry() );
        }
        if ( locationDTO.getState() != null ) {
            locationEntity.setState( locationDTO.getState() );
        }
        if ( locationEntity.getCoordinates() != null ) {
            Map<String, String> map = locationDTO.getCoordinates();
            if ( map != null ) {
                locationEntity.getCoordinates().clear();
                locationEntity.getCoordinates().putAll( map );
            }
        }
        else {
            Map<String, String> map = locationDTO.getCoordinates();
            if ( map != null ) {
                locationEntity.setCoordinates( new LinkedHashMap<String, String>( map ) );
            }
        }
        if ( locationEntity.getRelated_locations() != null ) {
            List<Map<String, Object>> list = locationDTO.getRelated_locations();
            if ( list != null ) {
                locationEntity.getRelated_locations().clear();
                locationEntity.getRelated_locations().addAll( list );
            }
        }
        else {
            List<Map<String, Object>> list = locationDTO.getRelated_locations();
            if ( list != null ) {
                locationEntity.setRelated_locations( new ArrayList<Map<String, Object>>( list ) );
            }
        }
        if ( locationDTO.getParking_type() != null ) {
            locationEntity.setParking_type( locationDTO.getParking_type() );
        }
        if ( locationEntity.getOpening_times() != null ) {
            Map<String, String> map1 = locationDTO.getOpening_times();
            if ( map1 != null ) {
                locationEntity.getOpening_times().clear();
                locationEntity.getOpening_times().putAll( map1 );
            }
        }
        else {
            Map<String, String> map1 = locationDTO.getOpening_times();
            if ( map1 != null ) {
                locationEntity.setOpening_times( new LinkedHashMap<String, String>( map1 ) );
            }
        }
        if ( locationEntity.getDirections() != null ) {
            List<Map<String, Object>> list1 = locationDTO.getDirections();
            if ( list1 != null ) {
                locationEntity.getDirections().clear();
                locationEntity.getDirections().addAll( list1 );
            }
        }
        else {
            List<Map<String, Object>> list1 = locationDTO.getDirections();
            if ( list1 != null ) {
                locationEntity.setDirections( new ArrayList<Map<String, Object>>( list1 ) );
            }
        }
        if ( locationEntity.getEvses() != null ) {
            List<EvsesEntity> list2 = eVSEListToEvsesEntityList( locationDTO.getEvses() );
            if ( list2 != null ) {
                locationEntity.getEvses().clear();
                locationEntity.getEvses().addAll( list2 );
            }
        }
        else {
            List<EvsesEntity> list2 = eVSEListToEvsesEntityList( locationDTO.getEvses() );
            if ( list2 != null ) {
                locationEntity.setEvses( list2 );
            }
        }
        if ( locationEntity.getOperator() != null ) {
            Map<String, String> map2 = locationDTO.getOperator();
            if ( map2 != null ) {
                locationEntity.getOperator().clear();
                locationEntity.getOperator().putAll( map2 );
            }
        }
        else {
            Map<String, String> map2 = locationDTO.getOperator();
            if ( map2 != null ) {
                locationEntity.setOperator( new LinkedHashMap<String, String>( map2 ) );
            }
        }
        if ( locationEntity.getSuboperator() != null ) {
            Map<String, String> map3 = locationDTO.getSuboperator();
            if ( map3 != null ) {
                locationEntity.getSuboperator().clear();
                locationEntity.getSuboperator().putAll( map3 );
            }
        }
        else {
            Map<String, String> map3 = locationDTO.getSuboperator();
            if ( map3 != null ) {
                locationEntity.setSuboperator( new LinkedHashMap<String, String>( map3 ) );
            }
        }
        if ( locationEntity.getOwner() != null ) {
            Map<String, String> map4 = locationDTO.getOwner();
            if ( map4 != null ) {
                locationEntity.getOwner().clear();
                locationEntity.getOwner().putAll( map4 );
            }
        }
        else {
            Map<String, String> map4 = locationDTO.getOwner();
            if ( map4 != null ) {
                locationEntity.setOwner( new LinkedHashMap<String, String>( map4 ) );
            }
        }
        if ( locationEntity.getFacilities() != null ) {
            List<String> list3 = locationDTO.getFacilities();
            if ( list3 != null ) {
                locationEntity.getFacilities().clear();
                locationEntity.getFacilities().addAll( list3 );
            }
        }
        else {
            List<String> list3 = locationDTO.getFacilities();
            if ( list3 != null ) {
                locationEntity.setFacilities( new ArrayList<String>( list3 ) );
            }
        }
        if ( locationDTO.getTime_zone() != null ) {
            locationEntity.setTime_zone( locationDTO.getTime_zone() );
        }
        if ( locationDTO.getLast_updated() != null ) {
            locationEntity.setLast_updated( locationDTO.getLast_updated() );
        }
        if ( locationEntity.getEnergy_mix() != null ) {
            Map<String, String> map5 = locationDTO.getEnergy_mix();
            if ( map5 != null ) {
                locationEntity.getEnergy_mix().clear();
                locationEntity.getEnergy_mix().putAll( map5 );
            }
        }
        else {
            Map<String, String> map5 = locationDTO.getEnergy_mix();
            if ( map5 != null ) {
                locationEntity.setEnergy_mix( new LinkedHashMap<String, String>( map5 ) );
            }
        }
        if ( locationEntity.getPublish_allowed_to() != null ) {
            List<Map<String, Object>> list4 = locationDTO.getPublish_allowed_to();
            if ( list4 != null ) {
                locationEntity.getPublish_allowed_to().clear();
                locationEntity.getPublish_allowed_to().addAll( list4 );
            }
        }
        else {
            List<Map<String, Object>> list4 = locationDTO.getPublish_allowed_to();
            if ( list4 != null ) {
                locationEntity.setPublish_allowed_to( new ArrayList<Map<String, Object>>( list4 ) );
            }
        }
        locationEntity.setCharging_when_closed( locationDTO.isCharging_when_closed() );

        return locationEntity;
    }

    @Override
    public EvsesEntity updateEvse(EVSE evse, EvsesEntity evsesEntity) {
        if ( evse == null ) {
            return evsesEntity;
        }

        if ( evse.getUid() != null ) {
            evsesEntity.setUid( evse.getUid() );
        }
        if ( evsesEntity.getStatus_schedule() != null ) {
            List<Map<String, Object>> list = evse.getStatus_schedule();
            if ( list != null ) {
                evsesEntity.getStatus_schedule().clear();
                evsesEntity.getStatus_schedule().addAll( list );
            }
        }
        else {
            List<Map<String, Object>> list = evse.getStatus_schedule();
            if ( list != null ) {
                evsesEntity.setStatus_schedule( new ArrayList<Map<String, Object>>( list ) );
            }
        }
        if ( evse.getEvse_id() != null ) {
            evsesEntity.setEvse_id( evse.getEvse_id() );
        }
        if ( evse.getStatus() != null ) {
            evsesEntity.setStatus( evse.getStatus() );
        }
        if ( evsesEntity.getCapabilities() != null ) {
            List<String> list1 = evse.getCapabilities();
            if ( list1 != null ) {
                evsesEntity.getCapabilities().clear();
                evsesEntity.getCapabilities().addAll( list1 );
            }
        }
        else {
            List<String> list1 = evse.getCapabilities();
            if ( list1 != null ) {
                evsesEntity.setCapabilities( new ArrayList<String>( list1 ) );
            }
        }
        if ( evsesEntity.getConnectors() != null ) {
            List<ConnectorEntity> list2 = connectorListToConnectorEntityList( evse.getConnectors() );
            if ( list2 != null ) {
                evsesEntity.getConnectors().clear();
                evsesEntity.getConnectors().addAll( list2 );
            }
        }
        else {
            List<ConnectorEntity> list2 = connectorListToConnectorEntityList( evse.getConnectors() );
            if ( list2 != null ) {
                evsesEntity.setConnectors( list2 );
            }
        }
        if ( evse.getPhysical_reference() != null ) {
            evsesEntity.setPhysical_reference( evse.getPhysical_reference() );
        }
        if ( evse.getFloor_level() != null ) {
            evsesEntity.setFloor_level( evse.getFloor_level() );
        }
        if ( evse.getLast_updated() != null ) {
            evsesEntity.setLast_updated( evse.getLast_updated() );
        }
        if ( evsesEntity.getCoordinates() != null ) {
            Map<String, String> map = evse.getCoordinates();
            if ( map != null ) {
                evsesEntity.getCoordinates().clear();
                evsesEntity.getCoordinates().putAll( map );
            }
        }
        else {
            Map<String, String> map = evse.getCoordinates();
            if ( map != null ) {
                evsesEntity.setCoordinates( new LinkedHashMap<String, String>( map ) );
            }
        }
        if ( evsesEntity.getDirections() != null ) {
            List<Map<String, Object>> list3 = evse.getDirections();
            if ( list3 != null ) {
                evsesEntity.getDirections().clear();
                evsesEntity.getDirections().addAll( list3 );
            }
        }
        else {
            List<Map<String, Object>> list3 = evse.getDirections();
            if ( list3 != null ) {
                evsesEntity.setDirections( new ArrayList<Map<String, Object>>( list3 ) );
            }
        }
        if ( evsesEntity.getImages() != null ) {
            List<Map<String, Object>> list4 = evse.getImages();
            if ( list4 != null ) {
                evsesEntity.getImages().clear();
                evsesEntity.getImages().addAll( list4 );
            }
        }
        else {
            List<Map<String, Object>> list4 = evse.getImages();
            if ( list4 != null ) {
                evsesEntity.setImages( new ArrayList<Map<String, Object>>( list4 ) );
            }
        }
        if ( evsesEntity.getParking_restrictions() != null ) {
            List<Map<String, Object>> list5 = evse.getParking_restrictions();
            if ( list5 != null ) {
                evsesEntity.getParking_restrictions().clear();
                evsesEntity.getParking_restrictions().addAll( list5 );
            }
        }
        else {
            List<Map<String, Object>> list5 = evse.getParking_restrictions();
            if ( list5 != null ) {
                evsesEntity.setParking_restrictions( new ArrayList<Map<String, Object>>( list5 ) );
            }
        }

        return evsesEntity;
    }

    @Override
    public ConnectorEntity updateConnector(Connector connector, ConnectorEntity connectorEntity) {
        if ( connector == null ) {
            return connectorEntity;
        }

        if ( connector.getId() != null ) {
            connectorEntity.setId( connector.getId() );
        }
        if ( connector.getStandard() != null ) {
            connectorEntity.setStandard( connector.getStandard() );
        }
        if ( connector.getFormat() != null ) {
            connectorEntity.setFormat( connector.getFormat() );
        }
        if ( connector.getPower_type() != null ) {
            connectorEntity.setPower_type( connector.getPower_type() );
        }
        if ( connector.getMax_voltage() != null ) {
            connectorEntity.setMax_voltage( connector.getMax_voltage() );
        }
        if ( connector.getMax_amperage() != null ) {
            connectorEntity.setMax_amperage( connector.getMax_amperage() );
        }
        if ( connector.getMax_electric_power() != null ) {
            connectorEntity.setMax_electric_power( connector.getMax_electric_power() );
        }
        if ( connectorEntity.getTariff_ids() != null ) {
            List<String> list = connector.getTariff_ids();
            if ( list != null ) {
                connectorEntity.getTariff_ids().clear();
                connectorEntity.getTariff_ids().addAll( list );
            }
        }
        else {
            List<String> list = connector.getTariff_ids();
            if ( list != null ) {
                connectorEntity.setTariff_ids( new ArrayList<String>( list ) );
            }
        }
        if ( connector.getLast_updated() != null ) {
            connectorEntity.setLast_updated( connector.getLast_updated() );
        }
        if ( connector.getTerms_and_conditions() != null ) {
            connectorEntity.setTerms_and_conditions( connector.getTerms_and_conditions() );
        }

        return connectorEntity;
    }

    @Override
    public EvsesEntity toEntity(EVSE evse) {
        if ( evse == null ) {
            return null;
        }

        EvsesEntity evsesEntity = new EvsesEntity();

        evsesEntity.setUid( evse.getUid() );
        List<Map<String, Object>> list = evse.getStatus_schedule();
        if ( list != null ) {
            evsesEntity.setStatus_schedule( new ArrayList<Map<String, Object>>( list ) );
        }
        evsesEntity.setEvse_id( evse.getEvse_id() );
        evsesEntity.setStatus( evse.getStatus() );
        List<String> list1 = evse.getCapabilities();
        if ( list1 != null ) {
            evsesEntity.setCapabilities( new ArrayList<String>( list1 ) );
        }
        evsesEntity.setConnectors( connectorListToConnectorEntityList( evse.getConnectors() ) );
        evsesEntity.setPhysical_reference( evse.getPhysical_reference() );
        evsesEntity.setFloor_level( evse.getFloor_level() );
        evsesEntity.setLast_updated( evse.getLast_updated() );
        Map<String, String> map = evse.getCoordinates();
        if ( map != null ) {
            evsesEntity.setCoordinates( new LinkedHashMap<String, String>( map ) );
        }
        List<Map<String, Object>> list3 = evse.getDirections();
        if ( list3 != null ) {
            evsesEntity.setDirections( new ArrayList<Map<String, Object>>( list3 ) );
        }
        List<Map<String, Object>> list4 = evse.getImages();
        if ( list4 != null ) {
            evsesEntity.setImages( new ArrayList<Map<String, Object>>( list4 ) );
        }
        List<Map<String, Object>> list5 = evse.getParking_restrictions();
        if ( list5 != null ) {
            evsesEntity.setParking_restrictions( new ArrayList<Map<String, Object>>( list5 ) );
        }

        return evsesEntity;
    }

    @Override
    public EVSE fromEntity(EvsesEntity entity) {
        if ( entity == null ) {
            return null;
        }

        EVSE eVSE = new EVSE();

        eVSE.setUid( entity.getUid() );
        eVSE.setEvse_id( entity.getEvse_id() );
        eVSE.setStatus( entity.getStatus() );
        List<Map<String, Object>> list = entity.getStatus_schedule();
        if ( list != null ) {
            eVSE.setStatus_schedule( new ArrayList<Map<String, Object>>( list ) );
        }
        List<String> list1 = entity.getCapabilities();
        if ( list1 != null ) {
            eVSE.setCapabilities( new ArrayList<String>( list1 ) );
        }
        eVSE.setConnectors( connectorEntityListToConnectorList( entity.getConnectors() ) );
        eVSE.setFloor_level( entity.getFloor_level() );
        Map<String, String> map = entity.getCoordinates();
        if ( map != null ) {
            eVSE.setCoordinates( new LinkedHashMap<String, String>( map ) );
        }
        eVSE.setPhysical_reference( entity.getPhysical_reference() );
        List<Map<String, Object>> list3 = entity.getDirections();
        if ( list3 != null ) {
            eVSE.setDirections( new ArrayList<Map<String, Object>>( list3 ) );
        }
        List<Map<String, Object>> list4 = entity.getParking_restrictions();
        if ( list4 != null ) {
            eVSE.setParking_restrictions( new ArrayList<Map<String, Object>>( list4 ) );
        }
        List<Map<String, Object>> list5 = entity.getImages();
        if ( list5 != null ) {
            eVSE.setImages( new ArrayList<Map<String, Object>>( list5 ) );
        }
        eVSE.setLast_updated( entity.getLast_updated() );

        return eVSE;
    }

    @Override
    public ConnectorEntity toEntity(Connector car) {
        if ( car == null ) {
            return null;
        }

        ConnectorEntity connectorEntity = new ConnectorEntity();

        connectorEntity.setId( car.getId() );
        connectorEntity.setStandard( car.getStandard() );
        connectorEntity.setFormat( car.getFormat() );
        connectorEntity.setPower_type( car.getPower_type() );
        if ( car.getMax_voltage() != null ) {
            connectorEntity.setMax_voltage( car.getMax_voltage() );
        }
        if ( car.getMax_amperage() != null ) {
            connectorEntity.setMax_amperage( car.getMax_amperage() );
        }
        connectorEntity.setMax_electric_power( car.getMax_electric_power() );
        List<String> list = car.getTariff_ids();
        if ( list != null ) {
            connectorEntity.setTariff_ids( new ArrayList<String>( list ) );
        }
        connectorEntity.setLast_updated( car.getLast_updated() );
        connectorEntity.setTerms_and_conditions( car.getTerms_and_conditions() );

        return connectorEntity;
    }

    @Override
    public Connector fromEntity(ConnectorEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Connector connector = new Connector();

        connector.setId( entity.getId() );
        connector.setStandard( entity.getStandard() );
        connector.setFormat( entity.getFormat() );
        connector.setPower_type( entity.getPower_type() );
        connector.setMax_voltage( entity.getMax_voltage() );
        connector.setMax_amperage( entity.getMax_amperage() );
        connector.setMax_electric_power( entity.getMax_electric_power() );
        List<String> list = entity.getTariff_ids();
        if ( list != null ) {
            connector.setTariff_ids( new ArrayList<String>( list ) );
        }
        connector.setTerms_and_conditions( entity.getTerms_and_conditions() );
        connector.setLast_updated( entity.getLast_updated() );

        return connector;
    }

    @Override
    public List<SessionDto> sessionListFromEntity(List<Session> entity) {
        if ( entity == null ) {
            return null;
        }

        List<SessionDto> list = new ArrayList<SessionDto>( entity.size() );
        for ( Session session : entity ) {
            list.add( sessionFromEntity( session ) );
        }

        return list;
    }

    @Override
    public Session sessionToEntity(SessionDto session) {
        if ( session == null ) {
            return null;
        }

        Session session1 = new Session();

        session1.setSessionId( session.getSessionId() );
        session1.setCdrTokenId( sessionCdrTokenUid( session ) );
        session1.setCountryCode( session.getCountryCode() );
        session1.setPartyId( session.getPartyId() );
        session1.setStartDateTime( session.getStartDateTime() );
        session1.setEndDateTime( session.getEndDateTime() );
        session1.setKwh( session.getKwh() );
        session1.setAuthMethod( session.getAuthMethod() );
        session1.setAuthorizationReference( session.getAuthorizationReference() );
        session1.setLocationId( session.getLocationId() );
        session1.setEvseUid( session.getEvseUid() );
        session1.setConnectorId( session.getConnectorId() );
        session1.setMeterId( session.getMeterId() );
        session1.setCurrency( session.getCurrency() );
        session1.setChargingPeriods( chargingPeriodListToChargingPeriodList( session.getChargingPeriods() ) );
        Map<String, Object> map = session.getTotalCost();
        if ( map != null ) {
            session1.setTotalCost( new LinkedHashMap<String, Object>( map ) );
        }
        session1.setStatus( session.getStatus() );
        session1.setLastUpdated( session.getLastUpdated() );

        return session1;
    }

    @Override
    public SessionDto sessionFromEntity(Session sessionEntity) {
        if ( sessionEntity == null ) {
            return null;
        }

        SessionDto sessionDto = new SessionDto();

        sessionDto.setCdrToken( sessionToCdrToken( sessionEntity ) );
        sessionDto.setSessionId( sessionEntity.getSessionId() );
        sessionDto.setCountryCode( sessionEntity.getCountryCode() );
        sessionDto.setPartyId( sessionEntity.getPartyId() );
        sessionDto.setStartDateTime( sessionEntity.getStartDateTime() );
        sessionDto.setEndDateTime( sessionEntity.getEndDateTime() );
        sessionDto.setKwh( sessionEntity.getKwh() );
        sessionDto.setAuthMethod( sessionEntity.getAuthMethod() );
        sessionDto.setAuthorizationReference( sessionEntity.getAuthorizationReference() );
        sessionDto.setLocationId( sessionEntity.getLocationId() );
        sessionDto.setEvseUid( sessionEntity.getEvseUid() );
        sessionDto.setConnectorId( sessionEntity.getConnectorId() );
        sessionDto.setMeterId( sessionEntity.getMeterId() );
        sessionDto.setCurrency( sessionEntity.getCurrency() );
        sessionDto.setChargingPeriods( chargingPeriodListToChargingPeriodList1( sessionEntity.getChargingPeriods() ) );
        Map<String, Object> map = sessionEntity.getTotalCost();
        if ( map != null ) {
            sessionDto.setTotalCost( new LinkedHashMap<String, Object>( map ) );
        }
        sessionDto.setStatus( sessionEntity.getStatus() );
        sessionDto.setLastUpdated( sessionEntity.getLastUpdated() );

        return sessionDto;
    }

    @Override
    public Tariff tariffToEntity(TariffDto tariff) {
        if ( tariff == null ) {
            return null;
        }

        Tariff tariff1 = new Tariff();

        tariff1.setTariffId( tariff.getTariffId() );
        tariff1.setCountryCode( tariff.getCountryCode() );
        tariff1.setPartyId( tariff.getPartyId() );
        tariff1.setCurrency( tariff.getCurrency() );
        tariff1.setType( tariff.getType() );
        List<Map<String, Object>> list = tariff.getTariffAltText();
        if ( list != null ) {
            tariff1.setTariffAltText( new ArrayList<Map<String, Object>>( list ) );
        }
        tariff1.setTariffAltUrl( tariff.getTariffAltUrl() );
        Map<String, Object> map = tariff.getMinPrice();
        if ( map != null ) {
            tariff1.setMinPrice( new LinkedHashMap<String, Object>( map ) );
        }
        Map<String, Object> map1 = tariff.getMaxPrice();
        if ( map1 != null ) {
            tariff1.setMaxPrice( new LinkedHashMap<String, Object>( map1 ) );
        }
        tariff1.setElements( tariffElementDtoListToTariffElementList( tariff.getElements() ) );
        tariff1.setStartDateTime( tariff.getStartDateTime() );
        tariff1.setEndDateTime( tariff.getEndDateTime() );
        Map<String, Object> map2 = tariff.getEnergyMix();
        if ( map2 != null ) {
            tariff1.setEnergyMix( new LinkedHashMap<String, Object>( map2 ) );
        }
        tariff1.setLastUpdated( tariff.getLastUpdated() );

        return tariff1;
    }

    @Override
    public TariffDto tariffFromEntity(Tariff tariff) {
        if ( tariff == null ) {
            return null;
        }

        TariffDto tariffDto = new TariffDto();

        tariffDto.setTariffId( tariff.getTariffId() );
        tariffDto.setCountryCode( tariff.getCountryCode() );
        tariffDto.setPartyId( tariff.getPartyId() );
        tariffDto.setCurrency( tariff.getCurrency() );
        tariffDto.setType( tariff.getType() );
        List<Map<String, Object>> list = tariff.getTariffAltText();
        if ( list != null ) {
            tariffDto.setTariffAltText( new ArrayList<Map<String, Object>>( list ) );
        }
        tariffDto.setTariffAltUrl( tariff.getTariffAltUrl() );
        Map<String, Object> map = tariff.getMinPrice();
        if ( map != null ) {
            tariffDto.setMinPrice( new LinkedHashMap<String, Object>( map ) );
        }
        Map<String, Object> map1 = tariff.getMaxPrice();
        if ( map1 != null ) {
            tariffDto.setMaxPrice( new LinkedHashMap<String, Object>( map1 ) );
        }
        tariffDto.setElements( tariffElementListToTariffElementDtoList( tariff.getElements() ) );
        tariffDto.setStartDateTime( tariff.getStartDateTime() );
        tariffDto.setEndDateTime( tariff.getEndDateTime() );
        Map<String, Object> map2 = tariff.getEnergyMix();
        if ( map2 != null ) {
            tariffDto.setEnergyMix( new LinkedHashMap<String, Object>( map2 ) );
        }
        tariffDto.setLastUpdated( tariff.getLastUpdated() );

        return tariffDto;
    }

    @Override
    public CdrDto cdrFromEntity(Cdr cdrEntity) {
        if ( cdrEntity == null ) {
            return null;
        }

        CdrDto.CdrDtoBuilder cdrDto = CdrDto.builder();

        cdrDto.cdrLocation( cdrToCdrLocation( cdrEntity ) );
        cdrDto.cdrToken( cdrToCdrToken( cdrEntity ) );
        cdrDto.cdrId( cdrEntity.getCdrId() );
        cdrDto.countryCode( cdrEntity.getCountryCode() );
        cdrDto.partyId( cdrEntity.getPartyId() );
        cdrDto.startDateTime( cdrEntity.getStartDateTime() );
        cdrDto.endDateTime( cdrEntity.getEndDateTime() );
        cdrDto.sessionId( cdrEntity.getSessionId() );
        cdrDto.authMethod( cdrEntity.getAuthMethod() );
        cdrDto.authorizationReference( cdrEntity.getAuthorizationReference() );
        cdrDto.meterId( cdrEntity.getMeterId() );
        cdrDto.currency( cdrEntity.getCurrency() );
        List<Map<String, Object>> list = cdrEntity.getTariffs();
        if ( list != null ) {
            cdrDto.tariffs( new ArrayList<Map<String, Object>>( list ) );
        }
        List<Map<String, Object>> list1 = cdrEntity.getChargingPeriods();
        if ( list1 != null ) {
            cdrDto.chargingPeriods( new ArrayList<Map<String, Object>>( list1 ) );
        }
        Map<String, Object> map = cdrEntity.getSignedData();
        if ( map != null ) {
            cdrDto.signedData( new LinkedHashMap<String, Object>( map ) );
        }
        Map<String, Object> map1 = cdrEntity.getTotalCost();
        if ( map1 != null ) {
            cdrDto.totalCost( new LinkedHashMap<String, Object>( map1 ) );
        }
        Map<String, Object> map2 = cdrEntity.getTotalFixedCost();
        if ( map2 != null ) {
            cdrDto.totalFixedCost( new LinkedHashMap<String, Object>( map2 ) );
        }
        cdrDto.totalEnergy( cdrEntity.getTotalEnergy() );
        Map<String, Object> map3 = cdrEntity.getTotalEnergyCost();
        if ( map3 != null ) {
            cdrDto.totalEnergyCost( new LinkedHashMap<String, Object>( map3 ) );
        }
        cdrDto.totalTime( cdrEntity.getTotalTime() );
        Map<String, Object> map4 = cdrEntity.getTotalTimeCost();
        if ( map4 != null ) {
            cdrDto.totalTimeCost( new LinkedHashMap<String, Object>( map4 ) );
        }
        cdrDto.totalParkingTime( cdrEntity.getTotalParkingTime() );
        Map<String, Object> map5 = cdrEntity.getTotalParkingCost();
        if ( map5 != null ) {
            cdrDto.totalParkingCost( new LinkedHashMap<String, Object>( map5 ) );
        }
        Map<String, Object> map6 = cdrEntity.getTotalReservationCost();
        if ( map6 != null ) {
            cdrDto.totalReservationCost( new LinkedHashMap<String, Object>( map6 ) );
        }
        cdrDto.remark( cdrEntity.getRemark() );
        cdrDto.invoiceReferenceId( cdrEntity.getInvoiceReferenceId() );
        cdrDto.credit( cdrEntity.getCredit() );
        cdrDto.creditReferenceId( cdrEntity.getCreditReferenceId() );
        cdrDto.homeChargingCompensation( cdrEntity.getHomeChargingCompensation() );
        cdrDto.lastUpdated( cdrEntity.getLastUpdated() );

        return cdrDto.build();
    }

    @Override
    public Cdr cdrToEntity(CdrDto cdrDto) {
        if ( cdrDto == null ) {
            return null;
        }

        Cdr cdr = new Cdr();

        cdr.setCdrId( cdrDto.getCdrId() );
        cdr.setCdrTokenId( cdrDtoCdrTokenUid( cdrDto ) );
        cdr.setCdrLocationId( cdrDtoCdrLocationLocationId( cdrDto ) );
        cdr.setEvseUid( cdrDtoCdrLocationEvseUid( cdrDto ) );
        cdr.setEvseId( cdrDtoCdrLocationEvseId( cdrDto ) );
        cdr.setConnectorId( cdrDtoCdrLocationConnectorId( cdrDto ) );
        cdr.setCountryCode( cdrDto.getCountryCode() );
        cdr.setPartyId( cdrDto.getPartyId() );
        cdr.setStartDateTime( cdrDto.getStartDateTime() );
        cdr.setEndDateTime( cdrDto.getEndDateTime() );
        cdr.setSessionId( cdrDto.getSessionId() );
        cdr.setAuthMethod( cdrDto.getAuthMethod() );
        cdr.setAuthorizationReference( cdrDto.getAuthorizationReference() );
        cdr.setMeterId( cdrDto.getMeterId() );
        cdr.setCurrency( cdrDto.getCurrency() );
        List<Map<String, Object>> list = cdrDto.getTariffs();
        if ( list != null ) {
            cdr.setTariffs( new ArrayList<Map<String, Object>>( list ) );
        }
        List<Map<String, Object>> list1 = cdrDto.getChargingPeriods();
        if ( list1 != null ) {
            cdr.setChargingPeriods( new ArrayList<Map<String, Object>>( list1 ) );
        }
        Map<String, Object> map = cdrDto.getSignedData();
        if ( map != null ) {
            cdr.setSignedData( new LinkedHashMap<String, Object>( map ) );
        }
        Map<String, Object> map1 = cdrDto.getTotalCost();
        if ( map1 != null ) {
            cdr.setTotalCost( new LinkedHashMap<String, Object>( map1 ) );
        }
        Map<String, Object> map2 = cdrDto.getTotalFixedCost();
        if ( map2 != null ) {
            cdr.setTotalFixedCost( new LinkedHashMap<String, Object>( map2 ) );
        }
        cdr.setTotalEnergy( cdrDto.getTotalEnergy() );
        Map<String, Object> map3 = cdrDto.getTotalEnergyCost();
        if ( map3 != null ) {
            cdr.setTotalEnergyCost( new LinkedHashMap<String, Object>( map3 ) );
        }
        cdr.setTotalTime( cdrDto.getTotalTime() );
        Map<String, Object> map4 = cdrDto.getTotalTimeCost();
        if ( map4 != null ) {
            cdr.setTotalTimeCost( new LinkedHashMap<String, Object>( map4 ) );
        }
        cdr.setTotalParkingTime( cdrDto.getTotalParkingTime() );
        Map<String, Object> map5 = cdrDto.getTotalParkingCost();
        if ( map5 != null ) {
            cdr.setTotalParkingCost( new LinkedHashMap<String, Object>( map5 ) );
        }
        Map<String, Object> map6 = cdrDto.getTotalReservationCost();
        if ( map6 != null ) {
            cdr.setTotalReservationCost( new LinkedHashMap<String, Object>( map6 ) );
        }
        cdr.setRemark( cdrDto.getRemark() );
        cdr.setInvoiceReferenceId( cdrDto.getInvoiceReferenceId() );
        cdr.setCredit( cdrDto.getCredit() );
        cdr.setCreditReferenceId( cdrDto.getCreditReferenceId() );
        cdr.setHomeChargingCompensation( cdrDto.getHomeChargingCompensation() );
        cdr.setLastUpdated( cdrDto.getLastUpdated() );

        return cdr;
    }

    @Override
    public CdrLocation cdrLocationFromLocationDto(LocationDto locationDto, Connector connectorDto) {
        if ( locationDto == null && connectorDto == null ) {
            return null;
        }

        CdrLocation cdrLocation = new CdrLocation();

        if ( locationDto != null ) {
            cdrLocation.setLocationId( locationDto.getId() );
            cdrLocation.setName( locationDto.getName() );
            cdrLocation.setAddress( locationDto.getAddress() );
            cdrLocation.setCity( locationDto.getCity() );
            cdrLocation.setState( locationDto.getState() );
            cdrLocation.setCountry( locationDto.getCountry() );
            cdrLocation.setCoordinates( stringStringMapToStringObjectMap( locationDto.getCoordinates() ) );
        }
        if ( connectorDto != null ) {
            cdrLocation.setConnectorId( connectorDto.getId() );
        }

        return cdrLocation;
    }

    @Override
    public TokenDto tokenFromEntity(Token token) {
        if ( token == null ) {
            return null;
        }

        TokenDto tokenDto = new TokenDto();

        tokenDto.setUid( token.getUid() );
        tokenDto.setCountryCode( token.getCountryCode() );
        tokenDto.setPartyId( token.getPartyId() );
        tokenDto.setType( token.getType() );
        tokenDto.setContractId( token.getContractId() );
        tokenDto.setVisualNumber( token.getVisualNumber() );
        tokenDto.setIssuer( token.getIssuer() );
        tokenDto.setGroupId( token.getGroupId() );
        tokenDto.setValid( token.getValid() );
        tokenDto.setWhitelist( token.getWhitelist() );
        tokenDto.setLanguage( token.getLanguage() );
        tokenDto.setDefaultProfileType( token.getDefaultProfileType() );
        Map<String, Object> map = token.getEnergyContract();
        if ( map != null ) {
            tokenDto.setEnergyContract( new LinkedHashMap<String, Object>( map ) );
        }
        tokenDto.setLastUpdated( token.getLastUpdated() );

        return tokenDto;
    }

    @Override
    public List<TokenDto> tokenAllFromEntity(List<Token> token) {
        if ( token == null ) {
            return null;
        }

        List<TokenDto> list = new ArrayList<TokenDto>( token.size() );
        for ( Token token1 : token ) {
            list.add( tokenFromEntity( token1 ) );
        }

        return list;
    }

    @Override
    public Token tokenToEntity(TokenDto tokenDto) {
        if ( tokenDto == null ) {
            return null;
        }

        Token token = new Token();

        token.setUid( tokenDto.getUid() );
        token.setCountryCode( tokenDto.getCountryCode() );
        token.setPartyId( tokenDto.getPartyId() );
        token.setType( tokenDto.getType() );
        token.setContractId( tokenDto.getContractId() );
        token.setVisualNumber( tokenDto.getVisualNumber() );
        token.setIssuer( tokenDto.getIssuer() );
        token.setGroupId( tokenDto.getGroupId() );
        token.setValid( tokenDto.getValid() );
        token.setWhitelist( tokenDto.getWhitelist() );
        token.setLanguage( tokenDto.getLanguage() );
        token.setDefaultProfileType( tokenDto.getDefaultProfileType() );
        Map<String, Object> map = tokenDto.getEnergyContract();
        if ( map != null ) {
            token.setEnergyContract( new LinkedHashMap<String, Object>( map ) );
        }
        token.setLastUpdated( tokenDto.getLastUpdated() );

        return token;
    }

    @Override
    public VersionDetailsEntity VersionDetailsToEntity(VersionDetailsDTO versionDetailsDto) {
        if ( versionDetailsDto == null ) {
            return null;
        }

        VersionDetailsEntity versionDetailsEntity = new VersionDetailsEntity();

        versionDetailsEntity.setId( versionDetailsDto.getId() );
        versionDetailsEntity.setVersion( versionDetailsDto.getVersion() );
        List<Map<String, Object>> list = versionDetailsDto.getEndpoints();
        if ( list != null ) {
            versionDetailsEntity.setEndpoints( new ArrayList<Map<String, Object>>( list ) );
        }

        return versionDetailsEntity;
    }

    @Override
    public VersionEntity VersionToEntity(VersionDto versionDto) {
        if ( versionDto == null ) {
            return null;
        }

        VersionEntity versionEntity = new VersionEntity();

        versionEntity.setId( versionDto.getId() );
        versionEntity.setVersion( versionDto.getVersion() );
        versionEntity.setUrl( versionDto.getUrl() );

        return versionEntity;
    }

    @Override
    public List<VersionDto> VersionFromEntity(List<VersionEntity> entity) {
        if ( entity == null ) {
            return null;
        }

        List<VersionDto> list = new ArrayList<VersionDto>( entity.size() );
        for ( VersionEntity versionEntity : entity ) {
            list.add( VersionSingleFromEntity( versionEntity ) );
        }

        return list;
    }

    @Override
    public VersionDto VersionSingleFromEntity(VersionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        VersionDto versionDto = new VersionDto();

        versionDto.setId( entity.getId() );
        versionDto.setVersion( entity.getVersion() );
        versionDto.setUrl( entity.getUrl() );

        return versionDto;
    }

    @Override
    public VersionDetailsDTO VersionDetailsFromEntity(VersionDetailsEntity entity) {
        if ( entity == null ) {
            return null;
        }

        VersionDetailsDTO versionDetailsDTO = new VersionDetailsDTO();

        versionDetailsDTO.setId( entity.getId() );
        versionDetailsDTO.setVersion( entity.getVersion() );
        List<Map<String, Object>> list = entity.getEndpoints();
        if ( list != null ) {
            versionDetailsDTO.setEndpoints( new ArrayList<Map<String, Object>>( list ) );
        }

        return versionDetailsDTO;
    }

    @Override
    public UserResponse UserfromEntity(UserInfo entity) {
        if ( entity == null ) {
            return null;
        }

        UserResponse userResponse = new UserResponse();

        userResponse.setId( entity.getId() );
        userResponse.setUsername( entity.getUsername() );
        userResponse.setEmail( entity.getEmail() );
        userResponse.setName( entity.getName() );
        userResponse.setPhoneNumber( entity.getPhoneNumber() );
        Set<UserRole> set = entity.getRoles();
        if ( set != null ) {
            userResponse.setRoles( new LinkedHashSet<UserRole>( set ) );
        }

        return userResponse;
    }

    @Override
    public PartnerDTO PartnerfromEntity(PartnerEntity partnerEntity) {
        if ( partnerEntity == null ) {
            return null;
        }

        PartnerDTO partnerDTO = new PartnerDTO();

        partnerDTO.setPid( partnerEntity.getPid() );
        partnerDTO.setUid( partnerEntity.getUid() );
        partnerDTO.setCountry_code( partnerEntity.getCountry_code() );
        partnerDTO.setParty_id( partnerEntity.getParty_id() );
        partnerDTO.setStatus( partnerEntity.getStatus() );
        partnerDTO.setStatus_message( partnerEntity.getStatus_message() );
        partnerDTO.setFrom_token( partnerEntity.getFrom_token() );
        partnerDTO.setRoles( partnerEntity.getRoles() );
        partnerDTO.setCreated_at( partnerEntity.getCreated_at() );
        partnerDTO.setLast_updated( partnerEntity.getLast_updated() );

        return partnerDTO;
    }

    protected List<EvsesEntity> eVSEListToEvsesEntityList(List<EVSE> list) {
        if ( list == null ) {
            return null;
        }

        List<EvsesEntity> list1 = new ArrayList<EvsesEntity>( list.size() );
        for ( EVSE eVSE : list ) {
            list1.add( toEntity( eVSE ) );
        }

        return list1;
    }

    protected List<EVSE> evsesEntityListToEVSEList(List<EvsesEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<EVSE> list1 = new ArrayList<EVSE>( list.size() );
        for ( EvsesEntity evsesEntity : list ) {
            list1.add( fromEntity( evsesEntity ) );
        }

        return list1;
    }

    protected List<ConnectorEntity> connectorListToConnectorEntityList(List<Connector> list) {
        if ( list == null ) {
            return null;
        }

        List<ConnectorEntity> list1 = new ArrayList<ConnectorEntity>( list.size() );
        for ( Connector connector : list ) {
            list1.add( toEntity( connector ) );
        }

        return list1;
    }

    protected List<Connector> connectorEntityListToConnectorList(List<ConnectorEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Connector> list1 = new ArrayList<Connector>( list.size() );
        for ( ConnectorEntity connectorEntity : list ) {
            list1.add( fromEntity( connectorEntity ) );
        }

        return list1;
    }

    private String sessionCdrTokenUid(SessionDto sessionDto) {
        if ( sessionDto == null ) {
            return null;
        }
        CdrToken cdrToken = sessionDto.getCdrToken();
        if ( cdrToken == null ) {
            return null;
        }
        String uid = cdrToken.getUid();
        if ( uid == null ) {
            return null;
        }
        return uid;
    }

    protected com.lge.ocpi.emsp.model.entity.session.ChargingPeriod chargingPeriodToChargingPeriod(ChargingPeriod chargingPeriod) {
        if ( chargingPeriod == null ) {
            return null;
        }

        com.lge.ocpi.emsp.model.entity.session.ChargingPeriod chargingPeriod1 = new com.lge.ocpi.emsp.model.entity.session.ChargingPeriod();

        chargingPeriod1.setStartDateTime( chargingPeriod.getStartDateTime() );
        List<Map<String, Object>> list = chargingPeriod.getDimensions();
        if ( list != null ) {
            chargingPeriod1.setDimensions( new ArrayList<Map<String, Object>>( list ) );
        }
        chargingPeriod1.setTariffId( chargingPeriod.getTariffId() );

        return chargingPeriod1;
    }

    protected List<com.lge.ocpi.emsp.model.entity.session.ChargingPeriod> chargingPeriodListToChargingPeriodList(List<ChargingPeriod> list) {
        if ( list == null ) {
            return null;
        }

        List<com.lge.ocpi.emsp.model.entity.session.ChargingPeriod> list1 = new ArrayList<com.lge.ocpi.emsp.model.entity.session.ChargingPeriod>( list.size() );
        for ( ChargingPeriod chargingPeriod : list ) {
            list1.add( chargingPeriodToChargingPeriod( chargingPeriod ) );
        }

        return list1;
    }

    protected CdrToken sessionToCdrToken(Session session) {
        if ( session == null ) {
            return null;
        }

        CdrToken cdrToken = new CdrToken();

        cdrToken.setUid( session.getCdrTokenId() );

        return cdrToken;
    }

    protected ChargingPeriod chargingPeriodToChargingPeriod1(com.lge.ocpi.emsp.model.entity.session.ChargingPeriod chargingPeriod) {
        if ( chargingPeriod == null ) {
            return null;
        }

        ChargingPeriod chargingPeriod1 = new ChargingPeriod();

        chargingPeriod1.setStartDateTime( chargingPeriod.getStartDateTime() );
        List<Map<String, Object>> list = chargingPeriod.getDimensions();
        if ( list != null ) {
            chargingPeriod1.setDimensions( new ArrayList<Map<String, Object>>( list ) );
        }
        chargingPeriod1.setTariffId( chargingPeriod.getTariffId() );

        return chargingPeriod1;
    }

    protected List<ChargingPeriod> chargingPeriodListToChargingPeriodList1(List<com.lge.ocpi.emsp.model.entity.session.ChargingPeriod> list) {
        if ( list == null ) {
            return null;
        }

        List<ChargingPeriod> list1 = new ArrayList<ChargingPeriod>( list.size() );
        for ( com.lge.ocpi.emsp.model.entity.session.ChargingPeriod chargingPeriod : list ) {
            list1.add( chargingPeriodToChargingPeriod1( chargingPeriod ) );
        }

        return list1;
    }

    protected TariffElement tariffElementDtoToTariffElement(TariffElementDto tariffElementDto) {
        if ( tariffElementDto == null ) {
            return null;
        }

        TariffElement tariffElement = new TariffElement();

        List<Map<String, Object>> list = tariffElementDto.getPriceComponents();
        if ( list != null ) {
            tariffElement.setPriceComponents( new ArrayList<Map<String, Object>>( list ) );
        }
        Map<String, Object> map = tariffElementDto.getRestrictions();
        if ( map != null ) {
            tariffElement.setRestrictions( new LinkedHashMap<String, Object>( map ) );
        }

        return tariffElement;
    }

    protected List<TariffElement> tariffElementDtoListToTariffElementList(List<TariffElementDto> list) {
        if ( list == null ) {
            return null;
        }

        List<TariffElement> list1 = new ArrayList<TariffElement>( list.size() );
        for ( TariffElementDto tariffElementDto : list ) {
            list1.add( tariffElementDtoToTariffElement( tariffElementDto ) );
        }

        return list1;
    }

    protected TariffElementDto tariffElementToTariffElementDto(TariffElement tariffElement) {
        if ( tariffElement == null ) {
            return null;
        }

        TariffElementDto tariffElementDto = new TariffElementDto();

        List<Map<String, Object>> list = tariffElement.getPriceComponents();
        if ( list != null ) {
            tariffElementDto.setPriceComponents( new ArrayList<Map<String, Object>>( list ) );
        }
        Map<String, Object> map = tariffElement.getRestrictions();
        if ( map != null ) {
            tariffElementDto.setRestrictions( new LinkedHashMap<String, Object>( map ) );
        }

        return tariffElementDto;
    }

    protected List<TariffElementDto> tariffElementListToTariffElementDtoList(List<TariffElement> list) {
        if ( list == null ) {
            return null;
        }

        List<TariffElementDto> list1 = new ArrayList<TariffElementDto>( list.size() );
        for ( TariffElement tariffElement : list ) {
            list1.add( tariffElementToTariffElementDto( tariffElement ) );
        }

        return list1;
    }

    protected CdrLocation cdrToCdrLocation(Cdr cdr) {
        if ( cdr == null ) {
            return null;
        }

        CdrLocation cdrLocation = new CdrLocation();

        cdrLocation.setEvseUid( cdr.getEvseUid() );
        cdrLocation.setEvseId( cdr.getEvseId() );
        cdrLocation.setLocationId( cdr.getCdrLocationId() );
        cdrLocation.setConnectorId( cdr.getConnectorId() );

        return cdrLocation;
    }

    protected CdrToken cdrToCdrToken(Cdr cdr) {
        if ( cdr == null ) {
            return null;
        }

        CdrToken cdrToken = new CdrToken();

        cdrToken.setUid( cdr.getCdrTokenId() );

        return cdrToken;
    }

    private String cdrDtoCdrTokenUid(CdrDto cdrDto) {
        if ( cdrDto == null ) {
            return null;
        }
        CdrToken cdrToken = cdrDto.getCdrToken();
        if ( cdrToken == null ) {
            return null;
        }
        String uid = cdrToken.getUid();
        if ( uid == null ) {
            return null;
        }
        return uid;
    }

    private String cdrDtoCdrLocationLocationId(CdrDto cdrDto) {
        if ( cdrDto == null ) {
            return null;
        }
        CdrLocation cdrLocation = cdrDto.getCdrLocation();
        if ( cdrLocation == null ) {
            return null;
        }
        String locationId = cdrLocation.getLocationId();
        if ( locationId == null ) {
            return null;
        }
        return locationId;
    }

    private String cdrDtoCdrLocationEvseUid(CdrDto cdrDto) {
        if ( cdrDto == null ) {
            return null;
        }
        CdrLocation cdrLocation = cdrDto.getCdrLocation();
        if ( cdrLocation == null ) {
            return null;
        }
        String evseUid = cdrLocation.getEvseUid();
        if ( evseUid == null ) {
            return null;
        }
        return evseUid;
    }

    private String cdrDtoCdrLocationEvseId(CdrDto cdrDto) {
        if ( cdrDto == null ) {
            return null;
        }
        CdrLocation cdrLocation = cdrDto.getCdrLocation();
        if ( cdrLocation == null ) {
            return null;
        }
        String evseId = cdrLocation.getEvseId();
        if ( evseId == null ) {
            return null;
        }
        return evseId;
    }

    private String cdrDtoCdrLocationConnectorId(CdrDto cdrDto) {
        if ( cdrDto == null ) {
            return null;
        }
        CdrLocation cdrLocation = cdrDto.getCdrLocation();
        if ( cdrLocation == null ) {
            return null;
        }
        String connectorId = cdrLocation.getConnectorId();
        if ( connectorId == null ) {
            return null;
        }
        return connectorId;
    }

    protected Map<String, Object> stringStringMapToStringObjectMap(Map<String, String> map) {
        if ( map == null ) {
            return null;
        }

        Map<String, Object> map1 = new LinkedHashMap<String, Object>( Math.max( (int) ( map.size() / .75f ) + 1, 16 ) );

        for ( java.util.Map.Entry<String, String> entry : map.entrySet() ) {
            String key = entry.getKey();
            Object value = entry.getValue();
            map1.put( key, value );
        }

        return map1;
    }
}
