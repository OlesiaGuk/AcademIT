package guk;

import guk.controller.ConversionController;
import guk.model.ConversionModel;

public class ConversionMain {
    public static void main(String[] args) {
        ConversionModel model = new ConversionModel();
        ConversionController controller = new ConversionController(model);
    }
}