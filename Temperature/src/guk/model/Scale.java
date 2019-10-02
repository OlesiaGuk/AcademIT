package guk.model;

public interface Scale {
    String getScaleName();

    double convertToCelsius(double temperature);

    double convertFromCelsius(double temperature);
}
