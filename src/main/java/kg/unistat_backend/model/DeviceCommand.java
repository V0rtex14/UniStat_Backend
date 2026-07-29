package kg.unistat_backend.model;

public class DeviceCommand {

    private Boolean ventilation;
    private Boolean sockets;
    private Integer lightGroups;
    private Boolean manualOverride;

    public DeviceCommand() {
    }

    public DeviceCommand(Boolean ventilation, Boolean sockets, Integer lightGroups, Boolean manualOverride) {
        this.ventilation = ventilation;
        this.sockets = sockets;
        this.lightGroups = lightGroups;
        this.manualOverride = manualOverride;
    }

    public Boolean getVentilation() {
        return ventilation;
    }

    public void setVentilation(Boolean ventilation) {
        this.ventilation = ventilation;
    }

    public Boolean getSockets() {
        return sockets;
    }

    public void setSockets(Boolean sockets) {
        this.sockets = sockets;
    }

    public Integer getLightGroups() {
        return lightGroups;
    }

    public void setLightGroups(Integer lightGroups) {
        this.lightGroups = lightGroups;
    }

    public Boolean getManualOverride() {
        return manualOverride;
    }

    public void setManualOverride(Boolean manualOverride) {
        this.manualOverride = manualOverride;
    }
}
