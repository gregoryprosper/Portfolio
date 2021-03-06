/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cop4331.shoppingCart;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 * Cart Panel
 *
 * @author Gregory Prosper
 */
public class CartPanel extends javax.swing.JPanel {

    /**
     * Creates new form CartPanel
     */
    public CartPanel() {
        initComponents();
    }

    /**
     * Returns JList of items in cart.
     *
     * @return JList of items.
     */
    public JList getCartList() {
        return cartList;
    }

    /**
     * Checks to see if item is selected.
     *
     * @return true is item is selected.
     */
    public boolean itemSelected() {
        return !this.cartList.isSelectionEmpty();
    }

    /**
     * Returns Remove from Cart Button.
     *
     * @return Remove from Cart Button.
     */
    public JButton getRemoveFromCartButton() {
        return removeFromCartButton;
    }

    /**
     * Returns Back Button.
     *
     * @return Back Button.
     */
    public JButton getBackButton() {
        return backButton;
    }

    /**
     * Returns Subtract Button.
     *
     * @return Subtract Button.
     */
    public JButton getSubtractButton() {
        return subtractButton;
    }

    /**
     * Returns Add Button.
     *
     * @return Add Button.
     */
    public JButton getAddButton() {
        return addButton;
    }

    /**
     * Returns Checkout Button.
     *
     * @return Checkout Button.
     */
    public JButton getCheckoutButton() {
        return checkoutButton;
    }

    /**
     * Returns Total Label.
     *
     * @return Total Label.
     */
    public JLabel getTotalLabel() {
        return totalLabel;
    }

    /**
     * Returns selected item from JList on SellerMainPanel.
     *
     * @return CartItem.
     */
    public CartItem getSelectedItem() {
        ArrayList<CartItem> item = (ArrayList<CartItem>) this.cartList.getSelectedValuesList();
        CartItem chosenItem = item.get(0);
        return chosenItem;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        cartList = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        removeFromCartButton = new javax.swing.JButton();
        checkoutButton = new javax.swing.JButton();
        subtractButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        totalLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(789, 588));

        jLabel1.setText("Item Name");

        jLabel2.setText("Price");

        cartList.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jScrollPane1.setViewportView(cartList);

        jLabel3.setText("Amount In Cart");

        removeFromCartButton.setText("Remove From Cart");

        checkoutButton.setText("Checkout");

        subtractButton.setText("-");

        addButton.setText("+");

        backButton.setText("Back");

        totalLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        totalLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalLabel.setText("Total:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/cop4331/shoppingCart/shoppingCart.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel1)
                                .addGap(113, 113, 113)
                                .addComponent(jLabel2)
                                .addGap(109, 109, 109)
                                .addComponent(jLabel3))
                            .addComponent(totalLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(checkoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(removeFromCartButton, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(subtractButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(subtractButton)
                            .addComponent(addButton))
                        .addGap(18, 18, 18)
                        .addComponent(removeFromCartButton)
                        .addGap(18, 18, 18)
                        .addComponent(checkoutButton)
                        .addGap(91, 91, 91)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(totalLabel)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JList cartList;
    private javax.swing.JButton checkoutButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton removeFromCartButton;
    private javax.swing.JButton subtractButton;
    private javax.swing.JLabel totalLabel;
    // End of variables declaration//GEN-END:variables
}
