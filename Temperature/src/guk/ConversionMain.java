package guk;

import guk.controller.ConversionController;
import guk.model.ConversionModel;
import guk.view.ConversionView;

public class ConversionMain {
    public static void main(String[] args) {
        ConversionView view = new ConversionView();
        ConversionModel model = new ConversionModel();
        ConversionController controller = new ConversionController(model, view);
    }
}
