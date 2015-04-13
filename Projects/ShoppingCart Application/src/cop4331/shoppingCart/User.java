/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cop4331.shoppingCart;

import java.util.ArrayList;

/**
 * A class for managing a user.
 *
 * @author Gregory Prosper
 */
public class User {

    /**
     * Constructor for the User class.
     */
    public User() {
        this.cart = new ArrayList<>();
    }

    /**
     * Constructor for the User class.
     *
     * @param id ID for the user.
     * @param name Name of the user.
     * @param typeOfUser Type of user.
     * @param address Address of the user.
     */
    public User(int id, String name, String typeOfUser, String address) {
        this.id = id;
        this.name = name;
        this.typeOfUser = typeOfUser;
        this.address = address;
        this.cart = new ArrayList<>();
    }

    /**
     * Sets the UserItems of the user.
     *
     * @param item An ArrayList of User Items.
     */
    public void setUserItems(ArrayList<UserItem> item) {
        this.userItems = item;
    }

    /**
     * Returns the ID of the user.
     *
     * @return The ID of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id The ID of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returns the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the type of the user.
     *
     * @return The type of the user.
     */
    public String getTypeOfUser() {
        return typeOfUser;
    }

    /**
     * Sets the type of the user.
     *
     * @param typeOfUser The type of user.
     */
    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }

    /**
     * Returns the address of the user.
     *
     * @return The address of the user.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the user.
     *
     * @param address The address of the user.
     */
    public void setAddress(String address) {
        this.address = address;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getRevenue() {
        return revenue;
    }

    public void setRevenue(float revenue) {
        this.revenue = revenue;
    }
    
    public void updateCost(int oldStock, int newStock, float invoicePrice){
        if (oldStock < newStock) {
            this.cost += (newStock - oldStock) * invoicePrice;
        }
    }

    /**
     * Adds CartItem to users cart.
     *
     * @param item The CartItem to be added to the cart.
     */
    public void addToCart(CartItem item) {
        CartItem.amountInCart.replace(item.getName(), CartItem.amountInCart.get(item.getName()) + 1);
        if (CartItem.amountInCart.get(item.getName()) < 2) {
            this.cart.add(item);
        }
    }

    /**
     * Remove CartItem from users cart.
     *
     * @param item The CartItem to be removed from cart.
     */
    public void removeFromCart(CartItem item) {
        int index = 0;
        for (CartItem i : this.cart) {
            if (i.getName().equals(item.getName())) {
                index = this.cart.indexOf(i);
            }
        }
        CartItem.amountInCart.remove(this.cart.get(index).getName());
        this.cart.remove(index);

    }

    /**
     * Returns the users cart.
     *
     * @return ArrayList of Cart Items.
     */
    public ArrayList<CartItem> getCart() {
        return (ArrayList<CartItem>) this.cart.clone();
    }

    /**
     * Returns the total sales price of the user's cart.
     *
     * @return total price sales price of user cart.
     */
    public String getCartTotal() {
        float total = 0;
        for (CartItem i : this.cart) {
            total += i.getSalesPrice() * CartItem.amountInCart.get(i.getName());
        }
        return String.format("$%.2f", total);
    }

    /**
     * Returns a list of user items.
     *
     * @return list of user items.
     */
    public ArrayList<UserItem> getUserItems() {
        return userItems;
    }

    /**
     * Add a User Item to User Items list.
     *
     * @param item User item to be added to user list.
     */
    public void addToUserItems(UserItem item) {
        this.userItems.add(item);
    }

    private int id;
    private String name;
    private String typeOfUser;
    private String address;
    float revenue;
    float cost;
    /**ArrayList that contains users cart items.*/
    private ArrayList<CartItem> cart;
    /**ArrayList that contains all user items.*/
    private ArrayList<UserItem> userItems;
}
