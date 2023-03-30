import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CurrencyConverter extends JFrame implements ActionListener {
    private JLabel lblAmount, lblFrom, lblTo, lblResult;
    private JTextField txtAmount, txtResult;
    private JComboBox cmbFrom, cmbTo;
    private JButton btnConvert;

    private final String[] currencies = {"USD", "EUR", "INR", "JPY", "CAD", "AUD", "CHF"};

    public CurrencyConverter() {
        super("Currency Converter");

        // Create components
        lblAmount = new JLabel("Amount:");
        lblFrom = new JLabel("From:");
        lblTo = new JLabel("To:");
        lblResult = new JLabel("Result:");

        txtAmount = new JTextField(10);
        txtResult = new JTextField(10);
        txtResult.setEditable(false);

        cmbFrom = new JComboBox(currencies);
        cmbTo = new JComboBox(currencies);

        btnConvert = new JButton("Convert");
        btnConvert.addActionListener(this);

        // Add components to content pane
        Container c = getContentPane();
        c.setLayout(new GridLayout(4, 2));
        c.add(lblAmount);
        c.add(txtAmount);
        c.add(lblFrom);
        c.add(cmbFrom);
        c.add(lblTo);
        c.add(cmbTo);
        c.add(lblResult);
        c.add(txtResult);
        c.add(btnConvert);

        // Set window properties
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnConvert) {
            try {
                double amount = Double.parseDouble(txtAmount.getText());
                String from = (String) cmbFrom.getSelectedItem();
                String to = (String) cmbTo.getSelectedItem();

                double rate = getExchangeRate(from, to);
                double result = amount * rate;

                txtResult.setText(String.format("%.2f %s = %.2f %s", amount, from, result, to));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private double getExchangeRate(String from, String to) {
        // Replace this with your own code to get exchange rates from an API or database
        // For this example, we'll just hardcode some rates
        switch (from) {
            case "USD":
                switch (to) {
                    case "EUR":
                        return 0.85;
                    case "INR":
                        return 81;
                    case "JPY":
                        return 109.45;
                    case "CAD":
                        return 1.26;
                    case "AUD":
                        return 1.31;
                    case "CHF":
                        return 0.93;
                    default:
                        return 1;
                }
            case "EUR":
                switch (to) {
                    case "USD":
                        return 1.18;
                    case "GBP":
                        return 0.86;
                    case "JPY":
                        return 128.26;
                    case "CAD":
                        return 1.48;
                    case "AUD":
                        return 1.54;
                    case "CHF":
                        return 1.10;
                    default:
                        return 1;
                }
            case "INR":
                switch (to) {
                    case "USD":
                        return 1.38;
                    case "EUR":
                        return 1.16;
                    case "JPY":
                        return 150.53;
                    case "CAD":
                        return 1.73;
                    case "AUD":

                        return 1.80;
                    case "CHF":
                        return 1.28;
                    default:
                        return 1;
                }
            case "JPY":
                switch (to) {
                    case "USD":
                        return 0.0091;
                    case "EUR":
                        return 0.0078;
                    case "GBP":
                        return 0.0066;
                    case "CAD":
                        return 0.011;
                    case "AUD":
                        return 0.012;
                    case "CHF":
                        return 0.0085;
                    default:
                        return 1;
                }
            case "CAD":
                switch (to) {
                    case "USD":
                        return 0.79;
                    case "EUR":
                        return 0.68;
                    case "GBP":
                        return 0.58;
                    case "JPY":
                        return 89.51;
                    case "AUD":
                        return 1.04;
                    case "CHF":
                        return 0.74;
                    default:
                        return 1;
                }
            case "AUD":
                switch (to) {
                    case "USD":
                        return 0.76;
                    case "EUR":
                        return 0.65;
                    case "GBP":
                        return 0.56;
                    case "JPY":
                        return 86.11;
                    case "CAD":
                        return 0.96;
                    case "CHF":
                        return 0.71;
                    default:
                        return 1;
                }
            case "CHF":
                switch (to) {
                    case "USD":
                        return 1.07;
                    case "EUR":
                        return 0.91;
                    case "GBP":
                        return 0.78;
                    case "JPY":
                        return 117.62;
                    case "CAD":
                        return 1.34;
                    case "AUD":
                        return 1.42;
                    default:
                        return 1;
                }
            default:
                return 1;
        }
    }

    public static void main(String[] args) {
        new CurrencyConverter();
    }
}
