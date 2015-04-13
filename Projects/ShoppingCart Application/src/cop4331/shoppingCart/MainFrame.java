/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cop4331.shoppingCart;

import java.awt.CardLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 * Main frame for application
 *
 * @author Gregory Prosper
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        this.shoppingCartApp = new ShoppingCartApp();
        this.welcomePanel = new WelcomePanel();
        this.loginPanel = new LoginPanel();
        this.registerPanel = new RegisterPanel();
        this.buyerMainPanel = new BuyerMainPanel();
        this.sellerMainPanel = new SellerMainPanel();
        this.cartPanel = new CartPanel();
        this.confirmationPanel = new ConfirmationPanel();
        this.itemPanel = new ItemPanel();
        this.glassPane = new GlassPane();
        this.glassPane.setOpaque(false);
        getRootPane().setGlassPane(this.glassPane);

        // Welcome Panel - Buyer Button Functionality
        this.welcomePanel.getBuyerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) cardPanel.getLayout();
                layout.show(cardPanel, "LoginPanel");
                resetLoginPanel();
                shoppingCartApp.getUser().setTypeOfUser("Buyer");
            }
        });

        // Welcome Panel - Seller Button Functionality
        this.welcomePanel.getSellerButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) cardPanel.getLayout();
                layout.show(cardPanel, "LoginPanel");
                resetLoginPanel();
                shoppingCartApp.getUser().setTypeOfUser("Seller");
            }
        });

        // Register Panel - Register Button Functionality
        this.registerPanel.getRegisterButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String name = registerPanel.getFullName();
                String type = shoppingCartApp.getUser().getTypeOfUser();
                String address = registerPanel.getAddress();
                String username = registerPanel.getUsername();
                String password = new String(registerPanel.getPassword());

                getRootPane().getGlassPane().setVisible(true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (ShoppingCartApp.checkUser(username) != 0) {
                            getRootPane().getGlassPane().setVisible(false);
                            JOptionPane.showMessageDialog(cardPanel, "User already Exist");
                        } else {
                            boolean userCreated = ShoppingCartApp.createUser(name, type, address, username, password);
                            if (userCreated) {
                                getRootPane().getGlassPane().setVisible(false);
                                JOptionPane.showMessageDialog(cardPanel, "You have successfully Registered");
                                CardLayout layout = (CardLayout) cardPanel.getLayout();
                                layout.show(cardPanel, "LoginPanel");
                                registerPanel.clearFields();
                            }
                        }
                    }
                }).start();
            }

        });

        // Login Panel - Login Button Functionality
        this.loginPanel.getLoginButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String username = loginPanel.getUsername();
                String password = loginPanel.getPassword();
                String typeOfUser = shoppingCartApp.getUser().getTypeOfUser();

                getRootPane().getGlassPane().setVisible(true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (ShoppingCartApp.login(username, password, typeOfUser)) {
                            User temp = ShoppingCartApp.getUser(username);
                            shoppingCartApp.getUser().setId(temp.getId());
                            shoppingCartApp.getUser().setAddress(temp.getAddress());
                            shoppingCartApp.getUser().setName(temp.getName());
                            if ("Seller".equals(typeOfUser)) {
                                shoppingCartApp.getUser().setCost(temp.getCost());
                                shoppingCartApp.getUser().setRevenue(temp.getRevenue());
                            }
                            shoppingCartApp.resetInventory();

                            if ("Buyer".equals(typeOfUser)) {
                                resetBuyerMainPanel();
                                CardLayout layout = (CardLayout) cardPanel.getLayout();
                                layout.show(cardPanel, "BuyerMainPanel");
                            } else {
                                shoppingCartApp.getUser().setUserItems(ShoppingCartApp.getDBUserList(shoppingCartApp.getUser().getId()));
                                resetSellerMainPanel();
                                CardLayout layout = (CardLayout) cardPanel.getLayout();
                                layout.show(cardPanel, "SellerMainPanel");
                            }
                        } else {
                            getRootPane().getGlassPane().setVisible(false);
                            JOptionPane.showMessageDialog(cardPanel, "Invalid Username or Password");
                        }
                        getRootPane().getGlassPane().setVisible(false);
                    }
                }).start();

            }
        });

        // Buyer Panel - Add to cart Button Functionality
        this.buyerMainPanel.getAddToCartButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (buyerMainPanel.itemSelected()) {
                    InventoryItem inventoryItem = buyerMainPanel.getSelectedItem();
                    CartItem cartItem = new CartItem(inventoryItem.getName(), inventoryItem.getSalesPrice(), inventoryItem.getDescription());

                    if (InventoryItem.amountInStock.get(cartItem.getName()) > CartItem.amountInCart.get(cartItem.getName())) {
                        shoppingCartApp.getUser().addToCart(cartItem);

                        int cartCount = 0;
                        for (Map.Entry<String, Integer> entry : CartItem.amountInCart.entrySet()) {
                            cartCount += entry.getValue();
                        }
                        buyerMainPanel.getCartButton().setText(String.format("Cart (%d)", cartCount));
                    } else {
                        JOptionPane.showMessageDialog(cardPanel, "Maximum supply reached");
                    }
                }

            }
        });

        // Buyer Panel - Cart Button Functionality
        this.buyerMainPanel.getCartButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetCartPanel();
                CardLayout layout = (CardLayout) cardPanel.getLayout();
                layout.show(cardPanel, "CartPanel");
            }
        });

        // Buyer Panel - Logout Button Functionality
        this.buyerMainPanel.getLogutButton().addActionListener(new LogoutListener());

        // Seller Panel - Logout Button Functionality
        this.sellerMainPanel.getLogoutButton().addActionListener(new LogoutListener());

        // Seller Panel - Remove from inventory Button Functionality
        this.sellerMainPanel.getRemoveFromInventoryButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (sellerMainPanel.itemSelected()) {
                    getRootPane().getGlassPane().setVisible(true);

                    new Thread(new Runnable() {
                        @Override
                        public void run() {

                            UserItem item = sellerMainPanel.getSelectedItem();
                            ShoppingCartApp.deleteDBItem(item);
                            shoppingCartApp.getUser().setUserItems(ShoppingCartApp.getDBUserList(shoppingCartApp.getUser().getId()));
                            resetSellerMainPanel();
                            getRootPane().getGlassPane().setVisible(false);
                        }
                    }).start();
                }
            }
        });

        // Seller Panel - Modify item Button Functionality
        this.sellerMainPanel.getModifyItemButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (sellerMainPanel.itemSelected()) {
                    UserItem item = sellerMainPanel.getSelectedItem();
                    itemPanel.getTitleLabel().setText("Modify Item");
                    itemPanel.getNameTextField().setText(item.getName());
                    itemPanel.getNameTextField().setEnabled(false);
                    itemPanel.getInvoicePriceTextField().setText(Float.toString(item.getInvoicePrice()));
                    itemPanel.getSalesPriceTextField().setText(Float.toString(item.getSalesPrice()));
                    itemPanel.getStockSpinner().setValue(UserItem.amountInStock.get(item.getName()));
                    itemPanel.getDescriptionTextArea().setText(item.getDescription());

                    CardLayout layout = (CardLayout) cardPanel.getLayout();
                    layout.show(cardPanel, "ItemPanel");
                }
            }
        });

        // Seller Panel - Add item to inventory Button Functionality
        this.sellerMainPanel.getAddItemToInventory().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                itemPanel.getTitleLabel().setText("Add Item");
                itemPanel.getNameTextField().setEnabled(true);
                itemPanel.getNameTextField().setText("");
                itemPanel.getInvoicePriceTextField().setText("");
                itemPanel.getSalesPriceTextField().setText("");
                itemPanel.getStockSpinner().setValue(0);
                itemPanel.getDescriptionTextArea().setText("");
                CardLayout layout = (CardLayout) cardPanel.getLayout();
                layout.show(cardPanel, "ItemPanel");
            }
        });

        // Seller Panel - Reset Statistics Button Functionality
        this.sellerMainPanel.getResetButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                getRootPane().getGlassPane().setVisible(true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ShoppingCartApp.resetStatistics(shoppingCartApp.getUser().getId());
                        shoppingCartApp.getUser().setCost(0);
                        shoppingCartApp.getUser().setRevenue(0);
                        resetSellerMainPanel();
                        getRootPane().getGlassPane().setVisible(false);
                    }
                }).start();
            }
        });

        // Cart Panel - Remove from cart Button Functionality
        this.cartPanel.getRemoveFromCartButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cartPanel.itemSelected()) {
                    CartItem item = cartPanel.getSelectedItem();
                    shoppingCartApp.getUser().removeFromCart(item);
                    resetCartPanel();
                }
            }
        });

        // Cart Panel - Back Button Functionality
        this.cartPanel.getBackButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout layout = (CardLayout) cardPanel.getLayout();
                layout.show(cardPanel, "BuyerMainPanel");

                int cartCount = 0;
                for (Map.Entry<String, Integer> entry : CartItem.amountInCart.entrySet()) {
                    cartCount += entry.getValue();
                }
                if (cartCount == 0) {
                    resetBuyerMainPanel();
                } else {
                    resetBuyerMainPanel();
                    buyerMainPanel.getCartButton().setText(String.format("Cart (%d)", cartCount));
                }
            }
        });

        // Cart Panel - Subtract Button Functionality
        this.cartPanel.getSubtractButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cartPanel.itemSelected()) {
                    int selectedIndex = cartPanel.getCartList().getSelectedIndex();
                    CartItem item = cartPanel.getSelectedItem();
                    if (CartItem.amountInCart.get(item.getName()) > 1) {
                        CartItem.amountInCart.replace(item.getName(), CartItem.amountInCart.get(item.getName()) - 1);
                    } else {
                        shoppingCartApp.getUser().removeFromCart(item);
                    }
                    resetCartPanel();
                    cartPanel.getCartList().setSelectedIndex(selectedIndex);
                }
            }
        });

        // Cart Panel - Add Button Functionality
        this.cartPanel.getAddButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (cartPanel.itemSelected()) {
                    int selectedIndex = cartPanel.getCartList().getSelectedIndex();
                    CartItem item = cartPanel.getSelectedItem();
                    if (InventoryItem.amountInStock.get(item.getName()) > CartItem.amountInCart.get(item.getName())) {
                        CartItem.amountInCart.replace(item.getName(), CartItem.amountInCart.get(item.getName()) + 1);
                        resetCartPanel();
                        cartPanel.getCartList().setSelectedIndex(selectedIndex);
                    } else {
                        JOptionPane.showMessageDialog(cardPanel, "Maximum supply reached");
                    }
                }
            }
        });

        // Cart Panel - Checkout Button Functionality
        this.cartPanel.getCheckoutButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getRootPane().getGlassPane().setVisible(true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (shoppingCartApp.getUser().getCart().size() > 0) {
                            ShoppingCartApp.updateStock(shoppingCartApp.getUser().getCart());
                            getRootPane().getGlassPane().setVisible(false);
                            CardLayout layout = (CardLayout) cardPanel.getLayout();
                            layout.show(cardPanel, "ConfirmationPanel");
                            shoppingCartApp.logout();
                        } else {
                            getRootPane().getGlassPane().setVisible(false);
                            JOptionPane.showMessageDialog(cardPanel, "Add Items to Cart Before Checkout");
                        }
                    }
                }).start();
            }
        });

        // Confirmation Panel - Close Button Functionality
        this.confirmationPanel.getCloseButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                shoppingCartApp.logout();
                resetBuyerMainPanel();
                CardLayout layout = (CardLayout) cardPanel.getLayout();
                layout.show(cardPanel, "WelcomePanel");
            }
        });

        // Item Panel - Submit Button Functionality
        this.itemPanel.getSubmitButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                getRootPane().getGlassPane().setVisible(true);

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        //Get Current Stock
                        int currentStock = 0;
                        if (UserItem.amountInStock.containsKey(itemPanel.getNameTextField().getText())) {
                            currentStock = UserItem.amountInStock.get(itemPanel.getNameTextField().getText());
                        }

                        if (itemPanel.fieldsValid(currentStock)) {
                            String name = itemPanel.getNameTextField().getText();
                            float invoicePrice = Float.parseFloat(itemPanel.getInvoicePriceTextField().getText());
                            float salesPrice = Float.parseFloat(itemPanel.getSalesPriceTextField().getText());
                            int stock = (int) itemPanel.getStockSpinner().getValue();
                            String description = itemPanel.getDescriptionTextArea().getText();

                            if ("Modify Item".equals(itemPanel.getTitleLabel().getText())) {
                                ShoppingCartApp.modifyDBItem(name, invoicePrice, salesPrice, stock, description, shoppingCartApp.getUser().getName());
                                shoppingCartApp.getUser().updateCost(UserItem.amountInStock.get(name), stock, invoicePrice);
                                shoppingCartApp.resetUserInventory();
                                resetSellerMainPanel();
                                getRootPane().getGlassPane().setVisible(false);
                                CardLayout layout = (CardLayout) cardPanel.getLayout();
                                layout.show(cardPanel, "SellerMainPanel");

                            } else {
                                if (ShoppingCartApp.checkItem(name)) {
                                    getRootPane().getGlassPane().setVisible(false);
                                    JOptionPane.showMessageDialog(cardPanel, "Item already Exist");
                                } else {
                                    ShoppingCartApp.addDBItem(name, invoicePrice, salesPrice, shoppingCartApp.getUser().getId(), stock, description, shoppingCartApp.getUser().getName());
                                    shoppingCartApp.resetUserInventory();
                                    shoppingCartApp.getUser().updateCost(0, stock, invoicePrice);
                                    resetSellerMainPanel();
                                    getRootPane().getGlassPane().setVisible(false);
                                    CardLayout layout = (CardLayout) cardPanel.getLayout();
                                    layout.show(cardPanel, "SellerMainPanel");
                                }
                            }
                        } else {
                            getRootPane().getGlassPane().setVisible(false);
                            JOptionPane.showMessageDialog(cardPanel, "Enter valid information");
                        }
                    }
                }).start();
            }
        });

        // Item Panel - Cancel Button Functionality
        this.itemPanel.getCancelButton().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetItemPanel();
                CardLayout layout = (CardLayout) cardPanel.getLayout();
                layout.show(cardPanel, "SellerMainPanel");
            }
        });

        initComponents();
        this.cardPanel.add(this.welcomePanel, "WelcomePanel");
        this.cardPanel.add(this.loginPanel, "LoginPanel");
        this.cardPanel.add(this.registerPanel, "RegisterPanel");
        this.cardPanel.add(this.buyerMainPanel, "BuyerMainPanel");
        this.cardPanel.add(this.sellerMainPanel, "SellerMainPanel");
        this.cardPanel.add(this.cartPanel, "CartPanel");
        this.cardPanel.add(this.confirmationPanel, "ConfirmationPanel");
        this.cardPanel.add(this.itemPanel, "ItemPanel");

    }

    /**
     * Resets fields and labels of SellerMainPanel.
     */
    private void resetSellerMainPanel() {
        sellerMainPanel.getCostLabel().setText(String.format("$%.2f", shoppingCartApp.getUser().getCost()));
        sellerMainPanel.getRevenueLabel().setText(String.format("$%.2f", shoppingCartApp.getUser().getRevenue()));
        sellerMainPanel.getProfitLabel().setText(String.format("$%.2f", shoppingCartApp.getUser().getRevenue() - shoppingCartApp.getUser().getCost()));
        sellerMainPanel.getInventoryList().setModel(new javax.swing.AbstractListModel() {
            ArrayList<UserItem> inventory = shoppingCartApp.getUser().getUserItems();

            @Override
            public int getSize() {
                return inventory.size();
            }

            @Override
            public Object getElementAt(int i) {
                return inventory.get(i);
            }
        });
    }

    /**
     * Resets fields and labels of LoginPanel.
     */
    private void resetLoginPanel() {
        loginPanel.getUsernameField().setText("");
        loginPanel.getPasswordField().setText("");
    }

    /**
     * Resets fields and labels of BuyerMainPanel.
     */
    private void resetBuyerMainPanel() {
        buyerMainPanel.getCartButton().setText("Cart");
        buyerMainPanel.getItemDetailsTextArea().setText("");
        buyerMainPanel.getItemList().setModel(new javax.swing.AbstractListModel() {
            ArrayList<InventoryItem> inventory = shoppingCartApp.getInventory();

            @Override
            public int getSize() {
                return inventory.size();
            }

            @Override
            public Object getElementAt(int i) {
                return inventory.get(i);
            }
        });
    }

    /**
     * Resets fields and labels of CartPanel.
     */
    private void resetCartPanel() {
        cartPanel.getTotalLabel().setText(String.format("Total: %s", shoppingCartApp.getUser().getCartTotal()));
        cartPanel.getCartList().setModel(new javax.swing.AbstractListModel() {
            ArrayList<CartItem> cart = shoppingCartApp.getUser().getCart();

            @Override
            public int getSize() {
                return cart.size();
            }

            @Override
            public Object getElementAt(int i) {
                return cart.get(i);
            }
        });
    }

    /**
     * Resets fields and labels of ItemPanel.
     */
    private void resetItemPanel() {
        itemPanel.getNameTextField().setText("");
        itemPanel.getInvoicePriceTextField().setText("");
        itemPanel.getSalesPriceTextField().setText("");
        itemPanel.getStockSpinner().setValue(0);
        itemPanel.getDescriptionTextArea().setText("");
    }

    /**
     * Listener to implement logout button.
     */
    class LogoutListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            shoppingCartApp.logout();
            buyerMainPanel.getCartButton().setText("Cart");
            CardLayout layout = (CardLayout) cardPanel.getLayout();
            layout.show(cardPanel, "WelcomePanel");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Shopping Cart Application");
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width  - getSize().width) / 4, (Toolkit.getDefaultToolkit().getScreenSize().height - getSize().height) / 4);
        setResizable(false);

        cardPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    private ShoppingCartApp shoppingCartApp;
    private WelcomePanel welcomePanel;
    private LoginPanel loginPanel;
    private RegisterPanel registerPanel;
    private SellerMainPanel sellerMainPanel;
    private BuyerMainPanel buyerMainPanel;
    private CartPanel cartPanel;
    private ConfirmationPanel confirmationPanel;
    private GlassPane glassPane;
    private ItemPanel itemPanel;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardPanel;
    // End of variables declaration//GEN-END:variables
}
