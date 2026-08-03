package kg.unistat_backend.service;

import kg.unistat_backend.model.DeviceCommand;
import kg.unistat_backend.model.Telemetry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TelemetryService {

    // simple memory storage for telemetry data
    private final List<Telemetry> history = new ArrayList<>();
    private Telemetry currentTelemetry;
    private DeviceCommand currentState;

    public TelemetryService() {
        // default initial state for system
        this.currentTelemetry = new Telemetry(22.4, 45.0, 580, 12, 350, 1.25);
        this.currentState = new DeviceCommand(true, true, 3, false);
        this.history.add(this.currentTelemetry);
    }

    // save telemetry received from esp32 microcontroller
    public Telemetry saveTelemetry(Telemetry data) {
        if (data.getTs() == 0) {
            data.setTs(System.currentTimeMillis());
        }
        this.currentTelemetry = data;
        this.history.add(data);

        // keep maximum 100 records in history list
        if (this.history.size() > 100) {
            this.history.remove(0);
        }
        return this.currentTelemetry;
    }

    // return latest telemetry reading
    public Telemetry getCurrentTelemetry() {
        return this.currentTelemetry;
    }

    // return history list for charts in frontend
    public List<Telemetry> getHistory() {
        return this.history;
    }

    // update state of relays when user clicks buttons on frontend
    public DeviceCommand updateDeviceState(DeviceCommand command) {
        if (command.getVentilation() != null) {
            this.currentState.setVentilation(command.getVentilation());
        }
        if (command.getSockets() != null) {
            this.currentState.setSockets(command.getSockets());
        }
        if (command.getLightGroups() != null) {
            this.currentState.setLightGroups(command.getLightGroups());
        }
        if (command.getManualOverride() != null) {
            this.currentState.setManualOverride(command.getManualOverride());
        }
        return this.currentState;
    }

    // return current state of devices for wemos microcontroller
    public DeviceCommand getCurrentState() {
        return this.currentState;
    }
}
