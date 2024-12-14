package fer.hr.rassus.lab1.dto;

import fer.hr.rassus.lab1.domain.Sensor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SensorDTO {
    private Double latitude;
    private Double longitude;
    private String ip;
    private Integer port;

    public SensorDTO(Sensor nearestSensor) {
        this.latitude = nearestSensor.getLatitude();
        this.longitude = nearestSensor.getLongitude();
        this.ip = nearestSensor.getIp();
        this.port = nearestSensor.getPort();
    }

    public Sensor toSensor() {
        return Sensor.builder()
                .latitude(latitude)
                .longitude(longitude)
                .ip(ip)
                .port(port)
                .build();
    }
}
