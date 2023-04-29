import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;

public class ComputerGuessingGame extends JFrame implements ActionListener {
    private JTextField textField;
    private JTextArea textArea;
    private JButton higherButton;
    private JButton lowerButton;
    private JButton correctButton;
    private int min = 1;
    private int max = 100;
    private int guess;
    private String playerName;

    public ComputerGuessingGame() {
        // Get player's name
        JFrame nameFrame = new JFrame("Enter Your Name");
        JPanel namePanel = new JPanel();
        JLabel nameLabel = new JLabel("Enter Your Name:");
        textField = new JTextField(20);
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                playerName = textField.getText();
                nameFrame.dispose();
                startGame();
            }
        });
        namePanel.add(nameLabel);
        namePanel.add(textField);
        namePanel.add(submitButton);
        nameFrame.add(namePanel);
        nameFrame.pack();
        nameFrame.setLocationRelativeTo(null);
        nameFrame.setVisible(true);
    }
    
    private void startGame() {
        setTitle("Computer Guessing Game");
            setLayout(new GridBagLayout());

            
            guess = (int) (Math.random() * 100) + 1;

            
            add(new JLabel("Is your number higher or lower than " + guess + "?"),
                createGridBagConstraints(0, 0, 3, 1, GridBagConstraints.CENTER));

            textArea = new JTextArea(10, 30);
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);
            add(scrollPane, createGridBagConstraints(0, 1, 3, 1, GridBagConstraints.CENTER));

            higherButton = new JButton("Higher");
            higherButton.addActionListener(this);
            add(higherButton, createGridBagConstraints(0, 2, 1, 1, GridBagConstraints.CENTER));

            lowerButton = new JButton("Lower");
            lowerButton.addActionListener(this);
            add(lowerButton, createGridBagConstraints(1, 2, 1, 1, GridBagConstraints.CENTER));

            correctButton = new JButton("Correct");
            correctButton.addActionListener(this);
            add(correctButton, createGridBagConstraints(2, 2, 1, 1, GridBagConstraints.CENTER));

            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
    }


    private GridBagConstraints createGridBagConstraints(int x, int y, int width, int height, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.anchor = anchor;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        return gbc;
    }

    public void actionPerformed(ActionEvent e) {
        try{
            if (e.getSource() == higherButton) {
                // User's number is higher, adjust range and guess again
                min = guess + 1;
                guess = (int) (Math.random() * (max - min + 1)) + min;
                textArea.append("Is your number higher or lower than " + guess + "?\n");
            } else if (e.getSource() == lowerButton) {
                // User's number is lower, adjust range and guess again
                max = guess - 1;
                guess = (int) (Math.random() * (max - min + 1)) + min;
                textArea.append("Is your number higher or lower than " + guess + "?\n");
            } else if (e.getSource() == correctButton) {
                // User's number was guessed correctly
                textArea.append("I am inevitable, " + playerName + "\n");
                higherButton.setEnabled(false);
                lowerButton.setEnabled(false);
                correctButton.setEnabled(false);
            }
        }
        catch(Exception exception){
            System.out.println("Error: "+exception);
        }
    }


    public static void main(String[] args) {
        new ComputerGuessingGame();
    }
}