package guk.view;

import guk.controller.ControllerInterface;
import guk.model.ConversionModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConversionView implements ActionListener {
    private ControllerInterface controller;
    private JFrame frame;
    private JLabel labelInputFieldSign;
    private JLabel labelResultFieldSign;
    private JTextField temperatureInputField;
    private JTextField temperatureOutputField;
    private JButton convertButton;
    private JLabel labelFrom;
    private JLabel labelTo;
    private JComboBox comboBoxFrom;
    private JComboBox comboBoxTo;

    public ConversionView(ControllerInterface controller) {
        SwingUtilities.invokeLater(() -> {
            this.controller = controller;
            frame = new JFrame("Перевод температур");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setMinimumSize(getFrameSize());
            frame.setLocationRelativeTo(null);

            labelInputFieldSign = new JLabel("Температура: ");
            labelResultFieldSign = new JLabel("Результат: ");
            temperatureInputField = new JTextField(5);
            temperatureOutputField = new JTextField(5);
            temperatureOutputField.setEditable(false);
            temperatureOutputField.setBackground(Color.white);
            convertButton = new JButton("Перевести");
            labelFrom = new JLabel("из ");
            labelTo = new JLabel("в ");
            comboBoxFrom = new JComboBox<>(ConversionModel.ScalesEnum.values());
            comboBoxTo = new JComboBox<>(ConversionModel.ScalesEnum.values());

            JPanel signLinePanel = new JPanel();
            signLinePanel.setLayout(new BoxLayout(signLinePanel, BoxLayout.X_AXIS));
            signLinePanel.add(labelInputFieldSign);
            signLinePanel.add(Box.createHorizontalStrut(140));
            signLinePanel.add(labelResultFieldSign);

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
            mainBox.add(signLinePanel);
            mainBox.add(firstLinePanel);
            mainBox.add(secondLinePanel);

            frame.add(mainBox);
            frame.setVisible(true);

            convertButton.addActionListener(this);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String text = temperatureInputField.getText();

        if (!isTypeCorrect(text)) {
            return;
        }

        double temp = Double.parseDouble(text);
        if (comboBoxFrom.getSelectedItem() != null && comboBoxTo.getSelectedItem() != null) {
            temperatureOutputField.setText(Double.toString(controller.convertTemperature(
                    temp, comboBoxFrom.getSelectedItem().toString(), comboBoxTo.getSelectedItem().toString())));
        }
    }


    private static boolean isTypeCorrect(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            wrongTypeMessage();
            return false;
        }
    }

    private Dimension getFrameSize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        double frameSizeMultiplier = (double) 1 / 3;
        screenSize.setSize((int) (width * frameSizeMultiplier), (int) (height * frameSizeMultiplier));

        return screenSize;
    }

    private static void wrongTypeMessage() {
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