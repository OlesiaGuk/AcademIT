package guk.model;

import static guk.model.UtilityFunctions.twoDecimalPlacesRound;

public class FahrenheitScale implements Scale {
    @Override
    public double convertToCelsius(double temperature) {
        return twoDecimalPlacesRound((temperature - 32) * 5 / 9);
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return twoDecimalPlacesRound(temperature * 9 / 5 + 32);
    }
}
