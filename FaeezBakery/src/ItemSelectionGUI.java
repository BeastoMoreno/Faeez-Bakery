import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ItemSelectionGUI {

    private static List<CartItem> cart;

    public static void open(List<String> selectedCategories, boolean hasMembership, List<CartItem> existingCart) {
        cart = existingCart;

        JFrame frame = new JFrame("Select Items");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout(10, 10));

        // Title Panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(255, 223, 186)); // Light orange background
        JLabel titleLabel = new JLabel("SELECT ITEMS AND QUANTITY", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(139, 69, 19)); // Brown color
        titlePanel.add(titleLabel);
        frame.add(titlePanel, BorderLayout.NORTH);

        // Main Selection Panel
        JPanel selectionPanel = new JPanel();
        selectionPanel.setLayout(new GridBagLayout());
        selectionPanel.setBackground(new Color(255, 248, 220)); // Light yellow background
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Item Selection
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel itemLabel = new JLabel("Item:");
        itemLabel.setFont(new Font("Arial", Font.BOLD, 16));
        selectionPanel.add(itemLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JComboBox<String> itemComboBox = new JComboBox<>();
        itemComboBox.setPreferredSize(new Dimension(200, 30));
        populateItems(selectedCategories, itemComboBox);
        selectionPanel.add(itemComboBox, gbc);

        // Quantity Selection
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 16));
        selectionPanel.add(quantityLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        JPanel quantityPanel = new JPanel();
        quantityPanel.setPreferredSize(new Dimension(250, 150));
        quantityPanel.setBorder(BorderFactory.createTitledBorder("Select Quantity"));
        quantityPanel.setLayout(new GridLayout(3, 3, 5, 5));
        quantityPanel.setBackground(new Color(255, 248, 220)); // Light yellow background

        ButtonGroup quantityGroup = new ButtonGroup();
        JRadioButton[] quantityButtons = new JRadioButton[9];
        for (int i = 0; i < 9; i++) {
            quantityButtons[i] = new JRadioButton(String.valueOf(i + 1));
            quantityButtons[i].setFont(new Font("Arial", Font.PLAIN, 14));
            quantityGroup.add(quantityButtons[i]);
            quantityPanel.add(quantityButtons[i]);
        }
        quantityButtons[0].setSelected(true);
        selectionPanel.add(quantityPanel, gbc);

        frame.add(selectionPanel, BorderLayout.CENTER);

        // Action Panel
        JPanel actionPanel = new JPanel();
        actionPanel.setBackground(new Color(255, 223, 186)); // Light orange background
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));

        JButton backButton = new JButton("BACK");
        styleButton(backButton, new Color(139, 69, 19), Color.WHITE); // Brown button with white text
        JButton addToCartButton = new JButton("ADD TO CART");
        styleButton(addToCartButton, new Color(0, 100, 0), Color.WHITE); // Dark green button with white text
        JButton checkoutButton = new JButton("CHECKOUT");
        styleButton(checkoutButton, new Color(139, 69, 19), Color.WHITE); // Brown button with white text

        actionPanel.add(backButton);
        actionPanel.add(addToCartButton);
        actionPanel.add(checkoutButton);

        frame.add(actionPanel, BorderLayout.SOUTH);

        // Action Listeners
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ItemSelectionGUI.open(selectedCategories, hasMembership, cart);
            }
        });

        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) itemComboBox.getSelectedItem();
                int selectedQuantity = 1;
                for (int i = 0; i < 9; i++) {
                    if (quantityButtons[i].isSelected()) {
                        selectedQuantity = i + 1;
                        break;
                    }
                }

                if (selectedItem == null || selectedItem.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Please select a valid item.");
                    return;
                }

                boolean itemExists = false;
                for (CartItem cartItem : cart) {
                    if (cartItem.getItemName().equals(selectedItem)) {
                        cartItem.addQuantity(selectedQuantity);
                        itemExists = true;
                        break;
                    }
                }

                if (!itemExists) {
                    cart.add(new CartItem(selectedItem, selectedQuantity));
                }

                JOptionPane.showMessageDialog(frame, "Item added to cart: " + selectedQuantity + " x " + selectedItem);
            }
        });

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cart.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Your cart is empty. Please add items before checking out.");
                } else {
                    frame.dispose();
                    CartGUI.open(cart, hasMembership, selectedCategories);
                }
            }
        });

        frame.setVisible(true);
    }

    private static void populateItems(List<String> selectedCategories, JComboBox<String> itemComboBox) {
        itemComboBox.removeAllItems();
        for (String category : selectedCategories) {
            if (category.equals("Cakes")) {
                itemComboBox.addItem("Pandan Layer Cake - RM 25.00");
                itemComboBox.addItem("Durian Cream Cake - RM 30.00");
                itemComboBox.addItem("Kek Lapis Sarawak (Sarawak Layer Cake) - RM 35.00");
                itemComboBox.addItem("Chocolate Moist Cake - RM 20.00");
                itemComboBox.addItem("Mango Cheesecake - RM 22.00");
            } else if (category.equals("Bread")) {
                itemComboBox.addItem("Roti Bun (Butter Bun) - RM 3.50");
                itemComboBox.addItem("Pandan Coconut Bun - RM 4.00");
                itemComboBox.addItem("Garlic Cheese Bread - RM 5.00");
                itemComboBox.addItem("Wholemeal Bread - RM 7.00");
                itemComboBox.addItem("Charcoal Bun - RM 4.50");
            } else if (category.equals("Pastries")) {
                itemComboBox.addItem("Curry Puff - RM 2.50");
                itemComboBox.addItem("Sambal Sardine Puff - RM 3.00");
                itemComboBox.addItem("Egg Tart - RM 2.00");
                itemComboBox.addItem("Pineapple Tart - RM 3.50");
                itemComboBox.addItem("Kuih Pie Tee - RM 4.00");
            } else if (category.equals("Cookies")) {
                itemComboBox.addItem("Kuih Bangkit (Tapioca Cookies) - RM 10.00/pack");
                itemComboBox.addItem("Honey Cornflakes - RM 8.00/pack");
                itemComboBox.addItem("Almond London Cookies - RM 12.00/pack");
                itemComboBox.addItem("Pandan Cookies - RM 9.00/pack");
                itemComboBox.addItem("Butter Cookies - RM 7.00/pack");
            } else if (category.equals("Traditional Kuih")) {
                itemComboBox.addItem("Kuih Lapis (Steamed Layer Cake) - RM 2.50/piece");
                itemComboBox.addItem("Kuih Seri Muka (Pandan & Coconut) - RM 3.00/piece");
                itemComboBox.addItem("Kuih Dadar (Coconut Pancake) - RM 2.00/piece");
                itemComboBox.addItem("Kuih Talam (Glutinous Rice Cake) - RM 2.50/piece");
                itemComboBox.addItem("Ondeh-Ondeh (Pandan & Gula Melaka) - RM 3.00/piece");
            } else if (category.equals("Drinks")) {
                itemComboBox.addItem("Teh Tarik (Pulled Tea) - RM 4.00");
                itemComboBox.addItem("Kopi O (Black Coffee) - RM 3.50");
                itemComboBox.addItem("Pandan Latte - RM 5.00");
                itemComboBox.addItem("Cendol Smoothie - RM 6.00");
                itemComboBox.addItem("Milo Dinosaur - RM 5.50");
            }
        }
    }

    private static void styleButton(JButton button, Color bgColor, Color textColor) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(textColor);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }
}