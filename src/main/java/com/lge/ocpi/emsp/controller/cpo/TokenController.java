package com.lge.ocpi.emsp.controller.cpo;

import com.lge.ocpi.emsp.helper.OcpiStatusCode;
import com.lge.ocpi.emsp.helper.ResponseFormat;
import com.lge.ocpi.emsp.mapstruct.CommonMapper;
import com.lge.ocpi.emsp.model.dto.Token.AuthorizationInfoDto;
import com.lge.ocpi.emsp.model.dto.Token.LocationReferencesDto;
import com.lge.ocpi.emsp.model.dto.Token.TokenDto;
import com.lge.ocpi.emsp.model.entity.token.Token;
import com.lge.ocpi.emsp.service.token.TokenService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import com.lge.ocpi.emsp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(Constants.Endponints.emsp_endpoint_url_server + "tokens")
public class TokenController {

    @Autowired
    TokenService tokenService;

    @Autowired
    CommonMapper mapper;

    @GetMapping(value = "/{country_code}/{party_id}/{token_id}", produces = "application/json")
    public ResponseEntity<TokenDto> getToken(
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "token_id") String tokenId) {
        TokenDto tokenDto = tokenService.getTokenById(tokenId);
        return ResponseEntity.ok(tokenDto);
    }

    @GetMapping("/get-all-token")
    public ResponseEntity<ResponseFormat<List<TokenDto>>> getAllToken() {
        List<TokenDto> tokenDto = tokenService.getAllToken();

        ResponseFormat<List<TokenDto>> responseFormat;
        if (tokenDto.isEmpty()) {
            responseFormat = new ResponseFormat<List<TokenDto>>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            responseFormat = new ResponseFormat<List<TokenDto>>()
                    .build(OcpiStatusCode.SUCCESS, tokenDto);
        }
        return ResponseEntity.ok(responseFormat);
    }

    @PutMapping("/push-token")
    public ResponseEntity<ResponseFormat<TokenDto>> putToken(@RequestBody @Valid TokenDto token) {
        Token tokenEntity = mapper.tokenToEntity(token);
        TokenDto tokenDto = tokenService.putToken(tokenEntity);
        ResponseFormat<TokenDto> responseFormat;
        if (token == null) {
            responseFormat = new ResponseFormat<TokenDto>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            responseFormat = new ResponseFormat<TokenDto>()
                    .build(OcpiStatusCode.SUCCESS, tokenDto);
        }
        return ResponseEntity.ok(responseFormat);
    }


    /**
     * Real-time authorization request
     *
     * @param tokenUid           Token.uid of the Token for which authorization is requested.
     * @param tokenType          Token.type of the Token for which this authorization is. Default if omitted: RFID
     * @param locationReferences Location and EVSEs for which the Token is requested to be authorized.
     * @return Contains information about the authorization, if the Token is allowed to charge and optionally
     * which EVSEs are allowed to be used.
     */
    @PostMapping("/{token_uid}/authorize")
    public ResponseEntity<ResponseFormat<AuthorizationInfoDto>> postToken(
            @PathVariable(value = "token_uid") @Size(min = 1, max = 36) String tokenUid,
            @RequestParam(value = "type", required = false) String tokenType,
            @RequestBody @Valid LocationReferencesDto locationReferences) {

        ResponseFormat<AuthorizationInfoDto> responseFormat;
        if (locationReferences == null) {
            responseFormat = new ResponseFormat<AuthorizationInfoDto>()
                    .build(OcpiStatusCode.NOT_ENOUGH_INFORMATION);
        } else {
            AuthorizationInfoDto authorizationInfoDto = tokenService.postToken(tokenUid, tokenType, locationReferences);
            if (authorizationInfoDto != null) {
                responseFormat = new ResponseFormat<AuthorizationInfoDto>()
                        .build(OcpiStatusCode.SUCCESS, authorizationInfoDto);
            } else {
                responseFormat = new ResponseFormat<AuthorizationInfoDto>()
                        .build(OcpiStatusCode.INVALID_PARAMETERS);
            }
        }
        return ResponseEntity.ok(responseFormat);
    }

}
