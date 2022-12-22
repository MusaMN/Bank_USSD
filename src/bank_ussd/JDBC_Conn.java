package bank_ussd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author MUSA
 */
public class JDBC_Conn {
    Connection conn;

    public JDBC_Conn() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
    }
}
