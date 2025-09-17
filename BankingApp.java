import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// BankAccount class demonstrating OOP (Encapsulation)
class BankAccount {
    private String accountHolder;
    private double balance;

    public BankAccount(String accountHolder, double initialBalance) {
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

// GUI Application
public class BankingApp extends JFrame {
    private BankAccount account;
    private JTextField inputAmount;
    private JLabel balanceLabel;

    public BankingApp() {
        // Create a sample account
        account = new BankAccount("Abhishek", 1000);

        setTitle("Simple Banking Application");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JLabel welcome = new JLabel("Welcome, " + account.getAccountHolder());
        welcome.setFont(new Font("Arial", Font.BOLD, 16));
        add(welcome);

        balanceLabel = new JLabel("Balance: ₹" + account.getBalance());
        add(balanceLabel);

        inputAmount = new JTextField(15);
        add(inputAmount);

        JButton depositBtn = new JButton("Deposit");
        JButton withdrawBtn = new JButton("Withdraw");
        JButton checkBalanceBtn = new JButton("Check Balance");

        add(depositBtn);
        add(withdrawBtn);
        add(checkBalanceBtn);

        // Deposit Action
        depositBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(inputAmount.getText());
                account.deposit(amount);
                updateBalance();
            }
        });

        // Withdraw Action
        withdrawBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(inputAmount.getText());
                boolean success = account.withdraw(amount);
                if (!success) {
                    JOptionPane.showMessageDialog(null, "Insufficient Balance!");
                }
                updateBalance();
            }
        });

        // Balance Inquiry
        checkBalanceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Current Balance: ₹" + account.getBalance());
            }
        });

        setVisible(true);
    }

    private void updateBalance() {
        balanceLabel.setText("Balance: ₹" + account.getBalance());
        inputAmount.setText("");
    }

    public static void main(String[] args) {
        new BankingApp();
    }
}
