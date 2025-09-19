package schoolproject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class Registration {

    Scanner scan = new Scanner(System.in);

    String name;
    String surname;
    String username;
    String password;
    String cellphoneNumber;

    public void userInput() {

        //prompt user for details 
        //prompt user for name
        System.out.println("  Please enter your name: ");

        name = scan.nextLine();

        //prompt user for surname 
        System.out.println("  Please enter your surname:");

        surname = scan.nextLine();
        System.out.println("***************************************************************");
        System.out.println("   WELCOME TO REGISTRATION " + name + " " + surname + "!!!     ");
        System.out.println("***************************************************************");
        //prompt user for username
        do {

            System.out.println("Please enter your username: "
                    + "\n Your username must have an underscore "
                    + "\n It must contain at least 5 characters ");
            username = scan.nextLine();

        } while (!checkUsername(username));

        //prompt user for password
        do {

            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Please enter your password. "
                    
                    + "Your password must have: "
                    + "\n ~At least 8 characters. "
                    + "\n ~At least one speacial character "
                    + "\n ~It must have at least one digit ");
            password = scan.nextLine();

        } while (!checkPassword(password));

        //prompt user for number
        do {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Please enter your cellphone number: "
                    + "\n Please use your *AREA CODE*");
            cellphoneNumber = scan.nextLine();
        } while (!checkCellphoneNumber(cellphoneNumber));
    }// end of user input 

    public boolean checkUsername(String username) {
        //username must have an underscore(_)        //five characters
        if (username.contains("_") && username.length() <= 5) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Username is successfully captured.");
            return true;
        } else {
            System.out.println("Your username is not correctly formatted, "
                    + " \n ~Please try again. ");
            return false;
        }

    }// end of checkUsername

    public boolean checkPassword(String password) {

        //check password length
        boolean hasMinLength = password.length() >= 8;

        //check password has uppercase
        boolean hasUppercase = password.matches(".*[A-Z].*");

        //check if password contains a number
        boolean hasDigit = password.matches(".*\\d.*");

        //check if password has special character
        boolean hasSpecialCharacters = password.matches(".*[!@#$%^&*()_-].*");

        if (hasMinLength && hasDigit && hasUppercase && hasSpecialCharacters) {

           
            System.out.println("Password is successfully capture.");
            return true;
        } else {
            System.out.println("Your password is not correctly formatted "
                    + "\n Please try again.");
            return false;
        }

    }//end of checkPassword

    public boolean checkCellphoneNumber(String cellphoneNumber) {

        //check country code
        boolean hasCountryCode = cellphoneNumber.contains("+27");

        //check length
        boolean hasMaxLength = cellphoneNumber.length() == 12;

        // contains numbers only
        boolean hasDigit = cellphoneNumber.matches(".*\\d{9}.*");

        if (hasCountryCode && hasMaxLength && hasDigit) {
            System.out.println("Your cellphone number is successfully captured");
            return true;
        } else {
            System.out.println("Your cellphone number  is not correctly formatted. "
                    + "\n It does not contain international code. ");
            return false;
        }

    }//end of checkNumber

}//end of registration
