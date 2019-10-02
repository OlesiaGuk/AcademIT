package guk.controller;

import guk.model.ConversionModel;
import guk.model.Scale;
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

    @Override
    public String[] getScalesNames() {
        String[] array = new String[model.getScalesList().size()];
        int i = 0;
        for (Scale scale : model.getScalesList()) {
            array[i] = scale.getScaleName();
            i++;
        }
        return array;
    }
}