package fer.hr.rassus.lab1.dao;

import fer.hr.rassus.lab1.domain.Measurement;
import fer.hr.rassus.lab1.dto.MeasurementIdDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMeasurement extends JpaRepository<Measurement, Long>{
     List<Measurement> findAllBySensor_SensorId(Long sensorId);
}
