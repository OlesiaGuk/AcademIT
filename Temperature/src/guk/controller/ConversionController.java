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
    public void convertTemperature(String temperature) {
        model.setTemperature(Double.parseDouble(temperature));

        if (view.getComboBoxFrom().getSelectedItem() != null && view.getComboBoxTo().getSelectedItem() != null) {
            double convertedTemperature =
                    model.convert(view.getComboBoxFrom().getSelectedItem().toString(), view.getComboBoxTo().getSelectedItem().toString());
            view.getTemperatureOutputField().setText(Double.toString(convertedTemperature));
        }
    }
}