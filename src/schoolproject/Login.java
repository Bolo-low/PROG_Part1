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
     
    public Login(Registration register){
        this.register = register;
    }
    
    public void log(){
        Scanner scan = new Scanner(System.in);
        String loginUsername;
        String loginPassword;
        
        System.out.println("Enter Username");
        loginUsername = scan.nextLine();
        
        System.out.println("Enter password");
        loginPassword = scan.nextLine();
        
    }//end of login method
    
    public boolean checkUserDetails(String username, String password){
        
        if(username.equals(register.username)&&
               password.equals(register.password)){
            System.out.println("Welcome" + register.name);
            return true;
        }else{
            System.out.println("failed attempt");
            return false;
        }
    }// end of check password and username
}
