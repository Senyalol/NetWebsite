package com.Network.Network.Controller;

import com.Network.Network.Service.SessionService;
import com.Network.Network.DTO.SessionsDTO.CreateSessionsDTO;
import com.Network.Network.DTO.SessionsDTO.ShortSessionInfoDTO;
import com.Network.Network.DTO.SessionsDTO.UpdateSessionsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/sessions")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping
    public List<ShortSessionInfoDTO> getAllSessions() {
        return sessionService.getSessions();
    }

    @GetMapping("/{id}")
    public ShortSessionInfoDTO getSessionById(@PathVariable int id) {
        return sessionService.getSessionById(id);
    }

    @PostMapping
    public void createSession(@RequestBody CreateSessionsDTO createSessionsDTO) {
        sessionService.createSession(createSessionsDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateSession(@PathVariable("id") int id, @Valid @RequestBody UpdateSessionsDTO updateSessionsDTO) {
        try {
            sessionService.updateSession(id, updateSessionsDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable("id") int id) {
        try {
            sessionService.deleteSession(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}