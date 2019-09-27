package guk.model;

import static guk.model.UtilityFunctions.twoDecimalPlacesRound;

public class KelvinScale implements Scale {
    private double temperature;

    public KelvinScale(double temperature) {
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
        return twoDecimalPlacesRound(temperature - 273.15);
    }

    @Override
    public double convertFromCelsius() {
        return twoDecimalPlacesRound(temperature + 273.15);
    }
}
