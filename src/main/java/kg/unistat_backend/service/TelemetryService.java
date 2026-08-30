package kg.unistat_backend.service;

import kg.unistat_backend.model.DeviceCommand;
import kg.unistat_backend.model.Telemetry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TelemetryService {

    /* memory storage for telemetry records, i decided to use ArrayList because it is simple to work with */
    private final ArrayList<Telemetry> history = new ArrayList<>();
    private Telemetry currentTelemetry;
    private DeviceCommand currentState;

    public TelemetryService() {
        // default initial state when server starts
        this.currentTelemetry = new Telemetry(22.4, 45.0, 580, 12, 350, 1.25);
        this.currentState = new DeviceCommand(true, true, 3, false);
        this.history.add(this.currentTelemetry);
    }

    /* save telemetry received from esp32, if data values aren't correct exception will be thrown */
    public Telemetry saveTelemetry(Telemetry data) {
        if (data == null) {
            throw new IllegalArgumentException("Telemetry data can not be null");
        }
        if (data.getTemp() < -50.0 || data.getTemp() > 80.0) {
            throw new IllegalArgumentException("Temperature value can not be out of range");
        }
        if (data.getCo2() < 0 || data.getCo2() > 10000) {
            throw new IllegalArgumentException("CO2 value is not valid");
        }

        if (data.getTs() == 0) {
            data.setTs(System.currentTimeMillis());
        }

        this.currentTelemetry = data;
        this.history.add(data);

        /* i keep maximum 100 records in history list so memory will not grow too much */
        if (this.history.size() > 100) {
            this.history.remove(0);
        }

        return this.currentTelemetry;
    }

    /* return current telemetry object */
    public Telemetry getCurrentTelemetry() {
        return this.currentTelemetry;
    }

    /* return history list for analytics charts */
    public ArrayList<Telemetry> getHistory() {
        return this.history;
    }

    /* update relay states when user changes controls from website */
    public DeviceCommand updateDeviceState(DeviceCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("Command can not be null");
        }

        if (command.getVentilation() != null) {
            this.currentState.setVentilation(command.getVentilation());
        }
        if (command.getSockets() != null) {
            this.currentState.setSockets(command.getSockets());
        }
        if (command.getLightGroups() != null) {
            if (command.getLightGroups() < 0 || command.getLightGroups() > 4) {
                throw new IllegalArgumentException("Light groups quantity can not be less than 0 or more than 4");
            }
            this.currentState.setLightGroups(command.getLightGroups());
        }
        if (command.getManualOverride() != null) {
            this.currentState.setManualOverride(command.getManualOverride());
        }

        return this.currentState;
    }

    /* return current device states for wemos microcontroller */
    public DeviceCommand getCurrentState() {
        return this.currentState;
    }

    /* simple helper method to calculate average temperature from recorded history */
    public double getAverageTemperature() {
        if (this.history.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (int i = 0; i < this.history.size(); i++) {
            sum += this.history.get(i).getTemp();
        }
        return sum / this.history.size();
    }
}
