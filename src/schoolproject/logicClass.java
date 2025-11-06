package schoolproject;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;

public class logicClass {

    private String messageId = "1234567891";
    private String recipient = "+27123456789";
    private String messageHash;
    private String randomText = "";
    private MessageManager manager;

    public logicClass(MessageManager manager) {
        this.manager = manager;
    }

    // Check message ID (must be 1–10 digits)
    public boolean checkMessageID(String id) {
        return id != null && id.matches("\\d{1,10}");
    }

    // Check South African number format
    public int checkRecipientCell(String cell) {
        if (cell != null && cell.matches("\\+27\\d{9}$")) {
            this.recipient = cell; // store valid number
            return 1;
        } else {
            return 0;
        }
    }

    // Generate message hash
    public String createMessageHash(String messageId, int messageNumber, String text) {
        this.messageId = messageId;
        this.randomText = text;

        String firstTwo = messageId.length() >= 2 ? messageId.substring(0, 2) : messageId;
        String[] words = text.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0].toUpperCase() : "";
        String lastWord = words.length > 0 ? words[words.length - 1].toUpperCase() : "";

        this.messageHash = firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
        return this.messageHash;
    }

    // Convert numeric choice into status
    public String sentMessage(String choice) {
        switch (choice) {
            case "1":
                return "sent";
            case "2":
                return "stored";
            case "3":
                return "disregarded";
            default:
                return "invalid";
        }
    }

    // Full send workflow that adds to manager and optionally saves to JSON
    public void handleSendMessage(String id, String hash, String cell, String text, String status) {
        // Add to MessageManager
        manager.addMessage(id, hash, cell, text, status);

        // Save to JSON only when status is 'sent' or 'stored' (as per requirements)
        if (status.equalsIgnoreCase("sent") || status.equalsIgnoreCase("stored")) {
            storeMessage(id, hash, cell, text, status);
        }

        JOptionPane.showMessageDialog(null, "Message Status: " + status);
    }

    // Save message to JSON file
    public void storeMessage(String id, String hash, String recipient, String text, String status) {
        JSONParser parser = new JSONParser();
        JSONArray messageList = new JSONArray();

        // Try to load existing messages from file
        try (FileReader reader = new FileReader("messages.json")) {
            Object obj = parser.parse(reader);
            if (obj instanceof JSONArray) {
                messageList = (JSONArray) obj;
            }
        } catch (Exception e) {
            // File might not exist yet — that's fine
        }

        JSONObject messageDetails = new JSONObject();
        messageDetails.put("Message ID", id);
        messageDetails.put("Message Hash", hash);
        messageDetails.put("Recipient", recipient);
        messageDetails.put("Message", text);
        messageDetails.put("Status", status);

        messageList.add(messageDetails);

        try (FileWriter file = new FileWriter("messages.json")) {
            file.write(messageList.toJSONString());
            file.flush();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error saving message: " + e.getMessage());
        }
    }
}
