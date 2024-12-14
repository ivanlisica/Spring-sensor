package fer.hr.rassus.lab1.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Measurement")
public class Measurement {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "MeasurementId", nullable = false)
    private Long measurementId;
    @Column(name = "temperature", nullable = false)
    private Double temperature;
    @Column(name = "pressure", nullable = false)
    private Double pressure;
    @Column(name = "humidity", nullable = false)
    private Double humidity;
    @Column(name = "co", nullable = false)
    private Double co;
    @Column(name = "no2", nullable = false)
    private Double no2;
    @Column(name = "so2", nullable = false)
    private Double so2;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SensorId")
    private Sensor sensor;


}
