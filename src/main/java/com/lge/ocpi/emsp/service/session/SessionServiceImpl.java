package com.lge.ocpi.emsp.service.session;

import com.lge.ocpi.emsp.mapstruct.CommonMapper;

import com.lge.ocpi.emsp.model.dto.session.SessionDto;
import com.lge.ocpi.emsp.model.entity.session.Session;
import com.lge.ocpi.emsp.repository.SessionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public List<SessionDto> getAllSessions() {

        List<Session> sessions = sessionRepository.findAll();
        return CommonMapper.INSTANCE.sessionListFromEntity(sessions);

    }

    @Override
    public SessionDto pushSession(Session sessionEntity) {
        Session session = sessionRepository.save(sessionEntity);
        return CommonMapper.INSTANCE.sessionFromEntity(session);
    }

    @Override
    public SessionDto getSession(Long sessionId) {
        Optional<Session> sessionEntity = sessionRepository.findById(sessionId);
        if (sessionEntity.isPresent()) {
            SessionDto sessionDto = CommonMapper.INSTANCE.sessionFromEntity(sessionEntity.get());

            return sessionDto;
        } else {
            System.out.printf("No session found with id %d%n", sessionId);
            return null;
        }
    }
}

