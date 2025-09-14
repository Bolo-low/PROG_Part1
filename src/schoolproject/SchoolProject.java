/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package schoolproject;

import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class SchoolProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int choice;
        menu();
        choice = menuChoice(scanner);

        //user's registration details
        Registration register = new Registration();
        register.userInput();

        //user's login details
        Login login = new Login(register);
        login.log();

        do {
            //menu

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
                    System.out.println("Invaild option. please select");
                    break;
            }

            System.out.println();

        } while (choice != 3);
        scanner.close();

    }

    //menu method
    private static void menu() {
        System.out.println("****************************************");
        System.out.println("                  HELLO                 ");
        System.out.println("****************************************");
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
        scanner.nextLine();
        return choice;
    }

    private static void handleReg(Scanner scanner, Registration register) {
        System.out.println("~~~REGISTRATION~~~");
        register.userInput();//Call without passing scanner
        System.out.println("Reg successful");
        System.out.println("You may now login.");
    }

    private static void handleLog(Scanner scanner, Login login) {
        System.out.println("---- login ----");
        login.log();

        System.out.println("Return to main menu...");
        scanner.nextLine();
    }

    private static void handleCancel() {
        System.out.println("Thank you. Goodbye");
    }

}
