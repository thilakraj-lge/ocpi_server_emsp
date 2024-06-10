package com.lge.ocpi.emsp.controller.cpo;

import com.lge.ocpi.emsp.helper.OcpiStatusCode;
import com.lge.ocpi.emsp.helper.ResponseFormat;
import com.lge.ocpi.emsp.mapstruct.CommonMapper;
import com.lge.ocpi.emsp.model.dto.session.SessionDto;
import com.lge.ocpi.emsp.model.entity.session.Session;
import com.lge.ocpi.emsp.service.session.SessionService;
import jakarta.validation.Valid;
import com.lge.ocpi.emsp.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@Slf4j
@RequestMapping(Constants.Endponints.emsp_endpoint_url_server + "sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    CommonMapper mapper;

    @PutMapping("/{country_code}/{party_id}/{session_id}")
    public ResponseEntity<ResponseFormat<SessionDto>> pushSession(@RequestBody @Valid SessionDto session,
                                                                  @PathVariable(value = "country_code") String countryCode,
                                                                  @PathVariable(value = "party_id") String partyId,
                                                                  @PathVariable(value = "session_id") Long sessionId) {
        Session sessionEntity = mapper.sessionToEntity(session);
        SessionDto sessionDto = sessionService.pushSession(sessionEntity);
        ResponseFormat<SessionDto> responseFormat;
        if (countryCode.isEmpty() || partyId.isEmpty()) {
            responseFormat = new ResponseFormat<SessionDto>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            responseFormat = new ResponseFormat<SessionDto>()
                    .build(OcpiStatusCode.SUCCESS, sessionDto);
        }
        return ResponseEntity.ok(responseFormat);

    }

    @PatchMapping("/{country_code}/{party_id}/{session_id}")
    public ResponseEntity<ResponseFormat<SessionDto>> updateSession(
            @RequestBody @Valid SessionDto session,
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "session_id") String sessionId) {
        Session sessionEntity = mapper.sessionToEntity(session);
        SessionDto sessionDto = sessionService.pushSession(sessionEntity);
        ResponseFormat<SessionDto> responseFormat;
        if (countryCode.isEmpty() || partyId.isEmpty()) {
            responseFormat = new ResponseFormat<SessionDto>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            responseFormat = new ResponseFormat<SessionDto>()
                    .build(OcpiStatusCode.SUCCESS, sessionDto);
        }
        return ResponseEntity.ok(responseFormat);
    }

    @GetMapping(value = "/{country_code}/{party_id}/{session_id}", produces = "application/json")
    public ResponseEntity<SessionDto> getSession(
            @PathVariable(value = "country_code") String countryCode,
            @PathVariable(value = "party_id") String partyId,
            @PathVariable(value = "session_id") Long sessionId) {
        SessionDto sessionDto = sessionService.getSession(sessionId);
        return ResponseEntity.ok(sessionDto);
    }

    @GetMapping("/all-session")
    public ResponseEntity<ResponseFormat<List<SessionDto>>> getAllSessions() {
        List<SessionDto> sessionList = sessionService.getAllSessions();

        ResponseFormat<List<SessionDto>> responseFormat;
        if (sessionList.isEmpty()) {
            responseFormat = new ResponseFormat<List<SessionDto>>()
                    .build(OcpiStatusCode.INVALID_PARAMETERS);
        } else {
            responseFormat = new ResponseFormat<List<SessionDto>>()
                    .build(OcpiStatusCode.SUCCESS, sessionList);
        }
        return ResponseEntity.ok(responseFormat);
    }
}
