import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ChatBotApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ChatBotGUI::new);
    }

    // === Core ChatBot Logic ===
    static class ChatBot {
        private final Map<String, String> knowledgeBase;

        public ChatBot() {
            knowledgeBase = new HashMap<>();
            trainBot();
        }

        private void trainBot() {
            knowledgeBase.put("hi", "Hello! How can I assist you today?");
            knowledgeBase.put("hello", "Hi there! What can I help you with?");
            knowledgeBase.put("how are you", "I'm just a bunch of code, but I'm running fine!");
            knowledgeBase.put("what is your name", "I'm your friendly Java chatbot.");
            knowledgeBase.put("bye", "Goodbye! Have a great day.");
            knowledgeBase.put("help", "Sure! Ask me about the weather, time, or general questions.");
            knowledgeBase.put("what is java", "Java is a high-level, class-based, object-oriented programming language.");
            knowledgeBase.put("who created you", "I was created by a Java developer.");
            knowledgeBase.put("thanks", "You're welcome!");
        }

        public String getResponse(String input) {
            input = input.trim().toLowerCase();
            for (String key : knowledgeBase.keySet()) {
                if (input.contains(key)) {
                    return knowledgeBase.get(key);
                }
            }
            return "I'm sorry, I didn't understand that. Can you rephrase?";
        }
    }

    // === GUI ===
    static class ChatBotGUI extends JFrame {
        private final JTextArea chatArea;
        private final JTextField inputField;
        private final ChatBot bot;

        public ChatBotGUI() {
            bot = new ChatBot();

            setTitle("Java ChatBot");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(500, 500);
            setLayout(new BorderLayout());

            chatArea = new JTextArea();
            chatArea.setEditable(false);
            chatArea.setLineWrap(true);
            chatArea.setWrapStyleWord(true);
            JScrollPane scrollPane = new JScrollPane(chatArea);

            inputField = new JTextField();
            inputField.addActionListener(this::handleUserInput);

            add(scrollPane, BorderLayout.CENTER);
            add(inputField, BorderLayout.SOUTH);

            setVisible(true);
            greetUser();
        }

        private void greetUser() {
            appendMessage("Bot", "Hello! I'm your Java chatbot. Type something to start chatting.");
        }

        private void handleUserInput(ActionEvent e) {
            String userInput = inputField.getText();
            if (userInput.isEmpty()) return;

            appendMessage("You", userInput);
            String response = bot.getResponse(userInput);
            appendMessage("Bot", response);
            inputField.setText("");
        }

        private void appendMessage(String sender, String message) {
            chatArea.append(sender + ": " + message + "\n");
        }
    }
}
