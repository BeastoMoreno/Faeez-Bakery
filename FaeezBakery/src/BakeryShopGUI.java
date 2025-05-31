import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BakeryShopGUI {

    public static void open() {
        JFrame frame = new JFrame("Faeez Bakery");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout(10, 10));

        // Title Panel
        JPanel titlePanel = new JPanel();
        JLabel welcomeLabel = new JLabel("WELCOME TO FAEEZ BAKERY", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titlePanel.add(welcomeLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel emptyLabel1 = new JLabel();
        JLabel emptyLabel2 = new JLabel();
        JLabel emptyLabel3 = new JLabel();
        JLabel emptyLabel4 = new JLabel();

        JButton orderButton = new JButton("ORDER");
        JButton quitButton = new JButton("QUIT");

        buttonPanel.add(emptyLabel1);
        buttonPanel.add(emptyLabel2);
        buttonPanel.add(orderButton);
        buttonPanel.add(quitButton);
        buttonPanel.add(emptyLabel3);
        buttonPanel.add(emptyLabel4);
        frame.add(buttonPanel, BorderLayout.CENTER);

        // Footer Panel
        JPanel footerPanel = new JPanel();
        JLabel footerLabel = new JLabel("Enjoy the best bakery items in town!", SwingConstants.CENTER);
        footerLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        footerPanel.add(footerLabel);
        frame.add(footerPanel, BorderLayout.SOUTH);

        // Action Listeners
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Close the current window
                CategorySelectionGUI.open(); // Open the CategorySelectionGUI
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Exit the application
            }
        });

        frame.setVisible(true);
    }
}