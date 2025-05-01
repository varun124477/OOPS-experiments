import javax.script.*;
import javax.swing.*;

public class Calculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        JTextField textField = new JTextField();
        textField.setBounds(30, 30, 210, 40);
        frame.add(textField);

        String[] buttons = {
            "7", "8", "9", "/", 
            "4", "5", "6", "*", 
            "1", "2", "3", "-", 
            "0", "C", "=", "+"
        };

        int x = 30, y = 80;
        StringBuilder expression = new StringBuilder();

        for (int i = 0; i < buttons.length; i++) {
            JButton btn = new JButton(buttons[i]);
            btn.setBounds(x, y, 50, 40);
            frame.add(btn);

            btn.addActionListener(e -> {
                String cmd = e.getActionCommand();
                if (cmd.equals("=")) {
                    try {
                        textField.setText("" + eval(expression.toString()));
                    } catch (Exception ex) {
                        textField.setText("Error");
                    }
                    expression.setLength(0);
                } else if (cmd.equals("C")) {
                    expression.setLength(0);
                    textField.setText("");
                } else {
                    expression.append(cmd);
                    textField.setText(expression.toString());
                }
            });

            x += 60;
            if ((i + 1) % 4 == 0) {
                x = 30;
                y += 50;
            }
        }

        frame.setSize(300, 350);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Simple expression evaluator
    public static double eval(String expr) throws ScriptException {
        return ((Number) new ScriptEngineManager().getEngineByName("JavaScript").eval(expr)).doubleValue();
    }
}
