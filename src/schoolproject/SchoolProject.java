package schoolproject;

import java.util.Scanner;
import javax.swing.JOptionPane;
import schoolproject.Login;
import schoolproject.Registration;

public class SchoolProject {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registration register = new Registration();
        Login login = new Login(register);
        logicClass logic = new logicClass(); // messaging logic instance

        int choice;
        do {
            menu();
            choice = menuChoice(scanner);      // << read choice each loop

            switch (choice) {
                case 1:
                    handleReg(scanner, register);
                    break;

                case 2:
                    handleLog(scanner, login);
                    break;

                case 3:
                    handleCancel();
                    break;

                default:
                    System.out.println("Invalid option. Please select 1-3.");
                    break;
            }

            System.out.println();

        } while (choice != 3);

        scanner.close();

    }

    // menu method
    private static void menu() {
        System.out.println("****************************************");
        System.out.println("            HELLO NEWBIE!!              ");
        System.out.println("    Please enter your choice ( 1-3 ).   ");
        System.out.println(" 1. Register.");
        System.out.println(" 2. Login. ");
        System.out.println(" 3. Cancel.");
        System.out.println("****************************************");
    }

    private static int menuChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid");
            scanner.next();
            System.out.println("Please select a choice.");
        }

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume end-of-line
        return choice;
    }

    private static void handleReg(Scanner scanner, Registration register) {

        System.out.println("------------------------------------------");
        System.out.println("    ~~~~~WELCOME TO REGISTRATION~~~~~~~");
        System.out.println("------------------------------------------");
        register.userInput(); // call once
        System.out.println("           REG IS SUCCESSFUL");
        System.out.println("You may now login.");
        System.out.println("Press Enter to return to main menu.");

        scanner.nextLine();
    }

    private static void handleLog(Scanner scanner, Login login) {
        System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        System.out.println("~~~~~~~WELCOME TO THE LOGIN SITE~~~~~~~~");
        System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
        login.log();
        System.out.println("Return to main menu. Press enter.");
        scanner.nextLine();
    }

    private static void handleCancel() {
        System.out.println("Thank you. Goodbye");
    }

}
