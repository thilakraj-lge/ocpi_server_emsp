package com.lge.ocpi.emsp.service.session;

import com.lge.ocpi.emsp.model.dto.session.SessionDto;

import java.util.List;

public interface SessionService {

    List<SessionDto> getAllSessions();

    SessionDto pushSession(com.lge.ocpi.emsp.model.entity.session.Session sessionEntity);

    SessionDto getSession(Long sessionId);
}
