package com.Network.Network.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.Network.Network.Entites.Session;
@Repository
public interface SessionRepository extends JpaRepository<Session, Integer> {
    Session findById(int id);
}
