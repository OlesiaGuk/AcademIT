package guk.model;

public class CelsiusScale implements Scale {
    private double temperature;

    public CelsiusScale(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public double convertToCelsius() {
        return temperature;
    }

    @Override
    public double convertFromCelsius() {
        return temperature;
    }
}