/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cop4331.shoppingCart;

import java.sql.*;
import java.util.ArrayList;

/**
 * A class for managing the Shopping Cart Application.
 *
 * @author Gregory Prosper
 */
public class ShoppingCartApp {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://sql3.freemysqlhosting.net/sql373139";

    // Database credentials
    static final String USER = "sql373139";
    static final String PASS = "yX9*aP8*";

    /**
     * Constructor for the User class.
     */
    public ShoppingCartApp() {
        this.user = new User();
    }

    /**
     * Logs out the user.
     */
    public void logout() {
        this.user = new User();
        CartItem.amountInCart.clear();
    }

    /**
     * Returns the user.
     *
     * @return User.
     */
    public User getUser() {
        return this.user;
    }

    /**
     * Resets the inventory.
     */
    public void resetInventory() {
        this.inventory = ShoppingCartApp.getDBInventory();
    }

    /**
     * Resets user inventory.
     */
    public void resetUserInventory() {
        this.user.setUserItems(ShoppingCartApp.getDBUserList(this.user.getId()));
    }

    /**
     * Returns the inventory.
     *
     * @return ArrayList of Inventory Items.
     */
    public ArrayList<InventoryItem> getInventory() {
        return (ArrayList<InventoryItem>) inventory.clone();
    }

    /**
     * Gets the inventory from the database.
     *
     * @return ArrayList of Inventory Items.
     */
    public static ArrayList<InventoryItem> getDBInventory() {
        Connection conn = null;
        Statement stmt = null;
        ArrayList<InventoryItem> inventory = new ArrayList<>();
        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: Execute a query
            System.out.println("Execute getDBInventory");
            stmt = conn.createStatement();
            String query = "SELECT * FROM items WHERE inStock > 0";
            ResultSet rs = stmt.executeQuery(query);

            // STEP 5: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                InventoryItem item = new InventoryItem(rs.getString("name"), rs.getFloat("invoicePrice"), rs.getFloat("salesPrice"), rs.getString("description"));
                InventoryItem.amountInStock.put(rs.getString("name"), rs.getInt("inStock"));
                inventory.add(item);
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }// end finally try
            return inventory;
        }

    }

    /**
     * Gets the User List of a specific user.
     *
     * @param id ID of user.
     * @return ArrayList of User Items.
     */
    public static ArrayList<UserItem> getDBUserList(int id) {
        Connection conn = null;
        Statement stmt = null;
        ArrayList<UserItem> userList = new ArrayList<>();
        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: Execute a query
            System.out.println("Executing getDBUserList");
            stmt = conn.createStatement();
            String query = String.format("SELECT * FROM items WHERE postedBY = '%d'", id);
            ResultSet rs = stmt.executeQuery(query);

            // STEP 5: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                UserItem item = new UserItem(rs.getString("name"), rs.getFloat("invoicePrice"), rs.getFloat("salesPrice"), rs.getString("description"));
                InventoryItem.amountInStock.put(rs.getString("name"), rs.getInt("inStock"));
                userList.add(item);
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }// end finally try
            return userList;
        }

    }

    /**
     * Creates a User in the database.
     *
     * @param name Name of user.
     * @param type Type of user.
     * @param address Address of user.
     * @param username Username of user.
     * @param password Password of user.
     * @return True if user is created or false if there was an error.
     */
    public static boolean createUser(String name, String type, String address, String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        boolean completed = true;
        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: Execute a query
            System.out.println("Executing createUser");
            stmt = conn.createStatement();
            String query = String.format("INSERT INTO users (name,typeOfUser, address, username,password) VALUES ('%s','%s','%s','%s','%s')", name, type, address, username, password);
            stmt.executeUpdate(query);
        } catch (SQLException se) {
            // Handle errors for JDBC
            completed = false;
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            completed = false;
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }// end finally try
            return completed;
        }
    }

    /**
     * Gets user from the database.
     *
     * @param username Username of the user.
     * @return User with the provided username.
     */
    public static User getUser(String username) {
        Connection conn = null;
        Statement stmt = null;
        User user = new User();
        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            // STEP 4: Execute a query
            System.out.println("Executing getUser");
            String query = String.format("SELECT * FROM users WHERE username = '%s'", username);
            ResultSet rs = stmt.executeQuery(query);

            // STEP 5: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setTypeOfUser(rs.getString("typeOfUser"));
                user.setAddress(rs.getString("address"));
                if ("Seller".equals(rs.getString("typeOfUser"))) {
                    user.setRevenue(rs.getFloat("revenue"));
                    user.setCost(rs.getFloat("cost"));
                }
            }
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }// end finally try
            return user;
        }

    }

    /**
     * Checks to see if user already exist.
     *
     * @param username Username of queried user.
     * @return True if user exist and false if it doesn't.
     */
    public static int checkUser(String username) {
        Connection conn = null;
        Statement stmt = null;
        int userExist = 0;
        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: Execute a query
            System.out.println("Executing checkUser");
            stmt = conn.createStatement();
            String query = String.format("SELECT * FROM users WHERE username = '%s'", username);
            ResultSet r = stmt.executeQuery(query);
            if (r.next()) {
                userExist = r.getInt("id");
            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }// end finally try
            return userExist;
        }
    }

    /**
     * Checks to see if item exist.
     *
     * @param name Name of the item.
     * @return True if item exist and false if it doesn't.
     */
    public static boolean checkItem(String name) {
        Connection conn = null;
        Statement stmt = null;
        boolean itemExist = false;
        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: Execute a query
            System.out.println("Executing checkItem");
            stmt = conn.createStatement();
            String query = String.format("SELECT * FROM items WHERE name = '%s'", name);
            ResultSet r = stmt.executeQuery(query);
            if (r.next()) {
                itemExist = true;
            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }// end finally try
            return itemExist;
        }
    }

    /**
     * Logs the user in.
     *
     * @param username Username of user.
     * @param password Password of user.
     * @param typeOfUser Type of user.
     * @return True if login is successful and false if it doesn't.
     */
    public static boolean login(String username, String password, String typeOfUser) {
        Connection conn = null;
        Statement stmt = null;
        boolean login = false;
        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: Execute a query
            System.out.println("Executing login");
            stmt = conn.createStatement();
            String query = String.format("SELECT * FROM users WHERE username = '%s' AND password = '%s' AND typeOfUser = '%s'", username, password, typeOfUser);
            ResultSet r = stmt.executeQuery(query);
            if (r.next()) {
                login = true;
            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }// end finally try
            return login;
        }
    }

    /**
     * Updates the stock and revenue in database.
     *
     * @param cart Cart to be used to update stock.
     */
    public static void updateStock(ArrayList<CartItem> cart) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Executing updateStock");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: Execute a query
            System.out.println("Execute updateStock");
            stmt = conn.createStatement();
            for (CartItem cItem : cart) {

                //Get amount in stock for item
                String getStockQuery = String.format("SELECT * FROM items WHERE name = '%s'", cItem.getName());
                ResultSet rs = stmt.executeQuery(getStockQuery);
                rs.next();

                //get stock info
                int originalInStock = rs.getInt("inStock");
                int newInStock = originalInStock - CartItem.amountInCart.get(cItem.getName());

                //Update User revenue
                float itemSalesPrice = rs.getFloat("salesPrice") * CartItem.amountInCart.get(cItem.getName());
                String updateRevenueQuery = String.format("UPDATE users SET revenue = revenue + '%f' WHERE id = '%d'", itemSalesPrice, rs.getInt("postedBy"));
                stmt.executeUpdate(updateRevenueQuery);

                //Update stock
                String updateQuery = String.format("UPDATE items SET inStock = '%s' WHERE name = '%s'", newInStock, cItem.getName());
                stmt.executeUpdate(updateQuery);

            }

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }// end finally try
        }
    }

    /**
     * Deletes an item from the database.
     *
     * @param item User Item used to delete item from database.
     */
    public static void deleteDBItem(UserItem item) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: Execute a query
            System.out.println("Executing deleteDBItem");
            stmt = conn.createStatement();

            String updateQuery = String.format("DELETE FROM items WHERE name = '%s'", item.getName());
            stmt.executeUpdate(updateQuery);

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }// end finally try
        }
    }

    /**
     * Modifies an item in the database.
     *
     * @param name Name of item to modify.
     * @param invoicePrice Invoice price to change to.
     * @param salesPrice Sales price to change to.
     * @param stock Stock to change to.
     * @param description Description to change to.
     * @param username Username of seller
     */
    public static void modifyDBItem(String name, float invoicePrice, float salesPrice, int stock, String description, String username) {
        Connection conn = null;
        Statement stmt = null;

        // Get cost
        int stockDifference = stock - UserItem.amountInStock.get(name);
        float cost = invoicePrice * stockDifference;

        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Executing modifyDBItem");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: Execute a query
            System.out.println("Execute modifyDBItem");
            stmt = conn.createStatement();

            String updateUserQuery = String.format("UPDATE users SET cost = cost + '%f' WHERE name = '%s'", cost, username);
            stmt.executeUpdate(updateUserQuery);

            String updateItemQuery = String.format("UPDATE items SET invoicePrice = '%f', salesPrice = '%f', inStock = '%d', "
                    + "description = '%s' WHERE name = '%s'", invoicePrice, salesPrice, stock, description, name);
            stmt.executeUpdate(updateItemQuery);

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }// end finally try
        }
    }

    /**
     * Adds item to the database.
     *
     * @param name Name of new item.
     * @param invoicePrice Invoice price of new item.
     * @param salesPrice Sales price of new item.
     * @param postedBy ID of user posting item.
     * @param stock Stock of item.
     * @param description Description of item.
     */
    public static void addDBItem(String name, float invoicePrice, float salesPrice, int postedBy, int stock, String description, String username) {
        Connection conn = null;
        Statement stmt = null;
        float cost = stock * invoicePrice;

        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: Execute a query
            System.out.println("Executing addDBItem");
            stmt = conn.createStatement();

            String updateUserQuery = String.format("UPDATE users SET cost = cost + '%f' WHERE name = '%s'", cost, username);
            stmt.executeUpdate(updateUserQuery);

            String updateQuery = String.format("INSERT INTO items (name,invoicePrice,salesPrice,postedBy,inStock,description) "
                    + "VALUES ('%s','%f','%f','%d','%d','%s')", name, invoicePrice, salesPrice, postedBy, stock, description);
            stmt.executeUpdate(updateQuery);

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }// end finally try
        }
    }

    /**
     * resets the statistics of user in database.
     *
     * @param id ID of user.
     */
    public static void resetStatistics(int id) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 2: Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // STEP 3: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 4: Execute a query
            System.out.println("Executing Reset Statistics");
            stmt = conn.createStatement();

            String updateUserQuery = String.format("UPDATE users SET revenue = '0.00',cost = '0.00' WHERE id = '%d'", id);
            stmt.executeUpdate(updateUserQuery);

        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }// end finally try
        }
    }

    /**
     * User object
     */
    private User user;
    /**
     * ArrayList that contains all items.
     */
    private ArrayList<InventoryItem> inventory;
}
