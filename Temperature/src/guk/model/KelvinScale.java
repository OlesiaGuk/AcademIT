package guk.model;

import static guk.model.UtilityFunctions.twoDecimalPlacesRound;

public class KelvinScale implements Scale {
    @Override
    public double convertToCelsius(double temperature) {
        return twoDecimalPlacesRound(temperature - 273.15);
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return twoDecimalPlacesRound(temperature + 273.15);
    }
}
