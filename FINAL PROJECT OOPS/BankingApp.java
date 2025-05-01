import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankingApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BankingGUI());
    }
}

record Transaction(String type, double amount, LocalDateTime timestamp) implements Serializable {}

abstract class Account implements Serializable {
    protected final String accountNumber;
    protected final String holderName;
    protected double balance;
    protected double loanAmount;
    protected final List<Transaction> transactions = new ArrayList<>();
    protected boolean interestEligible;
    protected static final double LOAN_INTEREST = 0.10;

    public Account(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
        this.interestEligible = true;
        transactions.add(new Transaction("ACCOUNT_CREATION", balance, LocalDateTime.now()));
    }

    public boolean withdraw(double amount) {
        if (amount <= 0 || amount > getAvailableBalance()) return false;
        balance -= amount;
        interestEligible = false;
        transactions.add(new Transaction("WITHDRAWAL", -amount, LocalDateTime.now()));
        return true;
    }

    public void deposit(double amount) {
        if (amount <= 0) return;
        balance += amount;
        transactions.add(new Transaction("DEPOSIT", amount, LocalDateTime.now()));
    }

    public boolean takeLoan(double amount) {
        if (balance < amount) return false;
        double totalLoan = amount * (1 + LOAN_INTEREST);
        loanAmount += totalLoan;
        transactions.add(new Transaction("LOAN_TAKEN", amount, LocalDateTime.now()));
        return true;
    }

    public void repayLoan(double amount) {
        double payment = Math.min(amount, loanAmount);
        loanAmount -= payment;
        transactions.add(new Transaction("LOAN_REPAYMENT", payment, LocalDateTime.now()));
    }

    public double getAvailableBalance() {
        return balance - loanAmount;
    }

    public abstract void calculateInterest();
}

class SavingsAccount extends Account {
    private static final double INTEREST_RATE = 0.04;

    public SavingsAccount(String number, String name, double balance) {
        super(number, name, balance);
    }

    @Override
    public void calculateInterest() {
        if (interestEligible) {
            double interest = balance * INTEREST_RATE;
            balance += interest;
            transactions.add(new Transaction("INTEREST", interest, LocalDateTime.now()));
        }
        interestEligible = true;
    }
}

class CurrentAccount extends Account {
    public CurrentAccount(String number, String name, double balance) {
        super(number, name, balance);
    }

    @Override
    public void calculateInterest() {
        // No interest for current accounts
    }
}

class BankManager {
    private final Map<String, Account> accounts = new HashMap<>();
    private static final String DATA_FILE = "bank.data";

    public void createAccount(String type, String number, String name, double balance) throws Exception {
        if (accounts.containsKey(number)) throw new Exception("Account already exists");
        
        Account acc = type.equalsIgnoreCase("SAVINGS") 
            ? new SavingsAccount(number, name, balance) 
            : new CurrentAccount(number, name, balance);
        accounts.put(number, acc);
        saveData();
    }

    public Account getAccount(String number) throws Exception {
        Account acc = accounts.get(number);
        if (acc == null) throw new Exception("Account not found");
        return acc;
    }

    public void saveData() throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(accounts);
        }
    }

    @SuppressWarnings("unchecked")
    public void loadData() {
        try {
            File file = new File(DATA_FILE);
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
                    accounts.putAll((Map<String, Account>) ois.readObject());
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }
    }
}

class BankingGUI extends JFrame {
    private final BankManager bank = new BankManager();
    private final CardLayout cards = new CardLayout();
    private final JPanel mainPanel = new JPanel(cards);
    private final NumberFormat currency = NumberFormat.getCurrencyInstance();

    public BankingGUI() {
        bank.loadData();
        setupUI();
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupUI() {
        mainPanel.add(createMainMenu(), "menu");
        mainPanel.add(createAccountScreen(), "create");
        mainPanel.add(createTransactionScreen("Deposit"), "deposit");
        mainPanel.add(createTransactionScreen("Withdraw"), "withdraw");
        mainPanel.add(createLoanScreen(), "loan");
        mainPanel.add(createRepaymentScreen(), "repay");
        mainPanel.add(createCheckBalanceScreen(), "check-balance");
        add(mainPanel);
    }

    private JPanel createMainMenu() {
        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        String[] buttons = {"Create Account", "Deposit", "Withdraw", 
                          "Take Loan", "Repay Loan", "Check Balance", "Exit"};
        
        for (String label : buttons) {
            JButton btn = new JButton(label);
            btn.setFont(new Font("Arial", Font.BOLD, 16));
            btn.addActionListener(e -> handleMenuAction(label));
            panel.add(btn);
        }
        return panel;
    }

    private void handleMenuAction(String action) {
        switch (action) {
            case "Create Account" -> cards.show(mainPanel, "create");
            case "Deposit" -> cards.show(mainPanel, "deposit");
            case "Withdraw" -> cards.show(mainPanel, "withdraw");
            case "Take Loan" -> cards.show(mainPanel, "loan");
            case "Repay Loan" -> cards.show(mainPanel, "repay");
            case "Check Balance" -> cards.show(mainPanel, "check-balance");
            case "Exit" -> System.exit(0);
        }
    }

    private JPanel createAccountScreen() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        JTextField numberField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField balanceField = new JTextField();
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Savings", "Current"});

        panel.add(new JLabel("Account Number:"));
        panel.add(numberField);
        panel.add(new JLabel("Account Holder:"));
        panel.add(nameField);
        panel.add(new JLabel("Initial Deposit:"));
        panel.add(balanceField);
        panel.add(new JLabel("Account Type:"));
        panel.add(typeCombo);

        JButton createBtn = new JButton("Create Account");
        createBtn.addActionListener(e -> {
            try {
                bank.createAccount(
                    (String) typeCombo.getSelectedItem(),
                    numberField.getText(),
                    nameField.getText(),
                    Double.parseDouble(balanceField.getText())
                );
                JOptionPane.showMessageDialog(this, "Account created successfully!");
                cards.show(mainPanel, "menu");
            } catch (Exception ex) {
                showError(ex.getMessage());
            }
        });
        panel.add(createBtn);
        panel.add(createBackButton());
        return panel;
    }

    private JPanel createTransactionScreen(String operation) {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField accField = new JTextField();
        JTextField amountField = new JTextField();

        panel.add(new JLabel("Account Number:"));
        panel.add(accField);
        panel.add(new JLabel("Amount:"));
        panel.add(amountField);

        JButton btn = new JButton(operation);
        btn.addActionListener(e -> handleTransaction(operation, accField.getText(), amountField.getText()));
        panel.add(btn);
        panel.add(createBackButton());
        return panel;
    }

    private void handleTransaction(String operation, String accNumber, String amountStr) {
        try {
            Account acc = bank.getAccount(accNumber);
            double amount = Double.parseDouble(amountStr);
            
            if (operation.equals("Deposit")) {
                acc.deposit(amount);
            } else {
                if (!acc.withdraw(amount)) throw new Exception("Withdrawal failed");
            }
            
            bank.saveData();
            JOptionPane.showMessageDialog(this, "Operation successful!\nNew Balance: " 
                + currency.format(acc.getAvailableBalance()));
            cards.show(mainPanel, "menu");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private JPanel createLoanScreen() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField accField = new JTextField();
        JTextField amountField = new JTextField();

        panel.add(new JLabel("Account Number:"));
        panel.add(accField);
        panel.add(new JLabel("Loan Amount:"));
        panel.add(amountField);

        JButton btn = new JButton("Take Loan");
        btn.addActionListener(e -> handleLoan(accField.getText(), amountField.getText()));
        panel.add(btn);
        panel.add(createBackButton());
        return panel;
    }

    private JPanel createRepaymentScreen() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField accField = new JTextField();
        JTextField amountField = new JTextField();

        panel.add(new JLabel("Account Number:"));
        panel.add(accField);
        panel.add(new JLabel("Repayment Amount:"));
        panel.add(amountField);

        JButton btn = new JButton("Repay Loan");
        btn.addActionListener(e -> handleRepayment(accField.getText(), amountField.getText()));
        panel.add(btn);
        panel.add(createBackButton());
        return panel;
    }

    private JPanel createCheckBalanceScreen() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField accField = new JTextField();
        
        panel.add(new JLabel("Account Number:"));
        panel.add(accField);
        panel.add(new JLabel());
        panel.add(new JLabel());

        JButton checkBtn = new JButton("Check Balance");
        checkBtn.addActionListener(e -> handleCheckBalance(accField.getText()));
        panel.add(checkBtn);
        panel.add(createBackButton());
        return panel;
    }

    private void handleCheckBalance(String accNumber) {
        try {
            Account acc = bank.getAccount(accNumber);
            String message = "Account Holder: " + acc.holderName + "\n" +
                           "Account Number: " + acc.accountNumber + "\n" +
                           "Current Balance: " + currency.format(acc.balance) + "\n" +
                           "Loan Amount: " + currency.format(acc.loanAmount) + "\n" +
                           "Available Balance: " + currency.format(acc.getAvailableBalance());
            
            if (acc instanceof SavingsAccount) {
                message += "\nInterest Eligibility: " + (acc.interestEligible ? "Yes" : "No");
            }
            
            JOptionPane.showMessageDialog(this, message, "Account Details", JOptionPane.INFORMATION_MESSAGE);
            cards.show(mainPanel, "menu");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void handleLoan(String accNumber, String amountStr) {
        try {
            Account acc = bank.getAccount(accNumber);
            double amount = Double.parseDouble(amountStr);
            
            if (!acc.takeLoan(amount)) throw new Exception("Loan request denied");
            bank.saveData();
            JOptionPane.showMessageDialog(this, "Loan approved!\nTotal Debt: " 
                + currency.format(acc.loanAmount));
            cards.show(mainPanel, "menu");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private void handleRepayment(String accNumber, String amountStr) {
        try {
            Account acc = bank.getAccount(accNumber);
            double amount = Double.parseDouble(amountStr);
            
            acc.repayLoan(amount);
            bank.saveData();
            JOptionPane.showMessageDialog(this, "Repayment successful!\nRemaining Debt: " 
                + currency.format(acc.loanAmount));
            cards.show(mainPanel, "menu");
        } catch (Exception ex) {
            showError(ex.getMessage());
        }
    }

    private JButton createBackButton() {
        JButton back = new JButton("Back to Menu");
        back.addActionListener(e -> cards.show(mainPanel, "menu"));
        return back;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}