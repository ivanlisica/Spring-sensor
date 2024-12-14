package fer.hr.rassus.lab1.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SensorId", nullable = false)
    private Long sensorId;
    @Column(name = "latitude", nullable = false, columnDefinition = "DECIMAL(10,8)")
    private Double latitude;
    @Column(name = "longitude", nullable = false, columnDefinition = "DECIMAL(10,8)")
    private Double longitude;
    @Column(name = "ip", nullable = false)
    private String ip;
    @Column(name = "port", nullable = false)
    private Integer port;
    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Measurement> measurementList;



}
