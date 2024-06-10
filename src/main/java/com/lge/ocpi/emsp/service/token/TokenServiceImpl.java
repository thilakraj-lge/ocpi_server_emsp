package com.lge.ocpi.emsp.service.token;

import com.lge.ocpi.emsp.mapstruct.CommonMapper;
import com.lge.ocpi.emsp.model.dto.Token.AuthorizationInfoDto;
import com.lge.ocpi.emsp.model.dto.Token.LocationReferencesDto;
import com.lge.ocpi.emsp.model.dto.Token.TokenDto;
import com.lge.ocpi.emsp.model.entity.token.Token;
import com.lge.ocpi.emsp.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.lge.ocpi.emsp.model.enums.AllowedType.ALLOWED;
import static com.lge.ocpi.emsp.model.enums.AllowedType.BLOCKED;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    TokenRepository tokenRepository;

    @Override
    public TokenDto getTokenById(String tokenId) {
        Optional<Token> tokenEntity = tokenRepository.findById(tokenId);
        return tokenEntity.map(CommonMapper.INSTANCE::tokenFromEntity).orElse(null);
    }

    @Override
    public List<TokenDto> getAllToken() {
        List<Token> tokenEntity = tokenRepository.findAll();
        return CommonMapper.INSTANCE.tokenAllFromEntity(tokenEntity);
    }


    @Override
    public TokenDto putToken(Token token) {
        Token tokenEntity = tokenRepository.save(token);
        return CommonMapper.INSTANCE.tokenFromEntity(tokenEntity);
    }

    @Override
    public AuthorizationInfoDto postToken(String tokenUid, String tokenType, LocationReferencesDto locationReferences) {
        Token token = tokenRepository.findTokenByID(tokenUid);
        AuthorizationInfoDto authorizationInfoDto = new AuthorizationInfoDto();
        if (token != null) {
            if (token.getValid()) {
                TokenDto tokenDto = CommonMapper.INSTANCE.tokenFromEntity(token);
                authorizationInfoDto.setLocation(locationReferences);
                authorizationInfoDto.setToken(tokenDto);
                authorizationInfoDto.setAllowed(ALLOWED);

            } else {
                TokenDto tokenDto = CommonMapper.INSTANCE.tokenFromEntity(token);
                authorizationInfoDto.setLocation(locationReferences);
                authorizationInfoDto.setToken(tokenDto);
                authorizationInfoDto.setAllowed(BLOCKED);
            }

        } else {
            authorizationInfoDto = null;
        }


        return authorizationInfoDto;
    }

}
