/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bank_ussd;

import java.sql.*;

/**
 *
 * @author user
 */
public class User {
    private Connection conn;
    private ResultSet rs;
    private int id = 0;
    private String name, phone, pin = "";

    public User() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
    }

    public int login(String phone, String pin) throws SQLException {
       String qry = "SELECT u.* FROM db_ussd.user u "
                + "WHERE u.userPhone = '" + phone + "' AND u.userPin = '" + pin + "';";

        rs = conn.createStatement().executeQuery(qry);
        
        if(rs.next() == false) {
            System.out.println("Invalid username and/or password");
            return 0;
        }
        else {            
            if(phone.equals(rs.getString("userPhone")) && pin.equals(rs.getString("userPin"))) {

                this.phone = phone;
                this.pin = pin;
                this.name = rs.getString("userName") + " " + rs.getString("userSurname");
                this.id = rs.getInt("userId");
                return 1;
            }
            else {
                return 0;
            }
        }        
    }

    public String getPhone() {
        return this.phone;
    }

    public String getName() {
        return this.name;
    }

    public int getUid() {
        return this.id;
    }

    public void logout() {
        name = phone = pin =  null;
    }
}
