package guk.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConversionModel {
    private double temperature;
    private List<Scale> scalesList;

    public ConversionModel() {
        createScalesList();
    }

    public ConversionModel(double temperature) {
        this.temperature = temperature;
        createScalesList();
    }

    private void createScalesList() {
        scalesList = new ArrayList<>();
        scalesList.add(new CelsiusScale());
        scalesList.add(new FahrenheitScale());
        scalesList.add(new KelvinScale());
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public List<Scale> getScalesList() {
        return scalesList;
    }

    private Scale findScale(String scaleName) {
        for (Scale scale : scalesList) {
            if (scale.getScaleName().equals(scaleName)) {
                return scale;
            }
        }
        return null;
    }

    public double convert(String inputScale, String outputScale) {
        double interimTemp = Objects.requireNonNull(findScale(inputScale)).convertToCelsius(temperature);

        return Objects.requireNonNull(findScale(outputScale)).convertFromCelsius(interimTemp);
    }

}