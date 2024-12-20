package com.Network.Network.Controller;

import com.Network.Network.Service.NetworkService;
import com.Network.Network.DTO.NetworkDTO.CreateNetworkDTO;
import com.Network.Network.DTO.NetworkDTO.ShortNetworkInfoDTO;
import com.Network.Network.DTO.NetworkDTO.UpdateNetworkDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/networks")
public class NetworkController {

    private final NetworkService networkService;

    @Autowired
    public NetworkController(NetworkService networkService) {
        this.networkService = networkService;
    }

    @GetMapping
    public List<ShortNetworkInfoDTO> getAllNetworks() {
        return networkService.getNetworks();
    }

    @GetMapping("/{id}")
    public ShortNetworkInfoDTO getNetworkById(@PathVariable int id) {
        return networkService.getNetworkById(id);
    }

    @PostMapping
    public void createNetwork(@RequestBody CreateNetworkDTO createNetworkDTO) {
        networkService.createNetwork(createNetworkDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateNetwork(@PathVariable("id") int id, @Valid @RequestBody UpdateNetworkDTO updateNetworkDTO) {
        try {
            networkService.updateNetwork(id, updateNetworkDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNetwork(@PathVariable("id") int id) {
        try {
            networkService.deleteNetwork(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}