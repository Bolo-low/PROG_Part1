package schoolproject;

import javax.swing.JOptionPane;

public class SchoolProject {

    public static void main(String[] args) {
        // Create shared objects
        Registration register = new Registration();
        MessageManager manager = new MessageManager(100);
        logicClass logic = new logicClass(manager);
        Login login = new Login(register, manager, logic);

        // Main menu loop (JOptionPane only)
        int choice = 0;
        do {
            String input = JOptionPane.showInputDialog(
                    "****************************************\n"
                    + "        ~~~~HELLO~~NEWBIE~~~~\n"
                    + "    Please enter your choice (1 to 6)\n"
                    + " 1. Register\n"
                    + " 2. Login\n"
                    + " 3. Load Sample Test Data\n"
                    + " 4. View Message Report\n"
                    + " 5. Display Longest Message\n"
                    + " 6. Cancel/Exit\n"
                    + "****************************************"
            );
            if (input == null) {
                choice = 6;
            } else if (!input.matches("[1-6]")) {
                JOptionPane.showMessageDialog(null, "Invalid option. Please select 1–6.");
                continue;
            } else {
                choice = Integer.parseInt(input);
            }

            switch (choice) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Opening Registration...");
                    register.userInput();
                    JOptionPane.showMessageDialog(null, "Registration successful! You may now login.");
                    break;

                case 2:
                    JOptionPane.showMessageDialog(null, "Opening Login...");
                    boolean ok = login.log(); // login will open messaging menu on success
                    if (!ok) {
                        // login.log already shows failure messages
                    }
                    break;
                //Load Sample Test Data automatically fills the system with 
                //messages so all message features can be tested without sending new messages.
                //pick this option before picking the the 4th or 5th option so the messages can load
                case 3:
                    int confirm = JOptionPane.showConfirmDialog(null,
                            "This will add sample messages to the system. Continue?",
                            "Load Sample Data",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        manager.loadTestData();
                        JOptionPane.showMessageDialog(null, "Sample data loaded.");
                    }
                    break;

                case 4:
                    manager.displayReport();
                    break;

                case 5:
                    manager.displayLongestMessage();
                    break;

                case 6:
                    JOptionPane.showMessageDialog(null, "Thank you. Goodbye!");
                    break;
            }

        } while (6 != choice);
    }
}
