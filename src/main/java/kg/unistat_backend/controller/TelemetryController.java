package kg.unistat_backend.controller;

import kg.unistat_backend.model.DeviceCommand;
import kg.unistat_backend.model.Telemetry;
import kg.unistat_backend.service.TelemetryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TelemetryController {

    private final TelemetryService service;

    public TelemetryController(TelemetryService service) {
        this.service = service;
    }

    // endpoint for frontend to get latest room data
    @GetMapping("/telemetry")
    public Telemetry getTelemetry() {
        return service.getCurrentTelemetry();
    }

    // endpoint for esp32 to post new sensor readings
    @PostMapping("/telemetry")
    public Telemetry addTelemetry(@RequestBody Telemetry data) {
        return service.saveTelemetry(data);
    }

    // endpoint for charts with history data
    @GetMapping("/telemetry/history")
    public List<Telemetry> getHistory() {
        return service.getHistory();
    }

    // endpoint for controlling relays from frontend
    @PostMapping("/devices/control")
    public DeviceCommand controlDevices(@RequestBody DeviceCommand command) {
        return service.updateDeviceState(command);
    }

    // endpoint for wemos d1 mini to read relay command state
    @GetMapping("/devices/state")
    public DeviceCommand getDeviceState() {
        return service.getCurrentState();
    }
}
