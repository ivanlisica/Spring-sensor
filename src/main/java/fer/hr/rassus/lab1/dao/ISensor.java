package fer.hr.rassus.lab1.dao;

import fer.hr.rassus.lab1.domain.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISensor extends JpaRepository<Sensor, Long> {
    Optional<Sensor> findByLongitudeAndLatitude(Double longitude, Double latitude);
}
