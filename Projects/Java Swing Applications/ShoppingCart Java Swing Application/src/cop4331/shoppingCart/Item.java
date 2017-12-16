/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cop4331.shoppingCart;

/**
 * A class for managing Items.
 *
 * @author Gregory Prosper
 */
public abstract class Item {

    /**
     * Constructor for the Item class.
     *
     * @param name Name of the item.
     * @param salesPrice Sales price of the item.
     * @param description Description of the item.
     */
    public Item(String name, float salesPrice, String description) {
        this.name = name;
        this.salesPrice = salesPrice;
        this.description = description;
    }

    /**
     * Returns the name of the item.
     *
     * @return The name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the sales price of the item.
     *
     * @return The sales price of the item.
     */
    public float getSalesPrice() {
        return salesPrice;
    }

    /**
     * Returns the description of the item.
     *
     * @return The description of the item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Takes a string and left pads it.
     *
     * @param s String to be padded.
     * @param fill String to be used to pad string.
     * @param size Size of the string after padding.
     * @return The string that was passed in with desired padding.
     */
    public String padLeft(String s, String fill, int size) {
        while (s.length() < size) {
            s = fill + s;
        }
        return s;
    }

    /**
     * Takes a string and right pads it.
     *
     * @param s String to be padded.
     * @param fill String to be used to pad string.
     * @param size Size of the string after padding.
     * @return The string that was passed in with desired padding.
     */
    public String padRight(String s, String fill, int size) {
        while (s.length() < size) {
            s = s + fill;
        }
        return s;
    }

    private String name;
    private float salesPrice;
    private String description;
}
