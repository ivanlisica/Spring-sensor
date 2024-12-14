package fer.hr.rassus.lab1.dto;

import fer.hr.rassus.lab1.domain.Measurement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementIdDTO {
    private Long measurementId;
    private Double temperature;
    private Double pressure;
    private Double humidity;
    private Double co;
    private Double no2;
    private Double so2;

    public MeasurementIdDTO(Measurement measurement) {
        this.measurementId = measurement.getMeasurementId();
        this.temperature = measurement.getTemperature();
        this.pressure = measurement.getPressure();
        this.humidity = measurement.getHumidity();
        this.co = measurement.getCo();
        this.so2 = measurement.getSo2();
    }
}
