package com.Network.Network.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Network.Network.Entites.Network;
@Repository
public interface NetworkRepository extends JpaRepository<Network, Integer> {
    Network findById(int id);
}
