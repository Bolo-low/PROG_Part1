/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package schoolproject;

import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author letso
 */
public class SchoolProjectNGTest {

    /*public SchoolProjectNGTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
    }

    @AfterMethod
    public void tearDownMethod() throws Exception {
    }

    /**
     * Test of main method, of class SchoolProject.
     */
 /*@Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        SchoolProject.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
    Registration Regtest = new Registration();

    //Username
    @Test
    public void validUsernameTest() {
        // Valid: contains underscore and <= 5 characters
        assertTrue(Regtest.checkUsername("Bo_lo"));
    }// Test_1

    @Test
    public void usernameWithoutUnderscoreTest() {
        // Invalid: missing underscore 
        assertFalse(Regtest.checkUsername("Bonolo"));
    }//Test_2

    @Test
    public void shortUsernameTest() {
        // Invalid: too short but must also contain underscore 
        assertFalse(Regtest.checkUsername("Bon"));
    }//Test_3

    //Password
    @Test
    public void validPasswordTest() {
        // Valid: contains special Characters 
        assertTrue(Regtest.checkPassword("Bonolo@10"));
    }//Test_4

    @Test
    public void validPasswordLength() {
        // Invalid: has less than 8 characters 
        assertFalse(Regtest.checkPassword("Bonolo"));
    }//Test_5

    @Test
    public void PasswordWithoutDigital() {
        // Invalid: has no digits 
        assertFalse(Regtest.checkPassword("Bonol@kgare"));
    }//Test_6

    @Test
    public void PasswordWithoutUpperCase() {
        // Invalid: has no UpperCase
        assertFalse(Regtest.checkPassword("bonol@kgare"));
    }//Test_7

    //Cellphone number
    @Test
    public void validCellphoneNumber() {
        // Valid: has no Country code
        assertTrue(Regtest.checkCellphoneNumber("+27671495415"));
    }//Test_8

    @Test
    public void cellphoneNumberWithoutCountryCode() {
        // Invalid: has no Country code
        assertFalse(Regtest.checkCellphoneNumber("0671495415"));
    }//Test_9

    @Test
    public void cellphoneNumberLenght() {
        // Invalid: has less then 12 digits including country code
        assertFalse(Regtest.checkCellphoneNumber("0671495415"));
    }//Test_10

    
    
    //Login
    public void validUserDetails() {
        // Set registration details first
        Regtest.username = "Bo_lo";
        Regtest.password = "Bonolo@10";

        Login logintest = new Login(Regtest);

        assertTrue(logintest.checkUserDetails("Bo_lo", "Bonolo@10")); // Pass-The-Test
    }
    
    @Test
    public void invalidUserDetails() {
        // Set registration details
        Regtest.username = "Bo_l";
        Regtest.password = "Bonolo10";

        Login logintest = new Login(Regtest);

        assertFalse(logintest.checkUserDetails("wrongUser", "wrongPass")); // Fail-The-Test
    }
 

}
