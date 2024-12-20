package com.Network.Network.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Network.Network.Entites.Device;
@Repository
public interface DeviceRepository extends JpaRepository<Device, Integer> {
    Device findById(int id);
}
