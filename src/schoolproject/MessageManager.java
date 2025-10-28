/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package schoolproject;
import javax.swing.JOptionPane;
/**
 *
 * @author letso
 */
public class MessageManager {
     private String[] messagesID;
    private String[] messageHash;
    private String[] recipients;
    private String[] messagesTexts;
    private String[] messagesStatus;
    private int messageCount;

    public MessageManager(int maxMessages) {
        messagesID = new String[maxMessages];
        messageHash = new String[maxMessages];
        recipients = new String[maxMessages];
        messagesTexts = new String[maxMessages];
        messagesStatus = new String[maxMessages];
        messageCount = 0;
    }

    // Add message
    public void addMessage(String id, String hash, String recipient, String text, String status) {
        if (messageCount < messagesID.length) {
            messagesID[messageCount] = id;
            messageHash[messageCount] = hash;
            recipients[messageCount] = recipient;
            messagesTexts[messageCount] = text;
            messagesStatus[messageCount] = status;
            messageCount++;
        } else {
            JOptionPane.showMessageDialog(null, "Message list full!");
        }
    }

    // Search by Message ID
    public void searchByMessageID() {
        String idToSearch = JOptionPane.showInputDialog("Enter the Message ID to search for:");
        if (idToSearch == null || idToSearch.isEmpty()) return;

        for (int i = 0; i < messageCount; i++) {
            if (idToSearch.equals(messagesID[i])) {
                JOptionPane.showMessageDialog(null, "Message Found:\n"
                        + "Message ID: " + messagesID[i] + "\n"
                        + "Hash: " + messageHash[i] + "\n"
                        + "Recipient: " + recipients[i] + "\n"
                        + "Text: " + messagesTexts[i] + "\n"
                        + "Status: " + messagesStatus[i]);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No message found with ID: " + idToSearch);
    }

    // Search by Message Hash
    public void searchByMessageHash() {
        String hashToSearch = JOptionPane.showInputDialog("Enter the message hash:");
        if (hashToSearch == null || hashToSearch.isEmpty()) return;

        for (int i = 0; i < messageCount; i++) {
            if (hashToSearch.equals(messageHash[i])) {
                JOptionPane.showMessageDialog(null, "Message Found:\n"
                        + "Message ID: " + messagesID[i] + "\n"
                        + "Recipient: " + recipients[i] + "\n"
                        + "Text: " + messagesTexts[i] + "\n"
                        + "Status: " + messagesStatus[i]);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No message found with hash: " + hashToSearch);
    }

    // Search by Recipient
    public void searchByRecipient() {
        String recipientToSearch = JOptionPane.showInputDialog("Enter the recipient number (+27...):");
        if (recipientToSearch == null || recipientToSearch.isEmpty()) return;

        StringBuilder result = new StringBuilder("Messages sent to " + recipientToSearch + ":\n\n");
        boolean found = false;

        for (int i = 0; i < messageCount; i++) {
            if (recipientToSearch.equals(recipients[i])) {
                result.append("Message ID: ").append(messagesID[i]).append("\n")
                        .append("Hash: ").append(messageHash[i]).append("\n")
                        .append("Text: ").append(messagesTexts[i]).append("\n")
                        .append("Status: ").append(messagesStatus[i]).append("\n")
                        .append("-----------------------------\n");
                found = true;
            }
        }

        if (found) JOptionPane.showMessageDialog(null, result.toString());
        else JOptionPane.showMessageDialog(null, "No messages found for " + recipientToSearch);
    }

    // Delete by Message ID
    public void deleteByMessageID() {
        String idToDelete = JOptionPane.showInputDialog("Enter the Message ID to delete:");
        if (idToDelete == null || idToDelete.isEmpty()) return;

        for (int i = 0; i < messageCount; i++) {
            if (idToDelete.equals(messagesID[i])) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Delete message for recipient: " + recipients[i] + " ?",
                        "Confirm Deletion", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    for (int j = i; j < messageCount - 1; j++) {
                        messagesID[j] = messagesID[j + 1];
                        messageHash[j] = messageHash[j + 1];
                        recipients[j] = recipients[j + 1];
                        messagesTexts[j] = messagesTexts[j + 1];
                        messagesStatus[j] = messagesStatus[j + 1];
                    }
                    messageCount--;
                    JOptionPane.showMessageDialog(null, "Message deleted successfully.");
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Message ID not found.");
    }
    
}
