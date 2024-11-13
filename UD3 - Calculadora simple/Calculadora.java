
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.math.BigDecimal;

public class Calculadora extends JFrame{
    private JTextField textFieldResult, textFieldOperation;
    private BigDecimal firstNumber = null;
    private BigDecimal secondNumber = null;
    private String operator = null;
    private boolean newEntry = true, deleteText = false, writeOperaor = false;

    private int radioBorde = 20;  

    public Calculadora() {
        setTitle("Calculadora");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Border marginBorder = BorderFactory.createMatteBorder(12, 12, 12, 12, new Color(238, 238, 238));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(marginBorder);
        add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;

        textFieldOperation = new JTextField();
        textFieldOperation.setFont(new Font("Arial", Font.BOLD, 10));
        textFieldOperation.setForeground(Color.gray);
        textFieldOperation.setEditable(false);
        textFieldOperation.setHorizontalAlignment(JTextField.RIGHT);
        textFieldOperation.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.05;
        panel.add(textFieldOperation, gbc);

        textFieldResult = new JTextField();
        textFieldResult.setFont(new Font(operator, Font.BOLD, 24));
        textFieldResult.setForeground(Color.BLACK);
        textFieldResult.setEditable(false);
        textFieldResult.setHorizontalAlignment(JTextField.RIGHT);
        textFieldResult.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gbc.gridy = 1;
        panel.add(textFieldResult, gbc);

        Color colorNumeros = new Color(200, 200, 200);
        Color colorOperadores = new Color(173, 216, 230);
        Color colorAcciones = new Color(255, 182, 193);
        Color colorIgual = new Color(83, 83, 236);

        JPanel panelNumbers = new JPanel(new GridLayout(4, 3, 8, 8)); 
        panelNumbers.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (int i = 7; i <= 9; i++) {
            RoundedButton button = createNumberButton(String.valueOf(i), colorNumeros);
            panelNumbers.add(button);
        }
        for (int i = 4; i <= 6; i++) {
            RoundedButton button = createNumberButton(String.valueOf(i), colorNumeros);
            panelNumbers.add(button);
        }
        for (int i = 1; i <= 3; i++) {
            RoundedButton button = createNumberButton(String.valueOf(i), colorNumeros);
            panelNumbers.add(button);
        }

        RoundedButton button0 = createNumberButton("0", colorNumeros);
        RoundedButton buttonMulti = createOperatorButton("*", colorOperadores);
        RoundedButton buttonDiv = createOperatorButton("/", colorOperadores);

        panelNumbers.add(button0);
        panelNumbers.add(buttonMulti);
        panelNumbers.add(buttonDiv);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weighty = 0.7;
        panel.add(panelNumbers, gbc);

        JPanel panelOperations = new JPanel(new GridLayout(4, 1, 8, 8));
        panelOperations.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        RoundedButton buttonC = createActionButton("C", colorAcciones);
        RoundedButton buttonPlus = createOperatorButton("+", colorOperadores);
        RoundedButton buttonMinus = createOperatorButton("-", colorOperadores);
        RoundedButton buttonEquals = createActionButton("=", colorIgual);

        buttonEquals.setForeground(Color.white); 

        panelOperations.add(buttonC);
        panelOperations.add(buttonPlus);
        panelOperations.add(buttonMinus);
        panelOperations.add(buttonEquals);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weighty = 0.7;
        panel.add(panelOperations, gbc);

        buttonC.addActionListener(e -> clear());
        buttonEquals.addActionListener(e -> calculate());
    }

    private RoundedButton createNumberButton(String label, Color color) {
        RoundedButton button = new RoundedButton(label, radioBorde);
        button.setBackground(color);
        button.setFont(new Font(operator, Font.BOLD, 20));
        button.addActionListener(e -> appendNumber(label));
        return button;
    }

    private RoundedButton createOperatorButton(String label, Color color) {
        RoundedButton button = new RoundedButton(label, radioBorde);
        button.setBackground(color);
        button.setFont(new Font(operator, Font.BOLD, 20));
        button.addActionListener(e -> setOperator(label));
        return button;
    }

    private RoundedButton createActionButton(String label, Color color) {
        RoundedButton button = new RoundedButton(label, radioBorde);
        button.setBackground(color);
        button.setFont(new Font(operator, Font.BOLD, 20));
        return button;
    }

    class RoundedButton extends JButton {
        private int cornerRadius;
        private int borderWidth;
    
        public RoundedButton(String label, int radius) {
            super(label);
            this.cornerRadius = radius;
            this.borderWidth = 2;
            setOpaque(false);
            setContentAreaFilled(false);
        }
    
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
            int offset = borderWidth / 2;
            if (getModel().isArmed()) {
                g2.setColor(getBackground().darker());
            } else {
                g2.setColor(getBackground());
            }
            g2.fillRoundRect(offset, offset, getWidth() - borderWidth, getHeight() - borderWidth, cornerRadius, cornerRadius);
        
            g2.setColor(getForeground());
            FontMetrics fm = g2.getFontMetrics();
            int textX = (getWidth() - fm.stringWidth(getText())) / 2;
            int textY = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();
            g2.drawString(getText(), textX, textY);
        
            g2.dispose();
        }
    
        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
            g2.setStroke(new BasicStroke(borderWidth));
            g2.setColor(new Color(200, 200, 200));
            g2.drawRoundRect(borderWidth / 2, borderWidth / 2, getWidth() - borderWidth, getHeight() - borderWidth, cornerRadius, cornerRadius);
            g2.dispose();
        }
    }

    private void appendNumber(String num) {
        if(deleteText){
            textFieldOperation.setText("");
            deleteText = false;
        }
        StringBuilder stringBuilder = new StringBuilder(textFieldOperation.getText());
        stringBuilder.append(num);
        textFieldOperation.setText(stringBuilder.toString());
        writeOperaor = true;

        if (newEntry) {
            textFieldResult.setText(num);
            newEntry = false;
        } else {
            textFieldResult.setText(textFieldResult.getText() + num);
        }
    }

    private void setOperator(String op) {
        if (writeOperaor) {
        StringBuilder stringBuilder = new StringBuilder(textFieldOperation.getText());
        stringBuilder.append(op);
        textFieldOperation.setText(stringBuilder.toString());
        writeOperaor = false;
        }

        try {
            if (firstNumber == null) {
                firstNumber = new BigDecimal(textFieldResult.getText());
            }
            operator = op;
            newEntry = true;
        } catch (NumberFormatException e) {
            textFieldResult.setText("Error");
        }
    }

    private void calculate() {
        StringBuilder stringBuilder = new StringBuilder(textFieldOperation.getText());
        stringBuilder.append("=");
        textFieldOperation.setText(stringBuilder.toString());

        try {
            if (firstNumber != null && operator != null) {
                secondNumber = new BigDecimal(textFieldResult.getText());
                String result = switch (operator) {
                    case "+" -> firstNumber.add(secondNumber).stripTrailingZeros().toPlainString();
                    case "-" -> firstNumber.subtract(secondNumber).stripTrailingZeros().toPlainString();
                    case "*" -> firstNumber.multiply(secondNumber).stripTrailingZeros().toPlainString();
                    case "/" -> secondNumber.equals(BigDecimal.ZERO) ? "ERROR" : firstNumber.divide(secondNumber).stripTrailingZeros().toPlainString();
                    default -> BigDecimal.ZERO.stripTrailingZeros().toPlainString();
                };
                textFieldResult.setText(result);
                if (result.equals("ERROR")) {
                    textFieldOperation.setText("");
                    firstNumber = BigDecimal.ZERO;
                } else {
                    StringBuilder stringBuilder2 = new StringBuilder(textFieldOperation.getText());
                    stringBuilder2.append(result);
                    textFieldOperation.setText(stringBuilder2.toString());
                    firstNumber = null;
                }
                newEntry = true; 
                operator = null; 
                deleteText = true;
            }
        } catch (ArithmeticException e) {
            textFieldResult.setText("Error");
        }
    }

    private void clear() {
        textFieldOperation.setText("");
        textFieldResult.setText("");
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

