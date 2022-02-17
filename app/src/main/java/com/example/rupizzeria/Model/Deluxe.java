package com.example.rupizzeria.Model;

import com.example.rupizzeria.Appconstant.AppConstant;
import com.example.rupizzeria.Apputil.AppUtil;

import java.util.ArrayList;

/**
 * This is the model class for Pizza type Deluxe.
 *
 */
public class Deluxe extends Pizza {
    protected double totalPrice;

    /**
     * Constructor for Deluxe pizza
     */
    public Deluxe() {
        super();
        this.totalPrice = 0.00;
    }

    /**
     * getter function for Topping List
     */
    public ArrayList<String> getToppings() {
        return Toppings;
    }

    /**
     * *Setter function for Topping List
     */
    public void setToppings(ArrayList<String> toppings) {
        this.Toppings = toppings;
    }

    /**
     * Getter for Pizza size
     */
    public String getSize() {
        return Size;
    }

    /**
     * Setter for Pizza Size
     */
    public void setSize(String size) {
        this.Size = size;
    }

    /**
     * Function to calculate the pizza price
     */
    @Override
    public double itemPrice() {

        switch (Size) {

            case AppConstant.SMALL:
                totalPrice = AppConstant.SMALL_DELUXE_PRICE;
                break;
            case AppConstant.MEDIUM:
                totalPrice = AppConstant.SMALL_DELUXE_PRICE + AppConstant.SIZE_UPGRADE;
                break;
            case AppConstant.LARGE:
                totalPrice = AppConstant.SMALL_DELUXE_PRICE + (2 * AppConstant.SIZE_UPGRADE);
                break;
        }
        for (int i = 0; i < Toppings.size(); i++) {
            if (i > 4) {
                totalPrice += AppConstant.TOPPING_UPGRADE;
            }
        }
        setTotalPrice(totalPrice);
        return totalPrice;
    }


    /**
     * setter for TotalPrice
     *
     * @param totalPrice
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = AppUtil.decimalFormat(totalPrice);
    }

    /**
     * ToString function for the class
     */
    @Override
    public String toString() {
        return "Deluxe--> Toppings = " + getToppingList(Toppings) + " Size =" + Size + ", Price = " + totalPrice;
    }

    /**
     * getter function for Topping List
     */
    public String getToppingList(ArrayList<String> toppings) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < toppings.size(); i++) {
            stringBuilder.append(toppings.get(i));
            if (!((toppings.size()) == i)) {
                stringBuilder.append(", ");
            }

        }
        return stringBuilder.toString();
    }
}
