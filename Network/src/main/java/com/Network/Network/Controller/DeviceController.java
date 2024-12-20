package com.Network.Network.Controller;

import com.Network.Network.Service.DeviceService;
import com.Network.Network.DTO.DeviceDTO.CreateDeviceDTO;
import com.Network.Network.DTO.DeviceDTO.ShortDeviceInfoDTO;
import com.Network.Network.DTO.DeviceDTO.UpdateDeviceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/devices")
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public List<ShortDeviceInfoDTO> getAllDevices() {
        return deviceService.getDevices();
    }

    @GetMapping("/{id}")
    public ShortDeviceInfoDTO getDeviceById(@PathVariable int id) {
        return deviceService.getDeviceById(id);
    }

    @PostMapping
    public ResponseEntity<String> createDevice(@RequestBody CreateDeviceDTO createDeviceDTO) {
        try {
            deviceService.createDevice(createDeviceDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body("Устройство успешно создано.");
        } catch (IllegalArgumentException e) {
            // Логирование ошибки
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ошибка: " + e.getMessage());
        } catch (Exception e) {
            // Логирование ошибки
            e.printStackTrace(); // Для отладки
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при создании устройства.");
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateDevice(@PathVariable("id") int id, @Valid @RequestBody UpdateDeviceDTO updateDeviceDTO) {
        try {
            deviceService.updateDevice(id, updateDeviceDTO);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable("id") int id) {
        try {
            deviceService.deleteDevice(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            // Log the error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}