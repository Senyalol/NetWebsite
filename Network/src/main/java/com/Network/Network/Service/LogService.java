package com.Network.Network.Service;

import com.Network.Network.DTO.LogsDTO.CreateLogsDTO;
import com.Network.Network.DTO.LogsDTO.ShortLogsInfoDTO;
import com.Network.Network.DTO.LogsDTO.UpdateLogsDTO;
import com.Network.Network.Entites.Log;
import com.Network.Network.Repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class LogService {

    private final LogRepository logRepository;

    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    public List<ShortLogsInfoDTO> getLogs() {
        List<Log> logs = logRepository.findAll();

        return logs.stream()
                .map(log -> {
                    ShortLogsInfoDTO logDTO = new ShortLogsInfoDTO();
                    logDTO.setLogs_id(log.getId());
                    logDTO.setUser_id(log.getUser() != null ? log.getUser().getId() : null);
                    logDTO.setAction(log.getAction());
                    logDTO.setTimestamp(log.getTimestamp());
                    logDTO.setDescription(log.getDescription());

                    return logDTO;
                }).toList();
    }

    public ShortLogsInfoDTO getLogById(int id) {
        Log log = logRepository.findById(id);

        ShortLogsInfoDTO logDTO = new ShortLogsInfoDTO();
        logDTO.setLogs_id(log.getId());
        logDTO.setUser_id(log.getUser() != null ? log.getUser().getId() : null);
        logDTO.setAction(log.getAction());
        logDTO.setTimestamp(log.getTimestamp());
        logDTO.setDescription(log.getDescription());

        return logDTO;
    }

    public void createLog(CreateLogsDTO createLogsDTO) {
        Log log = new Log();
        log.setUser(new com.Network.Network.Entites.User()); // Assuming User is already loaded by ID
        log.getUser().setId(createLogsDTO.getUser_id());
        log.setAction(createLogsDTO.getAction());
        log.setTimestamp(createLogsDTO.getTimestamp());
        log.setDescription(createLogsDTO.getDescription());

        logRepository.save(log);
    }

    public void updateLog(int id, UpdateLogsDTO updateLogsDTO) {
        Log logToUpdate = logRepository.findById(id);

        if (updateLogsDTO.getUser_id() != null) {
            logToUpdate.setUser(new com.Network.Network.Entites.User()); // If necessary, create a new User object
            logToUpdate.getUser().setId(updateLogsDTO.getUser_id());
        }
        if (updateLogsDTO.getAction() != null) {
            logToUpdate.setAction(updateLogsDTO.getAction());
        }
        if (updateLogsDTO.getTimestamp() != null) {
            logToUpdate.setTimestamp(updateLogsDTO.getTimestamp());
        }
        if(updateLogsDTO.getDescription() != null) {
            logToUpdate.setDescription(updateLogsDTO.getDescription());
        }

        logRepository.save(logToUpdate);
    }

    public void deleteLog(int id) {
        Log logToDelete = logRepository.findById(id);

        logRepository.delete(logToDelete);
    }
}