import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CartGUI {

    public static void open(List<CartItem> cart, boolean hasMembership, List<String> selectedCategories) {
        JFrame frame = new JFrame("Your Cart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 400);
        frame.setLayout(new BorderLayout(10, 10));

        JLabel titleLabel = new JLabel("YOUR CART", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        frame.add(titleLabel, BorderLayout.NORTH);

        DefaultListModel<String> cartModel = new DefaultListModel<>();
        for (CartItem item : cart) {
            cartModel.addElement(item.toString());
        }

        JList<String> cartList = new JList<>(cartModel);
        frame.add(new JScrollPane(cartList), BorderLayout.CENTER);

        double totalPrice = 0.0;
        for (CartItem item : cart) {
            String[] itemParts = item.getItemName().split(" - RM ");
            if (itemParts.length > 1) {
                totalPrice += item.getQuantity() * Double.parseDouble(itemParts[1]);
            }
        }
        double discount = hasMembership ? totalPrice * 0.05 : 0;
        double finalPrice = totalPrice - discount;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));

        JButton backButton = new JButton("BACK");
        JButton buyButton = new JButton("BUY");
        JButton removeButton = new JButton("REMOVE ITEM");

        JLabel priceLabel = new JLabel(String.format("Total: RM %.2f | Discount: RM %.2f | Final: RM %.2f", totalPrice, discount, finalPrice));

        buttonPanel.add(backButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(priceLabel);
        buttonPanel.add(buyButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                ItemSelectionGUI.open(selectedCategories, hasMembership, cart);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = cartList.getSelectedIndex();
                if (selectedIndex != -1) {
                    cart.remove(selectedIndex);
                    cartModel.remove(selectedIndex);

                    double newTotalPrice = 0.0;
                    for (CartItem item : cart) {
                        String[] itemParts = item.getItemName().split(" - RM ");
                        if (itemParts.length > 1) {
                            newTotalPrice += item.getQuantity() * Double.parseDouble(itemParts[1]);
                        }
                    }
                    double newDiscount = hasMembership ? newTotalPrice * 0.05 : 0;
                    double newFinalPrice = newTotalPrice - newDiscount;

                    priceLabel.setText(String.format("Total: RM %.2f | Discount: RM %.2f | Final: RM %.2f", newTotalPrice, newDiscount, newFinalPrice));

                    JOptionPane.showMessageDialog(frame, "Item removed from cart.");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select an item to remove.");
                }
            }
        });

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int response = JOptionPane.showConfirmDialog(frame, "Do you want to continue shopping?", "Continue Shopping?", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(frame, "Thank you for shopping with Faeez Bakery!");
                    System.exit(0);
                }
            }
        });

        frame.setVisible(true);
    }
}