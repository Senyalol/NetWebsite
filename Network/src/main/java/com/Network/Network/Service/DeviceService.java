package com.Network.Network.Service;

import com.Network.Network.DTO.DeviceDTO.CreateDeviceDTO;
import com.Network.Network.DTO.DeviceDTO.ShortDeviceInfoDTO;
import com.Network.Network.DTO.DeviceDTO.UpdateDeviceDTO;
import com.Network.Network.Entites.Device;
import com.Network.Network.Entites.User; // Импортируем User
import com.Network.Network.Repository.DeviceRepository;
import com.Network.Network.Repository.UserRepository; // Импортируем UserRepository
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class DeviceService {

    private final DeviceRepository deviceRepository;
    private final UserRepository userRepository; // Добавляем UserRepository

    @Autowired
    public DeviceService(DeviceRepository deviceRepository, UserRepository userRepository) {
        this.deviceRepository = deviceRepository;
        this.userRepository = userRepository; // Инициализируем UserRepository
    }

    public List<ShortDeviceInfoDTO> getDevices() {
        List<Device> devices = deviceRepository.findAll();

        return devices.stream()
                .map(device -> {
                    ShortDeviceInfoDTO deviceDTO = new ShortDeviceInfoDTO();
                    deviceDTO.setDevice_id(device.getId());
                    deviceDTO.setUser_id(device.getUser() != null ? device.getUser().getId() : null);
                    deviceDTO.setDeviceName(device.getDeviceName());
                    deviceDTO.setDeviceType(device.getDeviceType());
                    deviceDTO.setIpAddress(device.getIpAddress());
                    deviceDTO.setMacAddress(device.getMacAddress());
                    deviceDTO.setCreatedAt(device.getCreatedAt());

                    return deviceDTO;
                }).toList();
    }

    public ShortDeviceInfoDTO getDeviceById(int id) {
        Device device = deviceRepository.findById(id);

        ShortDeviceInfoDTO deviceDTO = new ShortDeviceInfoDTO();
        deviceDTO.setDevice_id(device.getId());
        deviceDTO.setUser_id(device.getUser() != null ? device.getUser().getId() : null);
        deviceDTO.setDeviceName(device.getDeviceName());
        deviceDTO.setDeviceType(device.getDeviceType());
        deviceDTO.setIpAddress(device.getIpAddress());
        deviceDTO.setMacAddress(device.getMacAddress());
        deviceDTO.setCreatedAt(device.getCreatedAt());

        return deviceDTO;
    }

    public void createDevice(CreateDeviceDTO createDeviceDTO) {
        if (createDeviceDTO.getIpAddress() == null || createDeviceDTO.getIpAddress().isEmpty()) {
            throw new IllegalArgumentException("IP-адрес не может быть пустым.");
        }

        Device device = new Device();
        User user = userRepository.findById(createDeviceDTO.getUser_id())
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        device.setUser(user);

        device.setDeviceName(createDeviceDTO.getDeviceName());
        device.setDeviceType(createDeviceDTO.getDeviceType());
        device.setIpAddress(createDeviceDTO.getIpAddress());
        device.setMacAddress(createDeviceDTO.getMacAddress());
        device.setCreatedAt(createDeviceDTO.getCreatedAt());

        deviceRepository.save(device);
    }

    public void updateDevice(int id, UpdateDeviceDTO updateDeviceDTO) {
        Device deviceToUpdate = deviceRepository.findById(id); // Обработка ошибки

        if (updateDeviceDTO.getUser_id() != null) {
            User user = userRepository.findById(updateDeviceDTO.getUser_id())
                    .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден")); // Обработка ошибки
            deviceToUpdate.setUser(user);
        }
        if (updateDeviceDTO.getDeviceName() != null) {
            deviceToUpdate.setDeviceName(updateDeviceDTO.getDeviceName());
        }
        if (updateDeviceDTO.getDeviceType() != null) {
            deviceToUpdate.setDeviceType(updateDeviceDTO.getDeviceType());
        }
        if (updateDeviceDTO.getIpAddress() != null) {
            deviceToUpdate.setIpAddress(updateDeviceDTO.getIpAddress());
        }
        if (updateDeviceDTO.getMacAddress() != null) {
            deviceToUpdate.setMacAddress(updateDeviceDTO.getMacAddress());
        }
        if (updateDeviceDTO.getCreatedAt() != null) {
            deviceToUpdate.setCreatedAt(updateDeviceDTO.getCreatedAt());
        }

        deviceRepository.save(deviceToUpdate);
    }

    public void deleteDevice(int id) {
        Device deviceToDelete = deviceRepository.findById(id); // Обработка ошибки

        deviceRepository.delete(deviceToDelete);
    }
}