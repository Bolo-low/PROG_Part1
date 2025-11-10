package schoolproject;

import javax.swing.JOptionPane;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class MessageManager {

    // original arrays
    private String[] messagesID;
    private String[] messageHash;
    private String[] recipients;
    private String[] messagesTexts;
    private String[] messagesStatus;

    // assignment-required arrays
    private String[] sentMessages;
    private String[] disregardedMessages;
    private String[] storedMessages;
    private String[] messageHashes;
    private String[] messageIDs;

    // counters
    private int messageCount;
    private int sentCount;
    private int disregardedCount;
    private int storedCount;

    public MessageManager(int maxMessages) {
        messagesID = new String[maxMessages];
        messageHash = new String[maxMessages];
        recipients = new String[maxMessages];
        messagesTexts = new String[maxMessages];
        messagesStatus = new String[maxMessages];

        sentMessages = new String[maxMessages];
        disregardedMessages = new String[maxMessages];
        storedMessages = new String[maxMessages];
        messageHashes = new String[maxMessages];
        messageIDs = new String[maxMessages];

        messageCount = 0;
        sentCount = 0;
        disregardedCount = 0;
        storedCount = 0;
    }

    // Add message
    public void addMessage(String id, String hash, String recipient, String text, String status) {
        if (messageCount < messagesID.length) {
            messagesID[messageCount] = id;
            messageHash[messageCount] = hash;
            recipients[messageCount] = recipient;
            messagesTexts[messageCount] = text;
            messagesStatus[messageCount] = status;

            messageHashes[messageCount] = hash;
            messageIDs[messageCount] = id;

            if (status.equalsIgnoreCase("sent")) {
                sentMessages[sentCount++] = text;
            } else if (status.equalsIgnoreCase("disregarded")) {
                disregardedMessages[disregardedCount++] = text;
            } else if (status.equalsIgnoreCase("stored")) {
                storedMessages[storedCount++] = text;
            }

            messageCount++;
        } else {
            JOptionPane.showMessageDialog(null, "Message list full!");
        }
    }

    // Display all sent messages
    public void displayAllSentMessages() {
        if (sentCount == 0) {
            JOptionPane.showMessageDialog(null, "No sent messages to display.");
            return;
        }
        StringBuilder sb = new StringBuilder("Sent Messages:\n\n");
        for (int i = 0; i < sentCount; i++) {
            sb.append(sentMessages[i]).append("\n---------------------\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Return longest message among loaded messages
    public String getLongestMessage() {
        if (messageCount == 0) {
            return "";
        }
        String longest = "";
        for (int i = 0; i < messageCount; i++) {
            if (messagesTexts[i] != null && messagesTexts[i].length() > longest.length()) {
                longest = messagesTexts[i];
            }
        }
        return longest;
    }

    public void displayLongestMessage() {
        if (messageCount == 0) {
            JOptionPane.showMessageDialog(null, "No messages loaded.");
            return;
        }
        String longest = getLongestMessage();
        if (longest.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages available.");
        } else {
            JOptionPane.showMessageDialog(null, "Longest Message:\n\n" + longest);
        }
    }

    // Helper: load messages.json (returns empty JSONArray if file missing or parse error)
    private JSONArray loadMessagesFromFile() {
        JSONParser parser = new JSONParser();
        JSONArray messageList = new JSONArray();
        try (FileReader reader = new FileReader("messages.json")) {
            Object obj = parser.parse(reader);
            if (obj instanceof JSONArray) {
                messageList = (JSONArray) obj;
            }
        } catch (Exception e) {
            // file missing or parse error -> return empty list (no dialog here)
        }
        return messageList;
    }//end

    // Search by Message ID (reads from JSON file)
    public void searchByMessageID() {
        String idToSearch = JOptionPane.showInputDialog("Enter the Message ID to search for:");
        if (idToSearch == null || idToSearch.isEmpty()) {
            return;
        }

        JSONArray messages = loadMessagesFromFile();
        for (int i = 0; i < messages.size(); i++) {
            Object o = messages.get(i);
            if (!(o instanceof JSONObject)) {
                continue;
            }
            JSONObject msg = (JSONObject) o;
            Object mid = msg.get("Message ID");
            if (mid != null && idToSearch.equals(mid.toString())) {
                StringBuilder out = new StringBuilder();
                out.append("Message Found:\n")
                        .append("Message ID: ").append(msg.get("Message ID")).append("\n")
                        .append("Hash: ").append(msg.get("Message Hash")).append("\n")
                        .append("Recipient: ").append(msg.get("Recipient")).append("\n")
                        .append("Text: ").append(msg.get("Message")).append("\n")
                        .append("Status: ").append(msg.get("Status")).append("\n");
                JOptionPane.showMessageDialog(null, out.toString());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No message found with ID: " + idToSearch);
    }//end

    // Search by Message Hash
    // Search by Message Hash (reads from JSON file)
    public void searchByMessageHash() {
        String hashToSearch = JOptionPane.showInputDialog("Enter the message hash:");
        if (hashToSearch == null || hashToSearch.isEmpty()) {
            return;
        }

        JSONArray messages = loadMessagesFromFile();
        for (int i = 0; i < messages.size(); i++) {
            Object o = messages.get(i);
            if (!(o instanceof JSONObject)) {
                continue;
            }
            JSONObject msg = (JSONObject) o;
            Object h = msg.get("Message Hash");
            if (h != null && hashToSearch.equals(h.toString())) {
                StringBuilder out = new StringBuilder();
                out.append("Message Found:\n")
                        .append("Message ID: ").append(msg.get("Message ID")).append("\n")
                        .append("Recipient: ").append(msg.get("Recipient")).append("\n")
                        .append("Text: ").append(msg.get("Message")).append("\n")
                        .append("Status: ").append(msg.get("Status")).append("\n");
                JOptionPane.showMessageDialog(null, out.toString());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "No message found with hash: " + hashToSearch);
    }//end

    
    private void saveMessagesToFile(JSONArray messageList) {
    try (FileWriter file = new FileWriter("messages.json")) {
        file.write(messageList.toJSONString());
        file.flush();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error saving messages.json: " + e.getMessage());
    }
}
    
    // Delete by message hash
    public void deleteByMessageHash() {
        String hash = JOptionPane.showInputDialog("Enter the message hash to delete:");
        if (hash == null || hash.isEmpty()) {
            return;
        }

        // Remove from JSON file
        JSONArray messages = loadMessagesFromFile();
        boolean removedFromFile = false;
        for (int i = 0; i < messages.size(); i++) {
            Object o = messages.get(i);
            if (!(o instanceof JSONObject)) {
                continue;
            }
            JSONObject msg = (JSONObject) o;
            Object h = msg.get("Message Hash");
            if (h != null && hash.equals(h.toString())) {
                messages.remove(i);
                saveMessagesToFile(messages);
                removedFromFile = true;
                break;
            }
        }

        // Also remove from in-memory arrays if present (keeps internal state consistent)
        boolean removedFromMemory = false;
        for (int i = 0; i < messageCount; i++) {
            if (messageHash[i] != null && messageHash[i].equals(hash)) {
                // shift left
                for (int j = i; j < messageCount - 1; j++) {
                    messagesID[j] = messagesID[j + 1];
                    messageHash[j] = messageHash[j + 1];
                    recipients[j] = recipients[j + 1];
                    messagesTexts[j] = messagesTexts[j + 1];
                    messagesStatus[j] = messagesStatus[j + 1];
                }
                // clear last
                int last = messageCount - 1;
                messagesID[last] = null;
                messageHash[last] = null;
                recipients[last] = null;
                messagesTexts[last] = null;
                messagesStatus[last] = null;
                messageCount--;
                removedFromMemory = true;
                break;
            }
        }

        if (removedFromFile || removedFromMemory) {
            JOptionPane.showMessageDialog(null, "Message with hash \"" + hash + "\" successfully deleted.");
        } else {
            JOptionPane.showMessageDialog(null, "Message hash not found.");
        }
    }//end

    // Search by Recipient
    public void searchByRecipient() {
        String recipientToSearch = JOptionPane.showInputDialog("Enter the recipient number (+27...):");
        if (recipientToSearch == null || recipientToSearch.isEmpty()) {
            return;
        }

        JSONArray messages = loadMessagesFromFile();
        StringBuilder result = new StringBuilder("Messages sent to " + recipientToSearch + ":\n\n");
        boolean found = false;

        for (int i = 0; i < messages.size(); i++) {
            Object o = messages.get(i);
            if (!(o instanceof JSONObject)) {
                continue;
            }
            JSONObject msg = (JSONObject) o;
            Object r = msg.get("Recipient");
            if (r != null && recipientToSearch.equals(r.toString())) {
                result.append("Message ID: ").append(msg.get("Message ID")).append("\n")
                        .append("Hash: ").append(msg.get("Message Hash")).append("\n")
                        .append("Text: ").append(msg.get("Message")).append("\n")
                        .append("Status: ").append(msg.get("Status")).append("\n")
                        .append("-----------------------------\n");
                found = true;
            }
        }

        if (found) {
            JOptionPane.showMessageDialog(null, result.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No messages found for " + recipientToSearch);
        }
    }//end

    public void displayReport() {
        if (messageCount == 0) {
            JOptionPane.showMessageDialog(null, "No messages to display. Load sample data or send messages.");
            return;
        }

        StringBuilder sb = new StringBuilder();

        sb.append("=== SENT MESSAGES ===\n\n");
        for (int i = 0; i < messageCount; i++) {
            if ("sent".equalsIgnoreCase(messagesStatus[i])) {
                sb.append("Message ID: ").append(messagesID[i]).append("\n");
                sb.append("Message Hash: ").append(messageHash[i]).append("\n");
                sb.append("Recipient: ").append(recipients[i]).append("\n");
                sb.append("Message: ").append(messagesTexts[i]).append("\n");
                sb.append("--------------------------------\n");
            }
        }

        sb.append("\n=== STORED MESSAGES ===\n\n");
        for (int i = 0; i < messageCount; i++) {
            if ("stored".equalsIgnoreCase(messagesStatus[i])) {
                sb.append("Message ID: ").append(messagesID[i]).append("\n");
                sb.append("Message Hash: ").append(messageHash[i]).append("\n");
                sb.append("Recipient: ").append(recipients[i]).append("\n");
                sb.append("Message: ").append(messagesTexts[i]).append("\n");
                sb.append("--------------------------------\n");
            }
        }

        sb.append("\n=== DISREGARDED MESSAGES ===\n\n");
        for (int i = 0; i < messageCount; i++) {
            if ("disregarded".equalsIgnoreCase(messagesStatus[i])) {
                sb.append("Message ID: ").append(messagesID[i]).append("\n");
                sb.append("Message Hash: ").append(messageHash[i]).append("\n");
                sb.append("Recipient: ").append(recipients[i]).append("\n");
                sb.append("Message: ").append(messagesTexts[i]).append("\n");
                sb.append("--------------------------------\n");
            }
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    // Test data
    public void loadTestData() {
        addMessage("MESSAGE:1", "HASH1", "+27834557896", "Did you get the cake?", "sent");
        addMessage("MESSAGE:2", "HASH2", "+27838884567",
                "Where are you? You are late! I have asked you to be on time.", "stored");
        addMessage("MESSAGE:3", "HASH3", "+27834484567", "Yohoooo, I am at your gate.", "disregarded");
        addMessage("MESSAGE:4", "HASH4", "08388884567", "It is dinner time!", "sent");
        addMessage("MESSAGE:5", "HASH5", "+27838884567", "Ok, I am leaving without you.", "stored");
    }

    // Bonus: read recent messages from JSON file
    public void showRecentMessagesFromJson(int lastN) {
        JSONParser parser = new JSONParser();
        JSONArray messageList;
        try (FileReader reader = new FileReader("messages.json")) {
            Object obj = parser.parse(reader);
            if (obj instanceof JSONArray) {
                messageList = (JSONArray) obj;
            } else {
                JOptionPane.showMessageDialog(null, "No recent messages found.");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No messages found or error reading file.");
            return;
        }

        int total = messageList.size();
        if (total == 0) {
            JOptionPane.showMessageDialog(null, "No recent messages found.");
            return;
        }

        int start = Math.max(0, total - lastN);
        StringBuilder out = new StringBuilder("Recent Messages:\n\n");
        for (int i = start; i < total; i++) {
            JSONObject msg = (JSONObject) messageList.get(i);
            out.append("Message ID: ").append(msg.get("Message ID")).append("\n")
                    .append("Recipient: ").append(msg.get("Recipient")).append("\n")
                    .append("Message: ").append(msg.get("Message")).append("\n")
                    .append("Hash: ").append(msg.get("Message Hash")).append("\n")
                    .append("Status: ").append(msg.get("Status")).append("\n")
                    .append("--------------------------------\n");
        }
        JOptionPane.showMessageDialog(null, out.toString());
    }

}
