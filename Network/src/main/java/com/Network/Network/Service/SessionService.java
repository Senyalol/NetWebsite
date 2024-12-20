package com.Network.Network.Service;

import com.Network.Network.DTO.SessionsDTO.CreateSessionsDTO;
import com.Network.Network.DTO.SessionsDTO.ShortSessionInfoDTO;
import com.Network.Network.DTO.SessionsDTO.UpdateSessionsDTO;
import com.Network.Network.Entites.Session;
import com.Network.Network.Repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SessionService {

    private final SessionRepository sessionRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public List<ShortSessionInfoDTO> getSessions() {
        List<Session> sessions = sessionRepository.findAll();

        return sessions.stream()
                .map(session -> {
                    ShortSessionInfoDTO sessionDTO = new ShortSessionInfoDTO();
                    sessionDTO.setSession_id(session.getId());
                    sessionDTO.setUser_id(session.getUser() != null ? session.getUser().getId() : null);
                    sessionDTO.setDevice_id(session.getDevice() != null ? session.getDevice().getId() : null);
                    sessionDTO.setStartTime(session.getStartTime());
                    sessionDTO.setEndTime(session.getEndTime());

                    return sessionDTO;
                }).toList();
    }

    public ShortSessionInfoDTO getSessionById(int id) {
        Session session = sessionRepository.findById(id);

        ShortSessionInfoDTO sessionDTO = new ShortSessionInfoDTO();
        sessionDTO.setSession_id(session.getId());
        sessionDTO.setUser_id(session.getUser() != null ? session.getUser().getId() : null);
        sessionDTO.setDevice_id(session.getDevice() != null ? session.getDevice().getId() : null);
        sessionDTO.setStartTime(session.getStartTime());
        sessionDTO.setEndTime(session.getEndTime());

        return sessionDTO;
    }

    public void createSession(CreateSessionsDTO createSessionsDTO) {
        Session session = new Session();
        session.setUser(new com.Network.Network.Entites.User()); // Assuming User is already loaded by ID
        session.getUser().setId(createSessionsDTO.getUser_id());
        session.setDevice(new com.Network.Network.Entites.Device()); // Assuming Device is already loaded by ID
        session.getDevice().setId(createSessionsDTO.getDevice_id());
        session.setStartTime(createSessionsDTO.getStartTime());
        session.setEndTime(createSessionsDTO.getEndTime());

        sessionRepository.save(session);
    }

    public void updateSession(int id, UpdateSessionsDTO updateSessionsDTO) {
        Session sessionToUpdate = sessionRepository.findById(id);

        if (updateSessionsDTO.getUser_id() != null) {
            sessionToUpdate.setUser(new com.Network.Network.Entites.User()); // If necessary, create a new User object
            sessionToUpdate.getUser().setId(updateSessionsDTO.getUser_id());
        }
        if (updateSessionsDTO.getDevice_id() != null) {
            sessionToUpdate.setDevice(new com.Network.Network.Entites.Device()); // If necessary, create a new Device object
            sessionToUpdate.getDevice().setId(updateSessionsDTO.getDevice_id());
        }
        if (updateSessionsDTO.getStartTime() != null) {
            sessionToUpdate.setStartTime(updateSessionsDTO.getStartTime());
        }
        if (updateSessionsDTO.getEndTime() != null) {
            sessionToUpdate.setEndTime(updateSessionsDTO.getEndTime());
        }

        sessionRepository.save(sessionToUpdate);
    }

    public void deleteSession(int id) {
        Session sessionToDelete = sessionRepository.findById(id);

        sessionRepository.delete(sessionToDelete);
    }
}