package com.lge.ocpi.emsp.mapstruct;

import com.lge.ocpi.emsp.model.dto.cdr.CdrDto;
import com.lge.ocpi.emsp.model.dto.cdr.CdrLocation;
import com.lge.ocpi.emsp.model.dto.Token.TokenDto;
import com.lge.ocpi.emsp.model.dto.location.Connector;
import com.lge.ocpi.emsp.model.dto.location.EVSE;
import com.lge.ocpi.emsp.model.dto.location.LocationDto;
import com.lge.ocpi.emsp.model.dto.session.SessionDto;
import com.lge.ocpi.emsp.model.dto.tariff.TariffDto;
import com.lge.ocpi.emsp.model.dto.userDetails.UserResponse;
import com.lge.ocpi.emsp.model.dto.credentials.partner.PartnerDTO;
import com.lge.ocpi.emsp.model.dto.version.VersionDetailsDTO;
import com.lge.ocpi.emsp.model.dto.version.VersionDto;
import com.lge.ocpi.emsp.model.entity.autentication.UserInfo;
import com.lge.ocpi.emsp.model.entity.cdr.Cdr;
import com.lge.ocpi.emsp.model.entity.credentials.PartnerEntity;
import com.lge.ocpi.emsp.model.entity.location.*;
import com.lge.ocpi.emsp.model.entity.session.Session;
import com.lge.ocpi.emsp.model.entity.tariff.Tariff;
import com.lge.ocpi.emsp.model.entity.token.Token;
import com.lge.ocpi.emsp.model.entity.version.VersionDetailsEntity;
import com.lge.ocpi.emsp.model.entity.version.VersionEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface
CommonMapper {
    CommonMapper INSTANCE = Mappers.getMapper(CommonMapper.class);


    //for location
    @Mapping(source = "id", target = "id")
    LocationEntity toEntity(LocationDto locationDto);

    @Mapping(source = "id", target = "id")
    LocationDto fromEntity(LocationEntity entity);

    @InheritInverseConfiguration
    List<LocationDto> fromEntity(List<LocationEntity> entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
// Ignore ID during update
    LocationEntity updateLocation(LocationDto locationDTO, @MappingTarget LocationEntity locationEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
// Ignore ID during update
    EvsesEntity updateEvse(EVSE evse, @MappingTarget EvsesEntity evsesEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
// Ignore ID during update
    ConnectorEntity updateConnector(Connector connector, @MappingTarget ConnectorEntity connectorEntity);


    //for evse
    @Mapping(source = "uid", target = "uid")
    EvsesEntity toEntity(EVSE evse);

    @Mapping(source = "uid", target = "uid")
    EVSE fromEntity(EvsesEntity entity);


    //for connector
    @Mapping(source = "id", target = "id")
    ConnectorEntity toEntity(Connector car);

    @Mapping(source = "id", target = "id")
    Connector fromEntity(ConnectorEntity entity);

    //Sessions

    @InheritInverseConfiguration
    List<SessionDto> sessionListFromEntity(List<Session> entity);

    @Mapping(source = "sessionId", target = "sessionId")
    @Mapping(source = "cdrToken.uid", target = "cdrTokenId")
    Session sessionToEntity(SessionDto session);

    @InheritInverseConfiguration
    SessionDto sessionFromEntity(Session sessionEntity);


    //Tariff
    @Mapping(source = "tariffId", target = "tariffId")
    Tariff tariffToEntity(TariffDto tariff);

    @InheritInverseConfiguration
    TariffDto tariffFromEntity(Tariff tariff);

    //CDR
    @InheritInverseConfiguration
    @Mapping(source = "evseUid", target = "cdrLocation.evseUid")
    @Mapping(source = "evseId", target = "cdrLocation.evseId")
    CdrDto cdrFromEntity(Cdr cdrEntity);

    @Mapping(source = "cdrId", target = "cdrId")
    @Mapping(source = "cdrToken.uid", target = "cdrTokenId")
    @Mapping(source = "cdrLocation.locationId", target = "cdrLocationId")
    @Mapping(source = "cdrLocation.evseUid", target = "evseUid")
    @Mapping(source = "cdrLocation.evseId", target = "evseId")
    @Mapping(source = "cdrLocation.connectorId", target = "connectorId")
    Cdr cdrToEntity(CdrDto cdrDto);

    @Mapping(source = "locationDto.id", target = "locationId")
    @Mapping(source = "connectorDto.id", target = "connectorId")
    CdrLocation cdrLocationFromLocationDto(LocationDto locationDto, Connector connectorDto);

    //Token

    @InheritInverseConfiguration
    TokenDto tokenFromEntity(Token token);

    @InheritInverseConfiguration
    List<TokenDto> tokenAllFromEntity(List<Token> token);

    @Mapping(source = "uid", target = "uid")
    Token tokenToEntity(TokenDto tokenDto);


    //Version
    @Mapping(source = "id", target = "id")
    VersionDetailsEntity VersionDetailsToEntity(VersionDetailsDTO versionDetailsDto);

/*    @Mapping(source = "id", target = "id")
    VersionDetailsEntity VersionAllDetailsToEntity(VersionDataResponse versionDataResponse);*/

    @Mapping(source = "id", target = "id")
    VersionEntity VersionToEntity(VersionDto versionDto);

    @InheritInverseConfiguration
    List<VersionDto> VersionFromEntity(List<VersionEntity> entity);

    @InheritInverseConfiguration
    VersionDto VersionSingleFromEntity(VersionEntity entity);


    @InheritInverseConfiguration
    VersionDetailsDTO VersionDetailsFromEntity(VersionDetailsEntity entity);


    @InheritInverseConfiguration
    UserResponse UserfromEntity(UserInfo entity);

    @InheritInverseConfiguration
    PartnerDTO PartnerfromEntity(PartnerEntity partnerEntity);


}
