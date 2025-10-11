/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolproject;
import javax.swing.JOptionPane;
import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class Login {

    Registration register;
    String name;
    String surname;

    public Login(Registration register) {
        this.register = register;
    }

    public boolean log() {
    Scanner scan = new Scanner(System.in);
    System.out.println("Enter Username");
    String loginUsername = scan.nextLine();

    System.out.println("Enter password");
    String loginPassword = scan.nextLine();

    boolean success = checkUserDetails(loginUsername, loginPassword);

    if (success) {
        JOptionPane.showMessageDialog(null, "Welcome to TheChat!");
        logicClass logic = new logicClass();
                messMenu(logic);
    }

    return success;
}


    public boolean checkUserDetails(String username, String password) {

        if (username.equals(register.username)
                && password.equals(register.password)) {
            
            System.out.println("Hello " + register.name + " " +register.surname + " It is good to have you again.");
            return true;
        } else {
            System.out.println("Username or password is incorrect.");
            return false;
        }
    }// end of check password and username
    
    //Mess Menu Methods
    private static void messMenu(logicClass logic){
    
        int choice;
        
        do{
        
            choice = Integer.parseInt(JOptionPane.showInputDialog(null, 
                    "Message Menu: \n"
                              + " 1. Send message\n"
                               + "2. Show recent message (coming soon)\n"
                               + "3. Exit"));
            
            switch(choice){
                case 1:
                    int numberOfMess = Integer.parseInt(JOptionPane.showInputDialog(null, "How many messages do you want to send?"));
                    
                    for(int i = 0; i < numberOfMess; i++){
                    
                        String id = JOptionPane.showInputDialog(null, "Enter messgae ID (max 10 digits):");
                        if (!logic.checkMessageID(id)){
                        JOptionPane.showMessageDialog(null, "Invaild Message ID");
                        continue;
                        }
                        
                        String text = JOptionPane.showInputDialog(null, "Enter your Message text:");
                        String hash = logic.createMessageHash(id, i + 1 , text);
                        
                        JOptionPane.showMessageDialog(null, "Message Hash:\n" + hash);
                        
                       // String result = logic.sentMessage(choice);
                        JOptionPane.showMessageDialog(null, "Message Status: " + choice);
                        
                    }
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Feature coming soon!");
                    break;
                    
                case 3:
                    JOptionPane.showMessageDialog(null, "Exiting the chat");
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "Invaild option, try again.");
                    break;
            }
            
        }while (choice != 3);
    }
}
