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
            MessageManager manager = new MessageManager(100);
            logicClass logic = new logicClass(manager);
           messMenu(logic, manager);
        }

        return success;
    }

    public boolean checkUserDetails(String username, String password) {

        if (username.equals(register.username)
                && password.equals(register.password)) {

            System.out.println("Hello " + register.name + " " + register.surname + " It is good to have you again.");
            return true;
        } else {
            System.out.println("Username or password is incorrect.");
            return false;
        }
    }// end of check password and username

    //Mess Menu Methods
    private static void messMenu(logicClass logic, MessageManager manager) {

        int choice;

        do {

            choice = Integer.parseInt(JOptionPane.showInputDialog(null,
                    "Welcome to The Message Menu: \n"
                    + "1. Send Message\n"
                    + "2. Search by Message ID\n"
                    + "3. Search by Hash\n"
                    + "4. Search by Recipient\n"
                    + "5. Delete Message\n"
                    + "6. Exit"));

            switch (choice) {
                case 1:
                    int numberOfMess = Integer.parseInt(JOptionPane.showInputDialog(null, "Hi there :) How many messages do you want to send?"));

                    for (int i = 0; i < numberOfMess; i++) {
                        // Automatically generate a random 10-digit message ID
                        String id = String.valueOf(1000000000 + new java.util.Random().nextInt(900000000));

                        // Call recipient cell method here
                        String cell = JOptionPane.showInputDialog(null, "Enter reciever's cell number (+27...)");
                        int validCell = logic.checkRecipientCell(cell);

                        if (validCell == 0) {
                            JOptionPane.showMessageDialog(null, "Recipient Number Invalid!");
                            continue;
                        }

                        String text = JOptionPane.showInputDialog(null, "Enter your Message text:");
                        String hash = logic.createMessageHash(id, i + 1, text);

                        JOptionPane.showMessageDialog(null, "Message Hash:\n" + hash);

                        JOptionPane.showMessageDialog(null, logic.printMessage());

                        logic.handleSendMessage(); // Offers sent, stored, or disregarded
                    }
                    break;
                case 2:
                    manager.searchByMessageID();
                    break;
                case 3:
                    manager.searchByMessageHash();
                    break;
                case 4:
                    manager.searchByRecipient();
                    break;
                case 5:
                    manager.deleteByMessageID();
                    break;
                case 6:
                    JOptionPane.showMessageDialog(null, "Exiting the chat");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invaild option, try again.");
                    break;
            }

        } while (choice != 6);
    }
}
