package com.example.rupizzeria.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rupizzeria.Adapters.ListAdapter;
import com.example.rupizzeria.Appconstant.AppConstant;
import com.example.rupizzeria.Apputil.AppUtil;
import com.example.rupizzeria.Model.Order;
import com.example.rupizzeria.Model.Pizza;
import com.example.rupizzeria.Model.StorePizzaOrder;
import com.example.rupizzeria.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the activity class for Cart Activity.
 *
 * @author Abhishek Patel, Darshan Patel
 */
public class CartActivity extends AppCompatActivity {
    private ListView AddedPizzas;
    private TextView stotal;
    private TextView ttotal;
    private TextView ftotal;
    private TextView pnum;
    private Button RemovePiz;
    private Button Confirmorder;


    Order orderSession = Order.getInstance();
    StorePizzaOrder store = StorePizzaOrder.getInstance();
    ArrayList<String> orders = new ArrayList<String>();
    int Piznum;

    /**
     * The oncreate function for the screen at Cart Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Cart");
        setContentView(R.layout.activity_cart);

        AddedPizzas = findViewById(R.id.Pizzaadded);
        stotal = findViewById(R.id.SubTotalLb);
        ttotal = findViewById(R.id.TaxTotallb);
        ftotal = findViewById(R.id.Totalamntlb);
        pnum = findViewById(R.id.editTextPhone);
        RemovePiz = findViewById(R.id.RemovePizzaBtn);
        Confirmorder = findViewById(R.id.PlaceOrderBtn);

        pnum.setText(orderSession.getphoneNumber());
        List<Pizza> plist = orderSession.getpizzaList();
        for (int i = 0; i < orderSession.getpizzaList().size(); i++) {
            orders.add(plist.get(i).toString());
        }
        ListAdapter Pizadapter = new ListAdapter(this, orders);
        AddedPizzas.setAdapter(Pizadapter);
        calculateTotal();

        AddedPizzas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Piznum = position;
                AddedPizzas.setSelected(true);
            }
        });

        RemovePiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (orderSession.getpizzaList().size() != 0) {
                    orders.remove(Piznum);
                    orderSession.remove(orderSession.getpizzaList().get(Piznum));
                    Pizadapter.notifyDataSetChanged();
                    Toast.makeText(CartActivity.this, AppConstant.MPIZZA_REMOVED, Toast.LENGTH_SHORT).show();
                    calculateTotal();
                }
            }
        });

        Confirmorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!store.checknum(orderSession)) {
                    if (orderSession.getpizzaList().size() != 0) {
                        store.add(orderSession);
                        Toast.makeText(CartActivity.this, AppConstant.MORDER_CONFORM, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(CartActivity.this, AppConstant.MNOPHONE_NOPIZZA, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CartActivity.this, AppConstant.MUNIQUE_PHN, Toast.LENGTH_SHORT).show();
                }
                orderSession.cleanOrderSession();
                finish();
            }
        });

    }

    /**
     * Function to calculate the Order Total
     */
    private void calculateTotal() {
        double total = 0.00;
        double tax;
        double cost;

        try {
            if (orderSession != null) {
                for (int i = 0; i < orderSession.getpizzaList().size(); i++) {
                    total += orderSession.getpizzaList().get(i).itemPrice();
                }
                stotal.setText(String.valueOf(AppUtil.decimalFormat(total)));
                tax = (AppConstant.TAX_PERCENTAGE * total) / 100;
                ttotal.setText(String.valueOf(AppUtil.decimalFormat(tax)));
                cost = tax + total;
                orderSession.setCost(cost);
                ftotal.setText(String.valueOf(AppUtil.decimalFormat(cost)));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}