package com.Network.Network.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Network.Network.Entites.Log;
@Repository
public interface LogRepository extends JpaRepository<Log, Integer> {
    Log findById(int id);
}
