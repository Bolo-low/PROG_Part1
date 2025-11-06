package schoolproject;

import javax.swing.JOptionPane;

public class Registration {

    public String name;
    public String surname;
    public String username;
    public String password;
    public String cellphoneNumber;

    public void userInput() {
        // Name
        name = JOptionPane.showInputDialog("Please enter your name:");
        if (name == null) return;

        // Surname
        surname = JOptionPane.showInputDialog("Please enter your surname:");
        if (surname == null) return;

        JOptionPane.showMessageDialog(null,
                "WELCOME TO REGISTRATION " + name + " " + surname + "!!!");

        // Username
        do {
            username = JOptionPane.showInputDialog(
                    "Please enter your username:\n"
                    + "• Must contain an underscore\n"
                    + "• Must have at least 5 characters"
            );
            if (username == null) return;
        } while (!checkUsername(username));

        // Password
        do {
            password = JOptionPane.showInputDialog(
                    "Please enter your password:\n"
                    + "• At least 8 characters\n"
                    + "• At least one special character (!@#$%^&*()_-)\n"
                    + "• At least one digit\n"
                    + "• At least one uppercase letter"
            );
            if (password == null) return;
        } while (!checkPassword(password));

        // Cellphone number
        do {
            cellphoneNumber = JOptionPane.showInputDialog(
                    "Please enter your cellphone number:\n"
                    + "Format: +27XXXXXXXXX (12 characters total)"
            );
            if (cellphoneNumber == null) return;
        } while (!checkCellphoneNumber(cellphoneNumber));
    }

    public boolean checkUsername(String username) {
        if (username.contains("_") && username.length() >= 5) {
            JOptionPane.showMessageDialog(null, "Username successfully captured!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Your username is not correctly formatted.\nPlease try again.");
            return false;
        }
    }

    public boolean checkPassword(String password) {
        boolean hasMinLength = password.length() >= 8;
        boolean hasUppercase = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialCharacters = password.matches(".*[!@#$%^&*()_-].*");

        if (hasMinLength && hasDigit && hasUppercase && hasSpecialCharacters) {
            JOptionPane.showMessageDialog(null, "Password successfully captured!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Your password is not correctly formatted.\nPlease try again.");
            return false;
        }
    }

    public boolean checkCellphoneNumber(String cellphoneNumber) {
        boolean hasCountryCode = cellphoneNumber != null && cellphoneNumber.startsWith("+27");
        boolean hasMaxLength = cellphoneNumber != null && cellphoneNumber.length() == 12;
        boolean hasDigit = cellphoneNumber != null && cellphoneNumber.matches("\\+27\\d{9}");

        if (hasCountryCode && hasMaxLength && hasDigit) {
            JOptionPane.showMessageDialog(null, "Cellphone number successfully captured!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null,
                    "Your cellphone number is not correctly formatted.\n"
                    + "Make sure it starts with +27 and contains 9 digits.");
            return false;
        }
    }
}
