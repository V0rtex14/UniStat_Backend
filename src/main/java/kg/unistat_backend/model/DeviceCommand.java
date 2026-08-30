package kg.unistat_backend.model;

public class DeviceCommand {

    private Boolean ventilation;
    private Boolean sockets;
    private Integer lightGroups;
    private Boolean manualOverride;

    public DeviceCommand() {
    }

    /* constructor for relay commands, i added simple check so light groups quantity can not exceed available 4 relay groups */
    public DeviceCommand(Boolean ventilation, Boolean sockets, Integer lightGroups, Boolean manualOverride) {
        if (lightGroups != null && (lightGroups < 0 || lightGroups > 4)) {
            throw new IllegalArgumentException("Light groups can not be less than 0 or more than 4");
        }
        this.ventilation = ventilation;
        this.sockets = sockets;
        this.lightGroups = lightGroups;
        this.manualOverride = manualOverride;
    }

    public Boolean getVentilation() {
        return this.ventilation;
    }

    public void setVentilation(Boolean ventilation) {
        this.ventilation = ventilation;
    }

    public Boolean getSockets() {
        return this.sockets;
    }

    public void setSockets(Boolean sockets) {
        this.sockets = sockets;
    }

    public Integer getLightGroups() {
        return this.lightGroups;
    }

    public void setLightGroups(Integer lightGroups) {
        if (lightGroups != null && (lightGroups < 0 || lightGroups > 4)) {
            throw new IllegalArgumentException("Light groups quantity is not valid");
        }
        this.lightGroups = lightGroups;
    }

    public Boolean getManualOverride() {
        return this.manualOverride;
    }

    public void setManualOverride(Boolean manualOverride) {
        this.manualOverride = manualOverride;
    }
}
