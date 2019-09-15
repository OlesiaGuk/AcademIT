package guk.view;

import guk.model.ConversionModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ConversionView {
    private JFrame frame;
    private JTextField temperatureInputField;
    private JTextField temperatureOutputField;
    private JButton convertButton;
    private JLabel labelFrom;
    private JLabel labelTo;
    private JComboBox comboBoxFrom;
    private JComboBox comboBoxTo;

    public ConversionView() {
        frame = new JFrame("Перевод температур");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(getFrameSize());
        frame.setLocationRelativeTo(null);

        temperatureInputField = new JTextField(5);
        temperatureOutputField = new JTextField(5);
        temperatureOutputField.setEditable(false);
        temperatureOutputField.setBackground(Color.white);
        convertButton = new JButton("Перевести");
        labelFrom = new JLabel("из ");
        labelTo = new JLabel("в ");
        comboBoxFrom = new JComboBox<>(ConversionModel.getTemperatureScales());
        comboBoxTo = new JComboBox<>(ConversionModel.getTemperatureScales());

        SwingUtilities.invokeLater(() -> {
            //создаем первую строку компонентов на форме
            JPanel firstLinePanel = new JPanel();
            firstLinePanel.setLayout(new BoxLayout(firstLinePanel, BoxLayout.X_AXIS));

            firstLinePanel.add(temperatureInputField);
            temperatureInputField.setMaximumSize(new Dimension(70, 50));
            firstLinePanel.add(Box.createHorizontalStrut(25));

            firstLinePanel.add(convertButton);
            firstLinePanel.add(Box.createHorizontalStrut(25));

            firstLinePanel.add(temperatureOutputField);
            temperatureOutputField.setMaximumSize(new Dimension(70, 50));

            //вторая строка
            JPanel secondLinePanel = new JPanel(new GridBagLayout());
            secondLinePanel.add(labelFrom);
            secondLinePanel.add(Box.createHorizontalStrut(5));
            secondLinePanel.add(comboBoxFrom);
            secondLinePanel.add(Box.createHorizontalStrut(10));
            secondLinePanel.add(labelTo);
            secondLinePanel.add(Box.createHorizontalStrut(5));
            secondLinePanel.add(comboBoxTo);

            Box mainBox = Box.createVerticalBox();
            mainBox.setBorder(new EmptyBorder(50, 10, 50, 10));
            mainBox.add(firstLinePanel);
            mainBox.add(secondLinePanel);

            frame.add(mainBox);
            frame.setVisible(true);
        });
    }

    private Dimension getFrameSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        double frameSizeMultiplier = (double) 1 / 3;
        screenSize.setSize((int) (width * frameSizeMultiplier), (int) (height * frameSizeMultiplier));

        return screenSize;
    }

    public void wrongTypeMessage() {
        JOptionPane.showMessageDialog(null, "Введено не число!", "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField getTemperatureInputField() {
        return temperatureInputField;
    }

    public void setTemperatureInputField(JTextField temperatureInputField) {
        this.temperatureInputField = temperatureInputField;
    }

    public JTextField getTemperatureOutputField() {
        return temperatureOutputField;
    }

    public void setTemperatureOutputField(JTextField temperatureOutputField) {
        this.temperatureOutputField = temperatureOutputField;
    }

    public JButton getConvertButton() {
        return convertButton;
    }

    public void setConvertButton(JButton convertButton) {
        this.convertButton = convertButton;
    }

    public JLabel getLabelFrom() {
        return labelFrom;
    }

    public void setLabelFrom(JLabel labelFrom) {
        this.labelFrom = labelFrom;
    }

    public JLabel getLabelTo() {
        return labelTo;
    }

    public void setLabelTo(JLabel labelTo) {
        this.labelTo = labelTo;
    }

    public JComboBox getComboBoxFrom() {
        return comboBoxFrom;
    }

    public void setComboBoxFrom(JComboBox comboBoxFrom) {
        this.comboBoxFrom = comboBoxFrom;
    }

    public JComboBox getComboBoxTo() {
        return comboBoxTo;
    }

    public void setComboBoxTo(JComboBox comboBoxTo) {
        this.comboBoxTo = comboBoxTo;
    }
}
