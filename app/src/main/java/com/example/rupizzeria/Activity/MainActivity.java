package com.example.rupizzeria.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rupizzeria.Appconstant.AppConstant;
import com.example.rupizzeria.Model.Order;
import com.example.rupizzeria.R;

/**
 * This is the activity class for Main Activity.
 *
 * @author Abhishek Patel, Darshan Patel
 */
public class MainActivity extends AppCompatActivity {

    private ImageButton DeluxeButton;
    private ImageButton PepperoniButton;
    private ImageButton HawaiianButton;
    private ImageButton CartButton;
    private ImageButton StoreButton;
    private EditText PhoneNum;

    /**
     * The oncreate function for the screen at Store Activity
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DeluxeButton = findViewById(R.id.DeluxeP);
        PepperoniButton = findViewById(R.id.PepperoniP);
        HawaiianButton = findViewById(R.id.HawaiianP);
        CartButton = findViewById(R.id.CartOrderB);
        StoreButton = findViewById(R.id.StoreOrderB);
        PhoneNum = findViewById(R.id.PhoneNumEntered);

        DeluxeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Order orderSession = Order.getInstance();
                orderSession.setphoneNumber(String.valueOf(PhoneNum.getText()));
                if (!orderSession.getphoneNumber().equals("")) {
                    Intent P1 = new Intent(MainActivity.this, CartCustomizationActivity.class);
                    P1.putExtra("PizType", AppConstant.PIZZA_DELUXE);
                    startActivity(P1);
                } else {
                    Toast.makeText(MainActivity.this, AppConstant.MENTER_PHONENUM, Toast.LENGTH_SHORT).show();
                }
            }
        });

        HawaiianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Order orderSession = Order.getInstance();
                orderSession.setphoneNumber(String.valueOf(PhoneNum.getText()));
                if (!orderSession.getphoneNumber().equals("")) {
                    Intent P1 = new Intent(MainActivity.this, CartCustomizationActivity.class);
                    P1.putExtra("PizType", AppConstant.PIZZA_HAWAIIAN);
                    startActivity(P1);
                } else {
                    Toast.makeText(MainActivity.this, AppConstant.MENTER_PHONENUM, Toast.LENGTH_SHORT).show();
                }
            }
        });

        PepperoniButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Order orderSession = Order.getInstance();
                orderSession.setphoneNumber(String.valueOf(PhoneNum.getText()));
                if (!orderSession.getphoneNumber().equals("")) {
                    Intent P1 = new Intent(MainActivity.this, CartCustomizationActivity.class);
                    P1.putExtra("PizType", AppConstant.PIZZA_PEPPERONI);
                    startActivity(P1);
                } else {
                    Toast.makeText(MainActivity.this, AppConstant.MENTER_PHONENUM, Toast.LENGTH_SHORT).show();
                }
            }
        });
        CartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent P1 = new Intent(MainActivity.this, CartActivity.class);
                startActivity(P1);
            }
        });

        StoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent P1 = new Intent(MainActivity.this, StoreActivity.class);
                startActivity(P1);
            }
        });
    }
}
