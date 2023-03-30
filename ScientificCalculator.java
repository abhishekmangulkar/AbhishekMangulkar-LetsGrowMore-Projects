import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScientificCalculator extends JFrame implements ActionListener {
    private JTextField display;
    private JButton[] buttons;
    private String[] buttonLabels = {"C", "CE", "<-", "+/-", "sqrt", "7", "8", "9", "/", "%", "4", "5", "6", "*", "1/x", "1", "2", "3", "-", "=", "0", ".", "sin", "cos", "tan"};
    private double num1, num2, result;
    private char operator;
    private boolean newCalculation;
    private boolean firstDigit;

    public ScientificCalculator() {
        super("Scientific Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(360, 400);
        setResizable(false);

        JPanel panel1 = new JPanel(new BorderLayout());
        display = new JTextField("0");
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        panel1.add(display, BorderLayout.NORTH);

        JPanel panel2 = new JPanel(new GridLayout(6, 4));
        buttons = new JButton[24];
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
            panel2.add(buttons[i]);
        }

        panel1.add(panel2, BorderLayout.CENTER);
        add(panel1);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "C":
                clearAll();
                break;
            case "CE":
                clearEntry();
                break;
            case "<-":
                backspace();
                break;
            case "+/-":
                changeSign();
                break;
            case "sqrt":
                squareRoot();
                break;
            case "1/x":
                reciprocal();
                break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "%":
                setOperator(command.charAt(0));
                break;
            case "=":
                calculate();
                break;
            case "sin":
            case "cos":
            case "tan":
                calculateTrig(command);
                break;
            default:
                addDigit(command);
                break;
        }
    }

    public void clearAll() {
        num1 = 0;
        num2 = 0;
        result = 0;
        operator = ' ';
        newCalculation = true;
        firstDigit = true;
        display.setText("0");
    }

    public void clearEntry() {
        display.setText("0");
    }

    public void backspace() {
        String text = display.getText();
        if (text.length() > 1) {
            display.setText(text.substring(0, text.length() - 1));
        } else {
            display.setText("0");
            firstDigit = true;
        }
    }

    public void changeSign() {
        String text = display.getText();
        if (!text.equals("0")) {
            if (text.charAt(0) == '-') {
                display.setText(text.substring(1));
            } else {
                display.setText("-" + text);
            }
        }
    }

    public void squareRoot() {
        double x = Double.parseDouble(display.getText());
        result = Math.sqrt(x);
        displayResult();
    }

    public void reciprocal() {
        double x = Double.parseDouble(display.getText());
        result = 1 / x;
        displayResult();
    }

    public void setOperator(char op) {
        if (newCalculation) {
            num1 = Double.parseDouble(display.getText());
            newCalculation = false;
        } else {
            num2 = Double.parseDouble(display.getText());
            calculate();
            num1 = result;
        }
        operator = op;
        firstDigit = true;
    }

    public void calculate() {
        num2 = Double.parseDouble(display.getText());
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            case '%':
                result = num1 % num2;
                break;
        }
        displayResult();
        newCalculation = true;
    }

    public void calculateTrig(String command) {
        double x = Double.parseDouble(display.getText());
        switch (command) {
            case "sin":
                result = Math.sin(x);
                break;
            case "cos":
                result = Math.cos(x);
                break;
            case "tan":
                result = Math.tan(x);
                break;
        }
        displayResult();
    }

    public void addDigit(String digit) {
        String text = display.getText();
        if (firstDigit) {
            if (digit.equals(".")) {
                display.setText("0.");
            } else {
                display.setText(digit);
            }
            firstDigit = false;
        } else {
            if (digit.equals(".")) {
                if (!text.contains(".")) {
                    display.setText(text + ".");
                }
            } else {
                display.setText(text + digit);
            }
        }
    }

    public void displayResult() {
        display.setText(Double.toString(result));
        firstDigit = true;
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}