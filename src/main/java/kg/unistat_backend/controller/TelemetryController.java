package kg.unistat_backend.controller;

import kg.unistat_backend.model.DeviceCommand;
import kg.unistat_backend.model.Telemetry;
import kg.unistat_backend.service.TelemetryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class TelemetryController {

    private final TelemetryService service;

    public TelemetryController(TelemetryService service) {
        this.service = service;
    }

    /* endpoint for website frontend to get current telemetry */
    @GetMapping("/telemetry")
    public Telemetry getTelemetry() {
        return this.service.getCurrentTelemetry();
    }

    /* endpoint for esp32 to post new sensor readings, i added try catch to handle validation exception */
    @PostMapping("/telemetry")
    public ResponseEntity<?> addTelemetry(@RequestBody Telemetry data) {
        try {
            Telemetry saved = this.service.saveTelemetry(data);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /* endpoint for history data used in charts */
    @GetMapping("/telemetry/history")
    public ArrayList<Telemetry> getHistory() {
        return this.service.getHistory();
    }

    /* endpoint for controlling relays from web UI */
    @PostMapping("/devices/control")
    public ResponseEntity<?> controlDevices(@RequestBody DeviceCommand command) {
        try {
            DeviceCommand updated = this.service.updateDeviceState(command);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /* endpoint for wemos microcontroller to poll relay state */
    @GetMapping("/devices/state")
    public DeviceCommand getDeviceState() {
        return this.service.getCurrentState();
    }

    /* endpoint to check average temperature calculated from history */
    @GetMapping("/telemetry/avg-temp")
    public double getAverageTemp() {
        return this.service.getAverageTemperature();
    }
}
