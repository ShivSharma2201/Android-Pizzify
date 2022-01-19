package com.example.rupizzeria.Model;

import com.example.rupizzeria.Apputil.AppUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the model class a Pizza order.
 *
 * @author Abhishek Patel, Darshan Patel
 */
public class Order {


    private static Order instance;
    private double Cost;
    private String PhoneNumber;
    private List<Pizza> PizzaList = new ArrayList<Pizza>();

    /**
     * Constructor for a Order
     */
    public Order() {
        super();
    }

    /**
     * Function to get instance of order
     *
     * @return
     */
    public static Order getInstance() {
        if (instance == null) {
            instance = new Order();
        }
        return instance;
    }

    /**
     * Getter for Phone number
     *
     * @return
     */
    public String getphoneNumber() {
        return PhoneNumber;
    }

    /**
     * Setter for Phone number
     *
     * @param phoneNumber
     */
    public void setphoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    /**
     * Getter function for Cost
     *
     * @return
     */
    public double getCost() {
        return Cost;
    }

    /**
     * Setter function for Cost
     *
     * @param cost
     */
    public void setCost(double cost) {
        this.Cost = AppUtil.decimalFormat(cost);
    }

    /**
     * Getter function for a PizzaList
     *
     * @return
     */
    public List<Pizza> getpizzaList() {
        return PizzaList;
    }

    /**
     * Setter function for Pizza List
     *
     * @param pizzaList
     */
    public void setPizzaList(List<Pizza> pizzaList) {
        this.PizzaList = pizzaList;
    }

    /**
     * Function to add a pizza to an order
     *
     * @param obj
     * @return
     */
    public boolean add(Object obj) {
        // TODO Auto-generated method stub
        if (PizzaList.add((Pizza) obj))
            return true;

        return false;
    }

    /**
     * Function to clear a order from the session
     */
    public void cleanOrderSession() {

        instance = new Order();
    }

    /**
     * Function to remove a pizza from an order
     *
     * @param obj
     * @return
     */
    public boolean remove(Object obj) {
        // TODO Auto-generated method stub
        if (obj != null) {

            int index = PizzaList.indexOf((Pizza) obj);
            if (index != -1) {
                PizzaList.remove(index);
                return true;
            }
        }
        return false;
    }

    /**
     * To string for an order
     */
    @Override
    public String toString() {
        return "Order [cost=" + Cost + ", pizzaList=" + PizzaList + "]";
    }

}
