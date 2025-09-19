/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolproject;

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

    public void log() {
        Scanner scan = new Scanner(System.in);
        String loginUsername;
        String loginPassword;
        
        System.out.println("Enter Username");
        loginUsername = scan.nextLine();

        System.out.println("Enter password");
        loginPassword = scan.nextLine();

        //Method to check user details
        checkUserDetails(loginUsername, loginPassword);

    }//end of login method8

    public boolean checkUserDetails(String username, String password) {

        if (username.equals(register.username)
                && password.equals(register.password)) {
            System.out.println("Hello " + register.name + " " +register.surname + " It is good to have you again");
            return true;
        } else {
            System.out.println("Username or password is incorrect.");
            return false;
        }
    }// end of check password and username
}
