package fer.hr.rassus.lab1.service;

import fer.hr.rassus.lab1.dao.IMeasurement;
import fer.hr.rassus.lab1.dao.ISensor;
import fer.hr.rassus.lab1.domain.Measurement;
import fer.hr.rassus.lab1.dto.MeasurementDTO;
import fer.hr.rassus.lab1.dto.MeasurementIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeasurementService {
    @Autowired
    private IMeasurement measurementRepo;
    @Autowired
    private ISensor sensorRepo;

    public Long addMeasurement(Long id, MeasurementDTO measurementDTO) {
        if (sensorRepo.findById(id).isEmpty())
            return null;
        Measurement measurement = Measurement.builder().
                temperature(measurementDTO.getTemperature()).
                pressure(measurementDTO.getPressure()).
                humidity(measurementDTO.getHumidity()).
                co(measurementDTO.getCo()).
                no2(measurementDTO.getNo2()).
                so2(measurementDTO.getSo2()).
                sensor(sensorRepo.findById(id).get()).
                                    build();
        measurementRepo.save(measurement);
        return measurement.getMeasurementId();

    }
    public MeasurementDTO getMeasurement(Long id) {
        if(measurementRepo.findById(id).isEmpty())
            return null;
        Measurement measurement = measurementRepo.findById(id).get();
        return MeasurementDTO.builder().
                temperature(measurement.getTemperature()).
                pressure(measurement.getPressure()).
                humidity(measurement.getHumidity()).
                co(measurement.getCo()).
                no2(measurement.getNo2()).
                so2(measurement.getSo2()).
                build();

    }

    public List<MeasurementIdDTO> getAllMeasurements(Long id) {
        return measurementRepo.findAllBySensor_SensorId(id).stream().map(MeasurementIdDTO::new).toList();

    }
}

