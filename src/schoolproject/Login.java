package schoolproject;

import javax.swing.JOptionPane;

public class Login {

    private Registration register;
    private MessageManager manager;
    private logicClass logic;
    private static int messagesSent = 0;

    public Login(Registration register, MessageManager manager, logicClass logic) {
        this.register = register;
        this.manager = manager;
        this.logic = logic;
    }

    public boolean log() {
        String loginUsername = JOptionPane.showInputDialog("Enter Username:");
        if (loginUsername == null) return false;

        String loginPassword = JOptionPane.showInputDialog("Enter Password:");
        if (loginPassword == null) return false;

        boolean success = checkUserDetails(loginUsername, loginPassword);

        if (!success) {
            JOptionPane.showMessageDialog(null, "Login failed. Please try again.");
            return false;
        }

        JOptionPane.showMessageDialog(null, "Welcome to TheChat!");
        messMenu(); // open messaging menu using shared manager & logic
        return true;
    }

    public boolean checkUserDetails(String username, String password) {
        if (register.username != null && register.password != null
                && username.equals(register.username)
                && password.equals(register.password)) {

            JOptionPane.showMessageDialog(null,
                    "Hello " + register.name + " " + register.surname + "! It is good to have you again.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Username or password is incorrect.");
            return false;
        }
    }

    // Non-recursive messaging menu
    private void messMenu() {
        int choice;
        do {
            String input = JOptionPane.showInputDialog(
                    "~~~~~Welcome to The Message Menu:~~~~~ \n"
                    + "1. Send message\n"
                    + "2. Show recent messages (from file)\n"
                    + "3. Display All Sent Messages\n"
                    + "4. Display Longest Message\n"
                    + "5. Display Message Report\n"
                    + "6. Load Sample Test Data\n"
                    + "7. Logout to Main Menu");
            if (input == null) return;
            if (!input.matches("[1-7]")) {
                JOptionPane.showMessageDialog(null, "Invalid option, try again.");
                continue;
            }
            choice = Integer.parseInt(input);

            switch (choice) {
                case 1:
                    String numStr = JOptionPane.showInputDialog("How many messages do you want to send?");
                    if (numStr == null) break;
                    int numberOfMess;
                    try {
                        numberOfMess = Integer.parseInt(numStr);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Invalid number.");
                        break;
                    }
                    messagesSent = numberOfMess;
                    for (int i = 0; i < numberOfMess; i++) {
                        String id = String.valueOf(1000000000 + new java.util.Random().nextInt(900000000));
                        String cell = JOptionPane.showInputDialog("Enter receiver's cell number (+27...)");
                        if (cell == null) break;
                        int validCell = logic.checkRecipientCell(cell);
                        if (validCell == 0) {
                            JOptionPane.showMessageDialog(null, "Recipient Number Invalid!");
                            continue;
                        }
                        String text = JOptionPane.showInputDialog("Enter your Message text:");
                        if (text == null) break;
                        String hash = logic.createMessageHash(id, i + 1, text);
                        JOptionPane.showMessageDialog(null, "Message Hash:\n" + hash);

                        // Ask user for final status choice (1-send,2-store,3-disregard)
                        String statusChoice = JOptionPane.showInputDialog(
                                "Choose options 1-3:\n1. Send Message\n2. Store Message\n3. Disregard Message");
                        if (statusChoice == null) statusChoice = "1";
                        String status = logic.sentMessage(statusChoice);
                        if (status.equals("invalid")) {
                            JOptionPane.showMessageDialog(null, "Invalid status choice; defaulting to 'sent'.");
                            status = "sent";
                        }

                        // Add & store according to status
                        logic.handleSendMessage(id, hash, cell, text, status);
                    }
                    break;
                case 2:
                    String nStr = JOptionPane.showInputDialog("How many recent messages to show? (e.g. 5)");
                    if (nStr == null) break;
                    int n;
                    try {
                        n = Integer.parseInt(nStr);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Invalid number.");
                        break;
                    }
                    manager.showRecentMessagesFromJson(n);
                    break;
                case 3:
                    manager.displayAllSentMessages();
                    break;
                case 4:
                    manager.displayLongestMessage();
                    break;
                case 5:
                    manager.displayReport();
                    break;
                case 6:
                    manager.loadTestData();
                    JOptionPane.showMessageDialog(null, "Sample data loaded.");
                    break;
                case 7:
                    JOptionPane.showMessageDialog(null, "Logging out to main menu...");
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option, try again.");
            }
        } while (true);
    }
}
