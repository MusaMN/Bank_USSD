package bank_ussd;
/**
 * @author MUSA
 */
import static java.lang.System.*;
import java.io.Console;
import java.sql.SQLException;
import java.util.*;

public class Bank_USSD {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String phone="", receiverPhone="", pin = "";
        double balance = 0;
        double transAmount = 0.0;
       
        try {
            out.println("Enter phone number: ");
            phone = sc.nextLine();
            out.println("Enter PIN: ");
            pin = sc.nextLine();
            
            User user = new User();

            if(user.login(phone, pin) == 1) {

                Account acc = new Account(user.getUid());
                Transaction transaction = new Transaction(user.getUid());

                out.println("Welcome " + user.getName());
                out.println("Current balance: " + Double.toString(acc.getBalance()));
                out.println("\nWhat would you like to do?");
                out.println("\t1. Wihdraw \n\t2. Deposit \n\t3. Transfer \n\t4. Buy airtime");   

                ArrayList<Integer> validOption =  new ArrayList<>(Arrays.asList(1,2,3,4));                              
                int option;

                do {
                    option = sc.nextInt();
                    switch(option) {                        
                        case 1: { //withdraw
                            out.println("Enter amount :: ");
                            transAmount = sc.nextDouble();
                            if(transAmount <= acc.getBalance()) 
                                transaction.withdraw(/*acc.getAccNumber(), */transAmount);        
                            else
                                out.println("Insufficient funds.");
                            break;
                            }
                        case 2: { //Deposit
                            out.println("Enter amount :: ");
                            transAmount = sc.nextDouble();                             
                            transaction.deposit(transAmount);
                            break; 
                            }
                        case 3:{//Transfer  
                            out.println("Enter amount to be transfered :: ");
                            transAmount = sc.nextDouble();
                            out.println("Enter receiver phone number :: ");
                            receiverPhone = sc.next();
                            transaction.transfer(receiverPhone, transAmount);
                            break;
                        }
                        case 4://Exit
                            user.logout();
                            break;
                        default:
                            out.println("Error. Enter a valid option :: ");
                    }
                }
                while(validOption.indexOf(option) == -1);
                //while(validOption != 1 || validOption != 2 || validOption != 3 || validOption != 4 ||)
            }               
            else {
                out.println("Invalid Login Credentials. Try again later");
            }

        }
        catch (ClassNotFoundException | SQLException e) {
            out.println("Error db level:: " + e);
        }
    }

}
