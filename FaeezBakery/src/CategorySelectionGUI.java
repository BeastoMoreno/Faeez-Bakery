import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CategorySelectionGUI {

    public static void open() {
        JFrame frame = new JFrame("Select a Category");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 350);
        frame.setLayout(new BorderLayout(10, 10));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(5, 1, 10, 10));

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(25);
        namePanel.add(nameLabel);
        namePanel.add(nameField);
        inputPanel.add(namePanel);

        JPanel membershipPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel membershipLabel = new JLabel("Do you have membership:");
        JRadioButton yesButton = new JRadioButton("Yes");
        JRadioButton noButton = new JRadioButton("No", true);
        ButtonGroup membershipGroup = new ButtonGroup();
        membershipGroup.add(yesButton);
        membershipGroup.add(noButton);
        membershipPanel.add(membershipLabel);
        membershipPanel.add(yesButton);
        membershipPanel.add(noButton);
        inputPanel.add(membershipPanel);

        JLabel selectItemsLabel = new JLabel("Select a items type:");
        selectItemsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        inputPanel.add(selectItemsLabel);

        JPanel categoryPanel = new JPanel(new GridLayout(1, 1));
        JCheckBox cakeCheckbox = new JCheckBox("Cake");
        JCheckBox breadCheckbox = new JCheckBox("Bread");
        JCheckBox cookieCheckbox = new JCheckBox("Cookies");
        categoryPanel.add(cakeCheckbox);
        categoryPanel.add(breadCheckbox);
        categoryPanel.add(cookieCheckbox);
        inputPanel.add(categoryPanel);

        frame.add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        JButton backButton = new JButton("BACK");
        JButton nextButton = new JButton("NEXT");
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                BakeryShopGUI.open();
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please enter your name.");
                    return;
                }

                List<String> selectedCategories = new ArrayList<>();
                if (cakeCheckbox.isSelected()) selectedCategories.add("Cake");
                if (breadCheckbox.isSelected()) selectedCategories.add("Bread");
                if (cookieCheckbox.isSelected()) selectedCategories.add("Cookie");

                if (selectedCategories.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please select at least one category.");
                    return;
                }

                frame.dispose();
                ItemSelectionGUI.open(selectedCategories, yesButton.isSelected(), new ArrayList<>());
            }
        });

        frame.setVisible(true);
    }
}