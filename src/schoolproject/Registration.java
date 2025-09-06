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
        System.out.println("Please enter your name:");
        name = scan.nextLine();
        
        //prompt user for surname 
        System.out.println("Please enter your surname:");
        surname = scan.nextLine();
        
        //prompt user for username
        
        do{
        System.out.println("Please enter your username:");
        username = scan.nextLine();
        }while (!checkUsername(username));
        
        //prompt user for password
        
        do{
        System.out.println("Please enter your password:");
        password = scan.nextLine();
        }while (!checkPassword(password));
        
        //prompt user for number
       
        do{
        System.out.println("Please enter your cellphone number:");
        cellphoneNumber = scan.nextLine();
        }while (!checkCellphoneNumber(cellphoneNumber));
        
        
    }// end of user input 
    
    public boolean checkUsername( String username){
        //username must have an underscore(_)
        //five characters
        if (username.contains("_") && username.length() <=5){
            System.out.println("Username is successfully captured");
            return true;
        }else{ 
            System.out.println("Your username didn't meet the following requirments...");
            return false;
        }
    
    }// end of checkUsername
    
    public boolean checkPassword(String password){
        
        //check password length
        boolean hasMinLength = password.length() >=8;

        //check password has uppercase
        boolean hasUppercase = password.matches(".*[A-Z].*");
        
        //check if password contains a number
        boolean hasDigit = password.matches(".*\\d.*");
        
        //check if password has special character
        boolean hasSpecialCharacters = password.matches(".*[!@#$%^&*()_-].*");
        
        if (hasMinLength && hasDigit && hasUppercase && hasSpecialCharacters){
        
            System.out.println("Password is successfully capture");
            return true;
        }else{
            System.out.println("Your password didn't meet the following requirments...");
            return false;
        }
        
        
    }//end of checkPassword
    
    public boolean checkCellphoneNumber(String cellphoneNumber){
    
        //check country code
        boolean hasCountryCode = cellphoneNumber.contains("+27");
        
        //check length
        boolean hasMaxLength = cellphoneNumber.length() == 12;
        
        // contains numbers only
        boolean hasDigit = cellphoneNumber.matches(".*\\d{9}.*");
        
        
        if (hasCountryCode && hasMaxLength && hasDigit){
            System.out.println("Cellphone numbewr is successfully captured");
            return true;
        }else{ 
            System.out.println("Your cellphone number didn't meet the following requirments...");
            return false;
        }
    
    
    }//end of checkNumber

}//end of registration
