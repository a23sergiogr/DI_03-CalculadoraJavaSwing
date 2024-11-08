
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.math.BigDecimal;

public class Calculadora extends JFrame{
    private JTextField textField;
    private BigDecimal firstNumber = null;
    private BigDecimal secondNumber = null;
    private String operator = null;
    private boolean newEntry = true;

    public Calculadora(){
        setTitle("Formulario de Registro");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Border marginBorder = BorderFactory.createMatteBorder(12,12,12,12,new Color(238,238,238));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(marginBorder);
        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        textField = new JTextField();
        textField.setEditable(false);
        textField.setHorizontalAlignment(JTextField.RIGHT);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1; 
        panel.add(textField, gbc);

        JPanel panelNumbers = new JPanel(new GridLayout(4, 3, 2, 2));
        for (int i = 7; i <= 9; i++) {
            String num = String.valueOf(i);
            JButton button = new JButton(num);
            button.addActionListener(e -> appendNumber(num));
            panelNumbers.add(button);
        }
        for (int i = 4; i <= 6; i++) {
            String num = String.valueOf(i);
            JButton button = new JButton(num);
            button.addActionListener(e -> appendNumber(num));
            panelNumbers.add(button);
        }
        for (int i = 1; i <= 3; i++) {
            String num = String.valueOf(i);
            JButton button = new JButton(num);
            button.addActionListener(e -> appendNumber(num));
            panelNumbers.add(button);
        }
        
        JButton button = new JButton("0");
        JButton buttonMulti = new JButton("*");
        JButton buttonDiv = new JButton("/");

        button.addActionListener(e -> appendNumber("0"));
        buttonMulti.addActionListener(e -> setOperator("*"));
        buttonDiv.addActionListener(e -> setOperator("/"));
        
        panelNumbers.add(button);
        panelNumbers.add(buttonMulti);
        panelNumbers.add(buttonDiv);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weighty = 0.7; 
        panel.add(panelNumbers, gbc);

        JPanel panelOperations = new JPanel(new GridLayout(4, 1, 2, 2));
        JButton buttonC = new JButton("C");
        JButton buttonPlus = new JButton("+");
        JButton buttonMinus = new JButton("-");
        JButton buttonEquals = new JButton("=");
        
        panelOperations.add(buttonC);
        panelOperations.add(buttonPlus);
        panelOperations.add(buttonMinus);
        panelOperations.add(buttonEquals);

        buttonPlus.addActionListener(e -> setOperator("+"));
        buttonMinus.addActionListener(e -> setOperator("-"));
        buttonC.addActionListener(e -> clear());
        buttonEquals.addActionListener(e -> calculate());

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weighty = 0.7; 
        panel.add(panelOperations, gbc);
    }

    private void appendNumber(String num) {
        if (newEntry) {
            textField.setText(num);
            newEntry = false;
        } else {
            textField.setText(textField.getText() + num);
        }
    }

    private void setOperator(String op) {
        try {
            if (firstNumber == null) {
                firstNumber = new BigDecimal(textField.getText());
            }
            operator = op;
            newEntry = true;
        } catch (NumberFormatException e) {
            textField.setText("Error");
        }
    }


    private void calculate() {
        try {
            if (firstNumber != null && operator != null) {
                secondNumber = new BigDecimal(textField.getText());
                String result = switch (operator) {
                    case "+" -> firstNumber.add(secondNumber).stripTrailingZeros().toPlainString();
                    case "-" -> firstNumber.subtract(secondNumber).stripTrailingZeros().toPlainString();
                    case "*" -> firstNumber.multiply(secondNumber).stripTrailingZeros().toPlainString();
                    case "/" -> secondNumber.equals(BigDecimal.ZERO) ? "ERROR" : firstNumber.divide(secondNumber).stripTrailingZeros().toPlainString();
                    default -> BigDecimal.ZERO.stripTrailingZeros().toPlainString();
                };
                textField.setText(result);
                if (result.equals("ERROR")) {
                    firstNumber = BigDecimal.ZERO;
                } else {
                    firstNumber = null;
                }
                newEntry = true; 
                operator = null; 
            }
        } catch (ArithmeticException e) {
            textField.setText("Error");
        }
    }

    private void clear() {
        textField.setText("");
        firstNumber = null;
        secondNumber = null;
        operator = null;
        newEntry = true;
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculadora app = new Calculadora();
            app.setVisible(true);
        });
    }
    
}
