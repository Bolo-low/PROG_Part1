package schoolproject;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SchoolProjectNGTest {

    private MessageManager mm;
    private Registration Regtest;
    private logicClass logicTest;

    @BeforeMethod
    public void setUp() {
        mm = new MessageManager(20);
        mm.loadTestData();

        Regtest = new Registration();
        logicTest = new logicClass(mm);
    }

    // USERNAME TESTS
    @Test
    public void validUsernameTest() {
        assertTrue(Regtest.checkUsername("Bo_lo"));
    }

    @Test
    public void usernameWithoutUnderscoreTest() {
        assertFalse(Regtest.checkUsername("Bonolo"));
    }

    @Test
    public void shortUsernameTest() {
        assertFalse(Regtest.checkUsername("Bon"));
    }

    // PASSWORD TESTS
    @Test
    public void validPasswordTest() {
        assertTrue(Regtest.checkPassword("Bonolo@10"));
    }

    @Test
    public void validPasswordLength() {
        assertFalse(Regtest.checkPassword("Bonolo"));
    }

    @Test
    public void PasswordWithoutDigital() {
        assertFalse(Regtest.checkPassword("Bonol@kgare"));
    }

    @Test
    public void PasswordWithoutUpperCase() {
        assertFalse(Regtest.checkPassword("bonol@kgare"));
    }

    // CELLPHONE TESTS
    @Test
    public void validCellphoneNumber() {
        assertTrue(Regtest.checkCellphoneNumber("+27671495415"));
    }

    @Test
    public void cellphoneNumberWithoutCountryCode() {
        assertFalse(Regtest.checkCellphoneNumber("0671495415"));
    }

    @Test
    public void cellphoneNumberLenght() {
        assertFalse(Regtest.checkCellphoneNumber("0671495415"));
    }

    // LOGIN TESTS
    @Test
    public void validUserDetails() {
        Regtest.username = "Bo_lo";
        Regtest.password = "Bonolo@10";

        Login loginTest = new Login(Regtest, mm, logicTest);
        assertTrue(loginTest.checkUserDetails("Bo_lo", "Bonolo@10"));
    }

    @Test
    public void invalidUserDetails() {
        Regtest.username = "Bo_l";
        Regtest.password = "Bonolo10";

        Login loginTest = new Login(Regtest, mm, logicTest);
        assertFalse(loginTest.checkUserDetails("wrongUser", "wrongPass"));
    }

    // LOGIC CLASS TESTS
    @Test
    public void validRecipientCell() {
        assertEquals(logicTest.checkRecipientCell("+27671495415"), 1);
    }

    @Test
    public void invalidRecipientCell() {
        assertEquals(logicTest.checkRecipientCell("0671495415"), 0);
    }

    @Test
    public void validMessageLength() {
        assertTrue("Hello world".length() <= 250);
    }

    @Test
    public void invalidMessageLength() {
        String longMsg = "x".repeat(251);
        assertFalse(longMsg.length() <= 250);
    }

    @Test
    public void validMessageID() {
        assertTrue(logicTest.checkMessageID("123456"));
    }

    @Test
    public void invalidMessageID() {
        assertFalse(logicTest.checkMessageID("123456789012"));
    }

    @Test
    public void createMessageHashTest() {
        String hash = logicTest.createMessageHash("12345", 1, "Hello user!");
        assertNotNull(hash);
        assertFalse(hash.isEmpty());
    }

    @Test
    public void testMessageStatusSent() {
        assertEquals(logicTest.sentMessage("1"), "sent");
    }

    @Test
    public void testMessageStatusStored() {
        assertEquals(logicTest.sentMessage("2"), "stored");
    }

    @Test
    public void testMessageStatusDisregarded() {
        assertEquals(logicTest.sentMessage("3"), "disregarded");
    }

    @Test
    public void testMessageStatusInvalid() {
        assertEquals(logicTest.sentMessage("9"),
