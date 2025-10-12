package schoolproject;

import javax.swing.JOptionPane;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileReader;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class logicClass {

    private String messageId = "1234567891";
    private String recipient = "+27123456789"; //
    private String messageHash;
    private int totalMessage = 0;
    private String randomText = "";

    // Check message ID (must be 1–10 digits)
    public boolean checkMessageID(String id) {
        return id.matches("\\d{1,10}");
    }

    //Check South African number format
    public int checkRecipientCell(String cell) {
        if (cell.matches("\\+27\\d{9}$")) {
            this.recipient = cell; // store valid number
            return 1;
        } else {
            return 0;
        }
    } //end of checking cell no of recipient

    // Generate message hash
    public String createMessageHash(String messageId, int messageNumber, String text) {
        this.messageId = messageId;
        this.randomText = text;

        String firstTwo = messageId.substring(0, 2);
        String[] words = text.trim().split("\\s+");
        String firstWord = words[0].toUpperCase();
        String lastWord = words[words.length - 1].toUpperCase();

        this.messageHash = firstTwo + ":" + messageNumber + ":" + firstWord + lastWord;
        return this.messageHash;
    }//end of hash message 

    // Handle message sending options --My test code would not run with J option 
//so I worked around it so that the code can run the unit tests 
    public String sentMessage(String choice) {
        switch (choice) {
            case "1":
                return "sent";
            case "2":
                return "stored";
            case "0":
                return "disregarded";
            default:
                return "invalid";
        }
    }
    
    // This method uses JOptionPane to collect user input and display the result
public void handleSendMessage() {
    String choice = JOptionPane.showInputDialog(null,
        "Choose options 0-2:\n" +
        "1. Send Message\n" +
        "2. Store Message\n" +
        "0. Disregard Message");

    String result = sentMessage(choice);
    JOptionPane.showMessageDialog(null, "Message Status: " + result);
}


    //This is the joption logic that is meant to be for the sent message method
    public void handleUserMessage() {
        String choice = JOptionPane.showInputDialog("1. Send\n2. Store\n0. Disregard");
        String result = sentMessage(choice);
        JOptionPane.showMessageDialog(null, "Message status: " + result);
    }

    // Save message to JSON file
    public void storeMessage() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("message", printMessage()); 
        try (FileWriter file = new FileWriter("messages.json")) {
            file.write(jsonObject.toJSONString()); 
            System.out.println("Message saved to messages.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    } //end json file

    // Display message info
    public String printMessage() {
        return "Message ID: " + messageId
                + "\nRecipient: " + recipient
                + "\nMessage: " + randomText
                + "\nMessage Hash: " + messageHash
                + "\nTotal Messages Sent: " + totalMessage;
    }//end print message
}
