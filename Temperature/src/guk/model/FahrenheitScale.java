package guk.model;

import static guk.model.UtilityFunctions.twoDecimalPlacesRound;

public class FahrenheitScale implements Scale {
    private double temperature;

    public FahrenheitScale() {
    }

    public FahrenheitScale(double temperature) {
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
        return twoDecimalPlacesRound((temperature - 32) * 5 / 9);
    }

    @Override
    public double convertFromCelsius() {
        return twoDecimalPlacesRound(temperature * 9 / 5 + 32);
    }
}
