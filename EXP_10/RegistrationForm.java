import javax.swing.*;

public class RegistrationForm {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Registration Form");
        JLabel nameLbl = new JLabel("Name:");
        nameLbl.setBounds(30, 30, 100, 30);
        JTextField nameField = new JTextField();
        nameField.setBounds(130, 30, 150, 30);

        JLabel emailLbl = new JLabel("Email:");
        emailLbl.setBounds(30, 70, 100, 30);
        JTextField emailField = new JTextField();
        emailField.setBounds(130, 70, 150, 30);

        JLabel passLbl = new JLabel("Password:");
        passLbl.setBounds(30, 110, 100, 30);
        JPasswordField passField = new JPasswordField();
        passField.setBounds(130, 110, 150, 30);

        JLabel confirmLbl = new JLabel("Confirm Password:");
        confirmLbl.setBounds(30, 150, 120, 30);
        JPasswordField confirmField = new JPasswordField();
        confirmField.setBounds(160, 150, 120, 30);

        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(100, 190, 100, 30);

        JLabel result = new JLabel();
        result.setBounds(30, 230, 300, 30);

        registerBtn.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passField.getPassword());
            String confirm = new String(confirmField.getPassword());

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                result.setText("Please fill in all fields.");
            } else if (!email.contains("@")) {
                result.setText("Invalid email address.");
            } else if (!password.equals(confirm)) {
                result.setText("Passwords do not match.");
            } else {
                result.setText("Registration successful!");
            }
        });

        frame.add(nameLbl); frame.add(nameField);
        frame.add(emailLbl); frame.add(emailField);
        frame.add(passLbl); frame.add(passField);
        frame.add(confirmLbl); frame.add(confirmField);
        frame.add(registerBtn); frame.add(result);

        frame.setSize(350, 320);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
