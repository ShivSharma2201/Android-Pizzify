package com.example.rupizzeria.Model;

import com.example.rupizzeria.Appconstant.AppConstant;

/**
 * This is the model class for PizzaMaker which creates an instance based on the chosen flavor.
 *
 * @author Abhishek Patel, Darshan Patel
 */
public class PizzaMaker {

    /**
     * A function which produces different instances of Pizzas
     */
    public static Pizza createPizza(String flavour) {
        switch (flavour) {
            case AppConstant.PIZZA_DELUXE:
                Deluxe a = new Deluxe();
                return a;
            case AppConstant.PIZZA_HAWAIIAN:
                Hawaiian b = new Hawaiian();
                return b;
            case AppConstant.PIZZA_PEPPERONI:
                Pepperoni c = new Pepperoni();
                return c;
            default:
                return null;
        }
    }
}
