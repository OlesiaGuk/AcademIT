package guk.controller;

import guk.model.ConversionModel;
import guk.view.ConversionView;

public class ConversionController {
    private ConversionModel model;
    private ConversionView view;

    public ConversionController(ConversionModel model, ConversionView view) {
        this.model = model;
        this.view = view;
    }

    public void getConversion() {
        String text = view.getTemperatureInputField().getText();
        if (!isTypeCorrect(text)) {
            return;
        }

        model.setTemperature(Double.parseDouble(text));
        if (view.getComboBoxFrom().getSelectedItem() != null && view.getComboBoxTo().getSelectedItem() != null) {
            double convertedTemperature =
                    model.convert(view.getComboBoxFrom().getSelectedItem().toString(), view.getComboBoxTo().getSelectedItem().toString());
            view.getTemperatureOutputField().setText(Double.toString(convertedTemperature));
        }
    }

    private static boolean isTypeCorrect(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            ConversionView.wrongTypeMessage();
            return false;
        }
    }
}

//todo: удалить код ниже
 /*private void initializeController() {
        view.getConvertButton().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String text = view.getTemperatureInputField().getText();

                if (!isTypeCorrect(text)) {
                    return;
                }

                model.setTemperature(Double.parseDouble(text));
                if (view.getComboBoxFrom().getSelectedItem() != null && view.getComboBoxTo().getSelectedItem() != null) {
                    double convertedTemperature =
                            model.convert(view.getComboBoxFrom().getSelectedItem().toString(), view.getComboBoxTo().getSelectedItem().toString());
                    view.getTemperatureOutputField().setText(Double.toString(convertedTemperature));
                }
            }
        });
    }*/