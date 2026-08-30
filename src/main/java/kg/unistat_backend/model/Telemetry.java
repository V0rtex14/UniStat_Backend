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

    /* constructor that creates telemetry object, i added some validation checks if incoming sensor data isn't correct */
    public Telemetry(double temp, double humidity, int co2, int pm25, int lux, double powerKw) {
        if (temp < -50.0 || temp > 80.0) {
            throw new IllegalArgumentException("Temperature value can not be out of valid range");
        }
        if (humidity < 0.0 || humidity > 100.0) {
            throw new IllegalArgumentException("Humidity can not be less than zero or more than 100 percent");
        }
        if (co2 < 0 || co2 > 10000) {
            throw new IllegalArgumentException("CO2 value can not be negative or unreasonably high");
        }
        if (pm25 < 0) {
            throw new IllegalArgumentException("PM2.5 value can not be less than zero");
        }
        if (lux < 0) {
            throw new IllegalArgumentException("Lux value can not be negative");
        }
        if (powerKw < 0.0) {
            throw new IllegalArgumentException("Power consumption can not be negative");
        }

        this.temp = temp;
        this.humidity = humidity;
        this.co2 = co2;
        this.pm25 = pm25;
        this.lux = lux;
        this.powerKw = powerKw;
        this.ts = System.currentTimeMillis();
    }

    public double getTemp() {
        return this.temp;
    }

    public void setTemp(double temp) {
        if (temp < -50.0 || temp > 80.0) {
            throw new IllegalArgumentException("Temperature out of range");
        }
        this.temp = temp;
    }

    public double getHumidity() {
        return this.humidity;
    }

    public void setHumidity(double humidity) {
        if (humidity < 0.0 || humidity > 100.0) {
            throw new IllegalArgumentException("Humidity out of range");
        }
        this.humidity = humidity;
    }

    public int getCo2() {
        return this.co2;
    }

    public void setCo2(int co2) {
        if (co2 < 0 || co2 > 10000) {
            throw new IllegalArgumentException("CO2 out of range");
        }
        this.co2 = co2;
    }

    public int getPm25() {
        return this.pm25;
    }

    public void setPm25(int pm25) {
        if (pm25 < 0) {
            throw new IllegalArgumentException("PM2.5 can not be negative");
        }
        this.pm25 = pm25;
    }

    public int getLux() {
        return this.lux;
    }

    public void setLux(int lux) {
        if (lux < 0) {
            throw new IllegalArgumentException("Lux can not be negative");
        }
        this.lux = lux;
    }

    public double getPowerKw() {
        return this.powerKw;
    }

    public void setPowerKw(double powerKw) {
        if (powerKw < 0.0) {
            throw new IllegalArgumentException("Power can not be negative");
        }
        this.powerKw = powerKw;
    }

    public long getTs() {
        return this.ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }
}
