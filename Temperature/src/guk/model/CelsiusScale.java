package guk.model;

public class CelsiusScale implements Scale {
    @Override
    public String getScaleName() {
        return "Градусы Цельсия";
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }
}