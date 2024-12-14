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
public class SensorIdDTO {
    private Long sensorId;
    private Double latitude;
    private Double longitude;
    private String ip;
    private Integer port;

    public SensorIdDTO(Sensor sensor) {
        this.sensorId = sensor.getSensorId();
        this.latitude = sensor.getLatitude();
        this.longitude = sensor.getLongitude();
        this.ip = sensor.getIp();
        this.port = sensor.getPort();
    }

    public Sensor toSensor(SensorIdDTO sensorIdDTO) {
        return Sensor.builder()
                .sensorId(sensorIdDTO.getSensorId())
                .latitude(sensorIdDTO.getLatitude())
                .longitude(sensorIdDTO.getLongitude())
                .ip(sensorIdDTO.getIp())
                .port(sensorIdDTO.getPort())
                .build();

    }

}
