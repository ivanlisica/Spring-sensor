package fer.hr.rassus.lab1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MeasurementDTO {
       private Double temperature;
       private Double pressure;
       private Double humidity;
       private Double co;
       private Double no2;
       private Double so2;
}
