package Online_Survey_System;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OnlineSurveySystem extends JFrame {
    private JLabel nameLabel, emailLabel, phoneLabel, ratingLabel, languageLabel, feedbackLabel, realTimeLabel;
    private JTextField nameField, emailField, phoneField;
    private JComboBox<String> languageDropDown;
    private JTextArea feedbackArea;
    private JSlider understandingRatingSlider, capabilityRatingSlider;
    private JButton submitButton;

    public OnlineSurveySystem() {
        showForm();
    }

    public void showForm() {
        // Initialize components
        nameLabel = new JLabel("Name:");
        emailLabel = new JLabel("Email-ID:");
        phoneLabel = new JLabel("Phone Number:");
        ratingLabel = new JLabel("Understanding about the language:");
        languageLabel = new JLabel("Capable to work with the languages:");
        feedbackLabel = new JLabel("Feedback:");
        realTimeLabel = new JLabel("Capable to solve real-time problems with the choice language:");

        // Set font for labels (bold and dark black color)
        Font boldFont = new Font(Font.SANS_SERIF, Font.BOLD, 15);
        Color darkBlack = new Color(0, 0, 0);
        nameLabel.setFont(boldFont);
        nameLabel.setForeground(darkBlack);
        emailLabel.setFont(boldFont);
        emailLabel.setForeground(darkBlack);
        phoneLabel.setFont(boldFont);
        phoneLabel.setForeground(darkBlack);
        ratingLabel.setFont(boldFont);
        ratingLabel.setForeground(darkBlack);
        languageLabel.setFont(boldFont);
        languageLabel.setForeground(darkBlack);
        feedbackLabel.setFont(boldFont);
        feedbackLabel.setForeground(darkBlack);
        realTimeLabel.setFont(boldFont);
        realTimeLabel.setForeground(darkBlack);
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        phoneField = new JTextField(20);

        String[] languages = {"Choose any one language","Java", "Python", "C++", "JavaScript", "Ruby", "Swift", "PHP", "C#", "Go", "Rust"};
        languageDropDown = new JComboBox<>(languages);
        languageDropDown.setSelectedIndex(0); 

        feedbackArea = new JTextArea(5, 30);

        understandingRatingSlider = new JSlider(1, 10); // Use a JSlider with range 1-10
        understandingRatingSlider.setMajorTickSpacing(1); // Set major tick spacing to 1
        understandingRatingSlider.setPaintTicks(true); // Display tick marks
        understandingRatingSlider.setPaintLabels(true); // Display labels on the tick marks

        capabilityRatingSlider = new JSlider(1, 10); // Use a JSlider with range 1-10
        capabilityRatingSlider.setMajorTickSpacing(1); // Set major tick spacing to 1
        capabilityRatingSlider.setPaintTicks(true); // Display tick marks
        capabilityRatingSlider.setPaintLabels(true); // Display labels on the tick marks

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitSurvey();
            }
        });

        // Set layout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(25, 25, 25, 25);

        // Set background color
        getContentPane().setBackground(Color.DARK_GRAY);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(nameLabel, gbc);
        gbc.gridx = 1;
        add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(emailLabel, gbc);
        gbc.gridx = 1;
        add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(phoneLabel, gbc);
        gbc.gridx = 1;
        add(phoneField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(ratingLabel, gbc);
        gbc.gridx = 1;
        add(understandingRatingSlider, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(languageLabel, gbc);
        gbc.gridx = 1;
        add(languageDropDown, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(realTimeLabel, gbc);
        gbc.gridx = 1;
        add(capabilityRatingSlider, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(feedbackLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        add(new JScrollPane(feedbackArea), gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        add(submitButton, gbc);

        // Set frame properties
        setTitle("Online Survey System");
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void submitSurvey() {
        String name = nameField.getText();
        String email = emailField.getText();
        String phone = phoneField.getText();
        String selectedLanguage = (String) languageDropDown.getSelectedItem();
        String feedback = feedbackArea.getText();

        int understandingRating = understandingRatingSlider.getValue();
        int capabilityRating = capabilityRatingSlider.getValue();

        // Check if any of the fields are blank
        if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || selectedLanguage.equals("Choose any one language") || feedback.isEmpty()) {
            StringBuilder errorMessage = new StringBuilder("You have to fill the following fields:");

            if (name.isEmpty()) {
                errorMessage.append("\n- Name");
                nameField.setBackground(Color.RED);
            } else {
                nameField.setBackground(Color.WHITE);
            }

            if (email.isEmpty()) {
                errorMessage.append("\n- Email-ID");
                emailField.setBackground(Color.RED);
            } else {
                emailField.setBackground(Color.WHITE);
            }

            if (phone.isEmpty()) {
                errorMessage.append("\n- Phone Number");
                phoneField.setBackground(Color.RED);
            } else if (!phone.matches("\\d+")) {
                errorMessage.append("\n- Phone Number should contain only numerical values");
                phoneField.setBackground(Color.RED);
            } else {
                phoneField.setBackground(Color.WHITE);
            }

            if (selectedLanguage.equals("Choose any one language")) {
                errorMessage.append("\n- Capable to work with the languages");
                languageDropDown.setBackground(Color.RED);
            } else {
                languageDropDown.setBackground(Color.WHITE);
            }

            if (feedback.isEmpty()) {
                errorMessage.append("\n- Feedback");
                feedbackArea.setBackground(Color.RED);
            } else {
                feedbackArea.setBackground(Color.WHITE);
            }

            JOptionPane.showMessageDialog(this,
                    errorMessage.toString(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);

            return; // Exit the method without submitting the survey
        }

        // Reset field backgrounds in case they were previously highlighted
        nameField.setBackground(Color.WHITE);
        emailField.setBackground(Color.WHITE);
        phoneField.setBackground(Color.WHITE);
        languageDropDown.setBackground(Color.WHITE);
        feedbackArea.setBackground(Color.WHITE);

        // Show a confirmation dialog with survey details
        JOptionPane.showMessageDialog(this,
                "Survey submitted successfully!\n\n" +
                        "Name: " + name + "\n" +
                        "Email: " + email + "\n" +
                        "Phone: " + phone + "\n" +
                        "Language: " + selectedLanguage + "\n" +
                        "Understanding Rating: " + understandingRating + "\n" +
                        "Capability Rating: " + capabilityRating + "\n" +
                        "Feedback: " + feedback,
                "Submission Confirmation",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OnlineSurveySystem();
            }
        });
    }
}
