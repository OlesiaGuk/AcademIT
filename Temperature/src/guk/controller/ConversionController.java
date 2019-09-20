package guk.controller;

import guk.model.ConversionModel;
import guk.view.ConversionView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConversionController {
    private ConversionModel model;
    private ConversionView view;

    public ConversionController(ConversionModel model, ConversionView view) {
        this.model = model;
        this.view = view;
        initializeController();
    }

    private boolean isTypeCorrect(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            view.wrongTypeMessage();
            return false;
        }
    }

    private void initializeController() {
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
                            model.convert(view.getComboBoxFrom().getSelectedItem().toString(),view.getComboBoxTo().getSelectedItem().toString());
                    view.getTemperatureOutputField().setText(Double.toString(convertedTemperature));
                }
            }
        });
    }
}
