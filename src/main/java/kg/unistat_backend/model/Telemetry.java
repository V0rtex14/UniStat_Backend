package kg.unistat_backend.model;

public class Telemetry {

    private double temp;
    private double humidity;
    private int co2;
    private int pm25;
    private int lux;
    private double powerKw;
    private long ts;

    public Telemetry() {
        this.ts = System.currentTimeMillis();
    }

    public Telemetry(double temp, double humidity, int co2, int pm25, int lux, double powerKw) {
        this.temp = temp;
        this.humidity = humidity;
        this.co2 = co2;
        this.pm25 = pm25;
        this.lux = lux;
        this.powerKw = powerKw;
        this.ts = System.currentTimeMillis();
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public int getLux() {
        return lux;
    }

    public void setLux(int lux) {
        this.lux = lux;
    }

    public double getPowerKw() {
        return powerKw;
    }

    public void setPowerKw(double powerKw) {
        this.powerKw = powerKw;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }
}
