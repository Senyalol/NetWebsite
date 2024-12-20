package com.Network.Network.Controller;

import com.Network.Network.Service.LogService;
import com.Network.Network.DTO.LogsDTO.CreateLogsDTO;
import com.Network.Network.DTO.LogsDTO.ShortLogsInfoDTO;
import com.Network.Network.DTO.LogsDTO.UpdateLogsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/logs")
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping
    public List<ShortLogsInfoDTO> getAllLogs() {
        return logService.getLogs();
    }

    @GetMapping("/{id}")
    public ShortLogsInfoDTO getLogById(@PathVariable int id) {
        return logService.getLogById(id);
    }

    @PostMapping
    public void createLog(@RequestBody CreateLogsDTO createLogsDTO) {
        logService.createLog(createLogsDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateLog(@PathVariable("id") int id, @Valid @RequestBody UpdateLogsDTO updateLogsDTO) {
        try {
            logService.updateLog(id, updateLogsDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLog(@PathVariable("id") int id) {
        try {
            logService.deleteLog(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}