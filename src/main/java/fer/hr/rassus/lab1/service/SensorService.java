package fer.hr.rassus.lab1.service;

import fer.hr.rassus.lab1.dao.ISensor;
import fer.hr.rassus.lab1.domain.Sensor;
import fer.hr.rassus.lab1.dto.SensorDTO;
import fer.hr.rassus.lab1.dto.SensorIdDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SensorService {
    @Autowired
    private ISensor sensorRepo;

    public boolean isSensorExist(SensorDTO sensorDTO) {
        return sensorRepo.findByLongitudeAndLatitude(sensorDTO.getLongitude(), sensorDTO.getLatitude()).isPresent();
    }

    public Long register(SensorDTO sensorDTO) {
        if (isSensorExist(sensorDTO))
            return sensorRepo.findByLongitudeAndLatitude(sensorDTO.getLongitude(), sensorDTO.getLatitude()).get().getSensorId();
        Sensor sensor = sensorDTO.toSensor();
        sensorRepo.save(sensor);
        return sensor.getSensorId();
    }

    public ResponseEntity getNearestNeighbor(Long id) {
       Sensor sensor = sensorRepo.findById(id).orElse(null);
         if(sensor == null)
              return ResponseEntity.badRequest().body("Sensor not found");

         var allSensor = sensorRepo.findAll();
         allSensor.remove(sensor);
            Sensor nearestSensor = null;
            double minDistance = Double.MAX_VALUE;
            Haversine haversine = new Haversine();
            for (Sensor s : allSensor) {
                double distance = haversine.distance(sensor.getLatitude(), sensor.getLongitude(), s.getLatitude(), s.getLongitude());
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestSensor = s;
                }
            }
            if (nearestSensor == null)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No neighbors found");

            SensorDTO sensorDTO = new SensorDTO(nearestSensor);
            return ResponseEntity.ok(sensorDTO);
    }

    public SensorDTO getSensor(Long id) {
        if (sensorRepo.findById(id).isEmpty())
            return null;
        Sensor sensor = sensorRepo.findById(id).get();
        return new SensorDTO(sensor);

    }
    public List<SensorIdDTO> getAllSensors() {
        return sensorRepo.findAll().stream().map(SensorIdDTO::new).toList();
    }


    private static class Haversine {
        private static final int R = 6371; // Radius of the earth

        public double distance(double lat1, double lon1, double lat2, double lon2) {
            double latDistance = Math.toRadians(lat2 - lat1);
            double lonDistance = Math.toRadians(lon2 - lon1);

            double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                    * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
            return R * c;
        }
    }
}
