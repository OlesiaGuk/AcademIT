package guk.controller;

import guk.model.ConversionModel;
import guk.view.ConversionView;

public class ConversionController implements ControllerInterface {
    private ConversionModel model;
    private ConversionView view;

    public ConversionController(ConversionModel model) {
        this.model = model;
        view = new ConversionView(this);
    }

    @Override
    public double convertTemperature(double temperature, String inputScale, String outputScale) {
        model.setTemperature(temperature);

        return model.convert(inputScale, outputScale);
    }
}