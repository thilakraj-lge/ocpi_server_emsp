package com.lge.ocpi.emsp.service.token;

import com.lge.ocpi.emsp.model.dto.Token.AuthorizationInfoDto;
import com.lge.ocpi.emsp.model.dto.Token.LocationReferencesDto;
import com.lge.ocpi.emsp.model.dto.Token.TokenDto;
import com.lge.ocpi.emsp.model.entity.token.Token;

import java.util.List;

public interface TokenService {

    TokenDto getTokenById(String tokenId);
    List<TokenDto> getAllToken();

    TokenDto putToken(Token token);
    AuthorizationInfoDto postToken(String tokenUid, String tokenType, LocationReferencesDto locationReferences);
}
