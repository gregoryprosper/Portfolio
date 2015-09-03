/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cop4331.shoppingCart;

import java.util.LinkedHashMap;

/**
 * A class for managing Inventory Items.
 *
 * @author Gregory Prosper
 */
public class InventoryItem extends Item {

    /**
     * Constructor for the Inventory Item class.
     *
     * @param name Name of the item.
     * @param invoicePrice Invoice price of the item.
     * @param salesPrice Sales price of the item.
     * @param description Description of the item.
     */
    public InventoryItem(String name, float invoicePrice, float salesPrice, String description) {
        super(name, salesPrice, description);
        this.invoicePrice = invoicePrice;
        if (InventoryItem.amountInStock.containsKey(name)) {
            InventoryItem.amountInStock.replace(name, InventoryItem.amountInStock.get(name) + 1);
        }
        InventoryItem.amountInStock.put(name, 1);
    }

    @Override
    public String toString() {
        return padRight(this.getName(), " ", 50) + padRight(String.format("$%.2f", this.getSalesPrice()), " ", 10);
    }

    /**
     * Returns the invoice price of the item.
     *
     * @return The invoice price of the item.
     */
    public float getInvoicePrice() {
        return invoicePrice;
    }

    private float invoicePrice;
    /**Static hash map that contains the name of the item and the amount in stock.*/
    public static LinkedHashMap<String, Integer> amountInStock = new LinkedHashMap<>();

}
