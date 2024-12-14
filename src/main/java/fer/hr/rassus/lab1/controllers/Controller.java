package fer.hr.rassus.lab1.controllers;

import fer.hr.rassus.lab1.dto.MeasurementDTO;
import fer.hr.rassus.lab1.dto.MeasurementIdDTO;
import fer.hr.rassus.lab1.dto.SensorDTO;
import fer.hr.rassus.lab1.service.MeasurementService;
import fer.hr.rassus.lab1.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {
    @Autowired
    private final SensorService sensorService;
    @Autowired
    private final MeasurementService measurementService;

    @PostMapping
    public ResponseEntity register(@RequestBody SensorDTO sensorDTO) {
        try {
            Long id = sensorService.register(sensorDTO);
            return ResponseEntity
                    .created(URI.create("/api/sensor/" + id))
                    .body(id);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();

        }

    }
    @GetMapping("/sensor/{id}")
    public ResponseEntity getSensor(@PathVariable Long id) {
        try {
            SensorDTO sensorDTO = sensorService.getSensor(id);
            if (sensorDTO == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(sensorDTO);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/neighbor/{id}")
    public ResponseEntity getNearestNeighbor(@PathVariable Long id) {
        return sensorService.getNearestNeighbor(id);
    }

    @PostMapping("/sensor/{id}/measurement")
    public ResponseEntity addMeasurement(@PathVariable Long id, @RequestBody MeasurementDTO measurementDTO) {
        Long measurementId = measurementService.addMeasurement(id, measurementDTO);
        if (measurementId == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.created(URI.create("/api/sensor/measurement/" + measurementId)).body(measurementId);
    }
    @GetMapping("/sensor/measurement/{measurementId}")
    public ResponseEntity getMeasurement(@PathVariable Long measurementId) {
        if (measurementService.getMeasurement(measurementId) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(measurementService.getMeasurement(measurementId));
    }

    @GetMapping("/sensor/all")
    public ResponseEntity getAllSensors() {
        return ResponseEntity.ok(sensorService.getAllSensors());
    }

    @GetMapping("/sensor/{id}/measurement/all")
    public ResponseEntity getAllMeasurements(@PathVariable Long id) {
        if (sensorService.getSensor(id) == null) {
            return ResponseEntity.noContent().build();
        }
        List<MeasurementIdDTO> measurementIdDTOList = measurementService.getAllMeasurements(id);
        if (measurementIdDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(measurementIdDTOList);
    }





}
