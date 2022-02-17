package com.example.rupizzeria.Model;

import java.util.ArrayList;

/**
 * This is the model class for Pizza.
 *
 */
public abstract class Pizza {
    protected ArrayList<String> Toppings = new ArrayList<String>();
    protected String Size;

    public abstract double itemPrice();

    public abstract void setToppings(ArrayList<String> selecttoppings);

    public abstract void setSize(String small);
}
