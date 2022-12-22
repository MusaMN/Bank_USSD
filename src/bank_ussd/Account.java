package bank_ussd;

import java.sql.*;

/***
 * @author user
 */
public class Account {

    private Connection conn;
    private int accNum = 0, receiverAccNum = 0;
    private double accBal = 0;
    private ResultSet rs, rs_getBal, rs_setBal;

    public Account(int uid) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");

        String qry = "SELECT a.* FROM db_ussd.account a "
         + "WHERE a.accUserId = " + uid + ";";

        rs = conn.createStatement().executeQuery(qry);

        if(rs.next() != false) {
            this.accBal = rs.getDouble("accBal");
            this.accNum = rs.getInt(1); // 1 = accNum
        }
    }    

public Account(String phone) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");

        String qry = "SELECT a.accNum, a.accBal FROM db_ussd.account a, db_ussd.user u "
         + "WHERE a.accUserId  = u.userId AND u.userPhone = '" + phone + "';";

        rs = conn.createStatement().executeQuery(qry);

        if(rs.next() != false) {
            this.accNum = rs.getInt(1);            
            this.accBal = rs.getDouble(2);
        }
}

    public double getBalance() throws SQLException{
        rs_getBal = conn.createStatement().executeQuery("SELECT a.accBal bal FROM db_ussd.account a WHERE a.accNum = " + Integer.toString(accNum));
        rs_getBal.next();
        return rs_getBal.getDouble("bal");
    }

    public void setBalance (double balance) throws SQLException {
        String qry = "UPDATE db_ussd.account "
                    + "SET accBal = " + balance
                    + " WHERE accNum = " + getAccNumber();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(qry);
    }

    public int getAccNumber() {
        return this.accNum;
    }
}
