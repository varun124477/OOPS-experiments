import javax.swing.*;

public class LoginForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Form");
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(30, 30, 80, 30);
        JTextField userText = new JTextField();
        userText.setBounds(120, 30, 150, 30);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(30, 70, 80, 30);
        JPasswordField passText = new JPasswordField();
        passText.setBounds(120, 70, 150, 30);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(120, 110, 80, 30);

        JLabel result = new JLabel();
        result.setBounds(30, 150, 250, 30);

        loginButton.addActionListener(e -> {
            String username = userText.getText();
            String password = new String(passText.getPassword());
            if ("admin".equals(username) && "password".equals(password)) {
                result.setText("Login Successful!");
            } else {
                result.setText("Invalid credentials.");
            }
        });

        frame.add(userLabel); frame.add(userText);
        frame.add(passLabel); frame.add(passText);
        frame.add(loginButton); frame.add(result);

        frame.setSize(350, 250);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}