package com.example.rupizzeria.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rupizzeria.Adapters.ListAdapter;
import com.example.rupizzeria.Appconstant.AppConstant;
import com.example.rupizzeria.Apputil.AppUtil;
import com.example.rupizzeria.Model.Pizza;
import com.example.rupizzeria.Model.StorePizzaOrder;
import com.example.rupizzeria.R;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the activity class for Store Activity.
 *
 */
public class StoreActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner Pnumlist;
    private TextView Ttlamount;
    private ListView Orderlist;
    private Button ClearOrderBtn;

    int Ono;
    StorePizzaOrder Store = StorePizzaOrder.getInstance();

    /**
     * The oncreate function for the screen at Store Activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Store");
        setContentView(R.layout.activity_store);
        Pnumlist = findViewById(R.id.PhoneSelect);
        Ttlamount = findViewById(R.id.Storettlamnt);
        Orderlist = findViewById(R.id.OrdersList);
        ClearOrderBtn = findViewById(R.id.ClearOrder);

        ArrayList<String> Num = new ArrayList<>();
        for (int i = 0; i < Store.getOrderSessionList().size(); i++) {
            Num.add(String.valueOf(Store.getOrderSessionList().get(i).getphoneNumber()));
        }

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Num);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Pnumlist.setOnItemSelectedListener(this);
        Pnumlist.setAdapter(aa);

        ClearOrderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Store.getOrderSessionList().size() != 0) {
                    Store.remove(Store.getOrderSessionList().get(Ono));
                    Num.remove(Ono);
                    Orderlist.setAdapter(null);
                    Ttlamount.setText("");
                    aa.notifyDataSetChanged();
                    Toast.makeText(StoreActivity.this, AppConstant.MORDER_CLEAR, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * onItemSelected for the spinner
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<String> orders = new ArrayList<>();
        Pnumlist.setSelected(true);
        Ono = position;
        for (int i = 0; i < Store.getOrderSessionList().size(); i++) {
            if (Store.getOrderSessionList().get(i).getphoneNumber().equals(Pnumlist.getItemAtPosition(position))) {
                List<Pizza> plist = Store.getOrderSessionList().get(i).getpizzaList();
                for (int j = 0; j < plist.size(); j++) {
                    orders.add(plist.get(j).toString());
                }
                ListAdapter Pizadapter = new ListAdapter(StoreActivity.this, orders);
                Orderlist.setAdapter(Pizadapter);
                Pizadapter.notifyDataSetChanged();
                Ttlamount.setText(String.valueOf(AppUtil.decimalFormat(Store.getOrderSessionList().get(i).getCost())));
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}