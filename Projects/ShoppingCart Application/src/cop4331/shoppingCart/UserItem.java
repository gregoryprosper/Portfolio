/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cop4331.shoppingCart;

/**
 * A class for managing User Items.
 *
 * @author Gregory Prosper
 */
public class UserItem extends InventoryItem {

    /**
     * Constructor for the User Item class.
     *
     * @param name Name of the item.
     * @param invoicePrice Invoice price of the item.
     * @param salesPrice Sales price of the item.
     * @param description Description of the item.
     */
    public UserItem(String name, float invoicePrice, float salesPrice, String description) {
        super(name, invoicePrice, salesPrice, description);
    }

    @Override
    public String toString() {
        return padRight(this.getName(), " ", 20) + padRight(String.format("$%.2f", this.getInvoicePrice()), " ", 20)
                + padRight(String.format("$%.2f", this.getSalesPrice()), " ", 20)
                + padRight(String.format("x%d", InventoryItem.amountInStock.get(this.getName())), " ", 6);
    }

}
