package guk.model;

import static guk.model.UtilityFunctions.twoDecimalPlacesRound;

public class ConversionModel {
    private double temperature;

    public ConversionModel() {
    }

    public ConversionModel(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getConversion(String inputScale, String outputScale) {
        if (inputScale.equals("Градусы Цельсия")) {
            if (outputScale.equals("Градусы Фаренгейта")) {
                return celsiusToFahrenheit();
            } else if (outputScale.equals("Кельвины")) {
                return celsiusToKelvin();
            }
            return temperature;
        } else if (inputScale.equals("Градусы Фаренгейта")) {
            if (outputScale.equals("Градусы Цельсия")) {
                return fahrenheitToCelsius();
            } else if (outputScale.equals("Кельвины")) {
                return fahrenheitToKelvin();
            }
            return temperature;
        }
        if (outputScale.equals("Градусы Цельсия")) {
            return kelvinToCelsius();
        } else if (outputScale.equals("Градусы Фаренгейта")) {
            return kelvinToFahrenheit();
        }
        return temperature;
    }

    private double celsiusToKelvin() {
        return twoDecimalPlacesRound(temperature + 273.15);
    }

    private double kelvinToCelsius() {
        return twoDecimalPlacesRound(temperature - 273.15);
    }

    private double celsiusToFahrenheit() {
        return twoDecimalPlacesRound(temperature * 9 / 5 + 32);
    }

    private double fahrenheitToCelsius() {
        return twoDecimalPlacesRound((temperature - 32) * 5 / 9);
    }

    private double fahrenheitToKelvin() {
        return twoDecimalPlacesRound((temperature - 32) * 5 / 9 + 273.15);
    }

    private double kelvinToFahrenheit() {
        return twoDecimalPlacesRound((temperature - 273.15) * 9 / 5 + 32);
    }

    public static String[] getTemperatureScales() {
        return new String[]{"Градусы Цельсия", "Градусы Фаренгейта", "Кельвины"};
    }
}
