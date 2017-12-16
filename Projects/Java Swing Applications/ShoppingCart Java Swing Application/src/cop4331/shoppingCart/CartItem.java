/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cop4331.shoppingCart;

import java.util.LinkedHashMap;

/**
 * A class for managing Cart Items.
 *
 * @author Gregory Prosper
 */
public class CartItem extends Item {

    /**
     * Constructor for the Cart Item class.
     *
     * @param name Name of the item.
     * @param salesPrice Sales price of the item.
     * @param description Description of the item.
     */
    public CartItem(String name, float salesPrice, String description) {
        super(name, salesPrice, description);

        if (!CartItem.amountInCart.containsKey(name)) {
            CartItem.amountInCart.put(name, 0);
        }
    }

    @Override
    public String toString() {
        return padRight(this.getName(), " ", 25) + padRight(String.format("$%.2f", this.getSalesPrice()), " ", 20) + padRight(String.format("%d", CartItem.amountInCart.get(this.getName())), " ", 10);
    }

    /**Static hash map that contains the name of the item and the amount in cart.*/
    public static LinkedHashMap<String, Integer> amountInCart = new LinkedHashMap<>();

}
