package com.example.rupizzeria.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the model class for StoreOrder which provides us the functionality to store the different orders.
 *
 * @author Abhishek Patel, Darshan Patel
 */
public class StorePizzaOrder {

    private static StorePizzaOrder instance;

    private List<Order> OrderSessionList = new ArrayList<Order>();

    /**
     * Constructor for StorePorder
     */
    public StorePizzaOrder() {
        super();
    }

    /**
     * Function to get the instance of a StorePorder
     */
    public static StorePizzaOrder getInstance() {
        if (instance == null) {
            instance = new StorePizzaOrder();
        }
        return instance;
    }

    /**
     * Getter function for list of orders
     */
    public List<Order> getOrderSessionList() {
        return OrderSessionList;
    }

    /**
     * Function to add an order to the store
     */
    public boolean add(Object obj) {

        if (OrderSessionList.add((Order) obj))
            return true;

        return false;
    }

    /**
     * Function to remove an order from the store
     */
    public boolean remove(Object obj) {

        if (obj != null) {

            int index = OrderSessionList.indexOf((Order) obj);
            if (index != -1) {
                OrderSessionList.remove(index);
                return true;
            }

        }

        return false;
    }

    /**
     * To check for unique numbers
     */
    public boolean checknum(Order o) {
        for (int i = 0; i < OrderSessionList.size(); i++) {
            if (OrderSessionList.get(i).getphoneNumber().equals(o.getphoneNumber())) {
                return true;
            }
        }
        return false;
    }

    /**
     * To String function for  StorePorder
     */
    @Override
    public String toString() {
        return "OrderSession [orderSessionList=" + OrderSessionList + "]";
    }

}

