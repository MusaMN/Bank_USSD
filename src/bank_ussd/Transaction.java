package bank_ussd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/***
 * @author user
 */
public class Transaction extends Account{
    private final Connection conn;
    private char transType;
    private Double transAmount = 0.0, balance = 0.0;

    public Transaction(int uid) throws SQLException, ClassNotFoundException {
        super(uid);
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
  
    }

    public void withdraw(double transAmount) throws SQLException {
        setBalance(getBalance() - transAmount);
        System.out.println("Transaction Done. Current Balance :: " + getBalance());
    }

    public void deposit(double transAmount) throws SQLException{
        setBalance(getBalance() + transAmount);
        System.out.println("Transaction Done. Current Balance :: " + getBalance());
    }

    public void transfer(String receiverPhone, double transAmount) throws SQLException, ClassNotFoundException {
        Account acc = new Account(receiverPhone);
        withdraw(transAmount);
        acc.setBalance(acc.getBalance()+transAmount);
    }
}
