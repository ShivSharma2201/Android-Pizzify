package com.example.rupizzeria.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rupizzeria.Adapters.ListAdapter;
import com.example.rupizzeria.Appconstant.AppConstant;
import com.example.rupizzeria.Apputil.AppUtil;
import com.example.rupizzeria.Model.Order;
import com.example.rupizzeria.Model.Pizza;
import com.example.rupizzeria.Model.PizzaMaker;
import com.example.rupizzeria.R;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is the activity class for Cart Customization Activity.
 *
 */
public class CartCustomizationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner PizzaSize;
    private TextView PizzaActiveTotal;
    private ListView Pizzaavailtoppings;
    private ListView Pizzaselectedtopping;
    private Button Addtopping;
    private Button Removetopping;
    private Button OrderButton;
    private ImageView PizPhoto;


    Order orderSession = Order.getInstance();
    Pizza piz;
    String pizza;
    int toppingadd, toppingrem;
    String[] Size;
    ArrayList<String> Availtoppings = new ArrayList<String>();
    ArrayList<String> Selecttoppings = new ArrayList<String>();

    /**
     * The oncreate function for the screen at Cart Customization Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Pizza Customization");
        setContentView(R.layout.activity_cart_customization);
        Intent ThisIntent = getIntent();
        ThisIntent.getExtras();
        pizza = ThisIntent.getStringExtra("PizType");
        piz = PizzaMaker.createPizza(pizza);

        PizzaSize = findViewById(R.id.PizzaSizeL);

        PizzaActiveTotal = findViewById(R.id.PizTotal);
        Pizzaavailtoppings = findViewById(R.id.Atoppinglist);
        Pizzaselectedtopping = findViewById(R.id.Stoppinglist);
        Addtopping = findViewById(R.id.Addtoppingbutton);
        Removetopping = findViewById(R.id.Removetoppingbutton);
        OrderButton = findViewById(R.id.OrderPizzaButton);
        PizPhoto = findViewById(R.id.Pizzaimg);

        SetSizeList();
        SetTopList();

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Size);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        PizzaSize.setOnItemSelectedListener(this);
        PizzaSize.setAdapter(aa);

        ListAdapter Atopadapter = new ListAdapter(this, Availtoppings);
        ListAdapter Stopadapter = new ListAdapter(this, Selecttoppings);
        Pizzaavailtoppings.setAdapter(Atopadapter);
        Pizzaselectedtopping.setAdapter(Stopadapter);
        piz.setSize(AppConstant.SMALL);
        PizzaActiveTotal.setText(String.valueOf(AppUtil.decimalFormat(piz.itemPrice())));
        setImage(pizza);

        Pizzaavailtoppings.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pizzaavailtoppings.setSelected(true);
                toppingadd = position;
            }
        });

        Pizzaselectedtopping.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pizzaselectedtopping.setSelected(true);
                toppingrem = position;

            }
        });

        Addtopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Selecttoppings.size() < 7) {
                        Selecttoppings.add(Pizzaavailtoppings.getItemAtPosition(toppingadd).toString());
                        Availtoppings.remove(toppingadd);
                        Stopadapter.notifyDataSetChanged();
                        Atopadapter.notifyDataSetChanged();
                        Pizzaavailtoppings.setSelected(false);
                        piz.setToppings(Selecttoppings);
                        PizzaActiveTotal.setText(String.valueOf(AppUtil.decimalFormat(piz.itemPrice())));
                    } else
                        Toast.makeText(CartCustomizationActivity.this, AppConstant.MMAXTOP_ADDED, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        Removetopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Availtoppings.add(Pizzaselectedtopping.getItemAtPosition(toppingrem).toString());
                    Selecttoppings.remove(toppingrem);
                    Atopadapter.notifyDataSetChanged();
                    Stopadapter.notifyDataSetChanged();
                    piz.setToppings(Selecttoppings);
                    PizzaActiveTotal.setText(String.valueOf(AppUtil.decimalFormat(piz.itemPrice())));
                    removetopalert();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        OrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                piz.setToppings(Selecttoppings);
                orderSession.add(piz);
                Toast.makeText(CartCustomizationActivity.this, AppConstant.MPIZZA_ADDED, Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    /**
     * The fucntion to show toast whenever removing a topping
     */
    private void removetopalert() {
        switch (pizza) {
            case AppConstant.PIZZA_DELUXE:
                if (Selecttoppings.size() < 5) {
                    Toast.makeText(CartCustomizationActivity.this, AppConstant.MREMOVE_TOPP, Toast.LENGTH_SHORT).show();
                }
                break;
            case AppConstant.PIZZA_HAWAIIAN:
                if (Selecttoppings.size() < 2) {
                    Toast.makeText(CartCustomizationActivity.this, AppConstant.MREMOVE_TOPP, Toast.LENGTH_SHORT).show();
                }
                break;
            case AppConstant.PIZZA_PEPPERONI:
                if (Selecttoppings.size() < 1) {
                    Toast.makeText(CartCustomizationActivity.this, AppConstant.MREMOVE_TOPP, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    /**
     * The function to setImages for the pizza in the activity
     */
    private void setImage(String type) {
        switch (type) {
            case AppConstant.PIZZA_DELUXE:
                PizPhoto.setImageResource(R.drawable.deluxe);
                break;
            case AppConstant.PIZZA_PEPPERONI:
                PizPhoto.setImageResource(R.drawable.pepperoni);
                break;
            case AppConstant.PIZZA_HAWAIIAN:
                PizPhoto.setImageResource(R.drawable.hawaiian);
                break;
            default:
                PizPhoto.setImageResource(R.drawable.deluxe);
                break;
        }
    }

    /**
     * The fucntion to set the size list
     */
    private void SetSizeList() {
        Size = new String[]{AppConstant.SMALL, AppConstant.MEDIUM, AppConstant.LARGE};
    }

    /**
     * The fucntion to set the topping lists in the activity
     */
    private void SetTopList() {
        switch (pizza) {
            case AppConstant.PIZZA_PEPPERONI:
                Availtoppings.addAll(Arrays.asList(AppConstant.TOP_BEEF, AppConstant.TOP_HAM, AppConstant.TOP_GREEN_PEPPER,
                        AppConstant.TOP_CHEESE, AppConstant.TOP_ONION, AppConstant.TOP_BLACK_OLIVES,
                        AppConstant.TOP_CHICKEN, AppConstant.TOP_MUSHROOM, AppConstant.TOP_PINEAPPLE,
                        AppConstant.TOP_SAUSAGE));
                Selecttoppings.add(AppConstant.TOP_PEPPERONI);
                break;
            case AppConstant.PIZZA_DELUXE:
                Availtoppings.addAll(Arrays.asList(AppConstant.TOP_CHEESE, AppConstant.TOP_ONION, AppConstant.TOP_CHICKEN,
                        AppConstant.TOP_MUSHROOM, AppConstant.TOP_PINEAPPLE, AppConstant.TOP_SAUSAGE));
                Selecttoppings.addAll(Arrays.asList(AppConstant.TOP_PEPPERONI, AppConstant.TOP_BEEF, AppConstant.TOP_HAM,
                        AppConstant.TOP_GREEN_PEPPER, AppConstant.TOP_BLACK_OLIVES));
                break;
            case AppConstant.PIZZA_HAWAIIAN:
                Availtoppings.addAll(Arrays.asList(AppConstant.TOP_PEPPERONI, AppConstant.TOP_BEEF, AppConstant.TOP_HAM,
                        AppConstant.TOP_GREEN_PEPPER, AppConstant.TOP_CHEESE, AppConstant.TOP_ONION,
                        AppConstant.TOP_BLACK_OLIVES, AppConstant.TOP_CHICKEN, AppConstant.TOP_MUSHROOM));
                Selecttoppings.addAll(Arrays.asList(AppConstant.TOP_PINEAPPLE, AppConstant.TOP_SAUSAGE));
                break;
            default:
                break;
        }
    }

    /**
     * onItemSelected for the spinner
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        PizzaActiveTotal = findViewById(R.id.PizTotal);
        switch (position) {
            case 0:
                piz.setSize(AppConstant.SMALL);
                piz.itemPrice();
                PizzaActiveTotal.setText(String.valueOf(AppUtil.decimalFormat(piz.itemPrice())));
                break;
            case 1:
                piz.setSize(AppConstant.MEDIUM);
                piz.itemPrice();
                PizzaActiveTotal.setText(String.valueOf(AppUtil.decimalFormat(piz.itemPrice())));
                break;
            case 2:
                piz.setSize(AppConstant.LARGE);
                piz.itemPrice();
                PizzaActiveTotal.setText(String.valueOf(AppUtil.decimalFormat(piz.itemPrice())));
                break;
            default:
                piz.setSize(AppConstant.SMALL);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}