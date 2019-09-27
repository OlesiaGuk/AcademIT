package guk.model;

public interface Scale {
    double convertToCelsius(double temperature);

    double convertFromCelsius(double temperature);
}
