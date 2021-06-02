package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private byte numberOfCoffees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button orderButton = findViewById(R.id.send_an_order_button);
        Button incrementPriceButton = findViewById(R.id.increment_price_button);
        Button decrementPriceButton = findViewById(R.id.decrement_price_button);

        orderButton.setOnClickListener(this);
        incrementPriceButton.setOnClickListener(this);
        decrementPriceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.increment_price_button) {
            if (numberOfCoffees < 9) {
                numberOfCoffees++;
                displayQuantityOfCoffees(Integer.toString(numberOfCoffees));
            }
        }

        if (v.getId() == R.id.decrement_price_button) {
            if (numberOfCoffees > 0) {
                numberOfCoffees--;
                displayQuantityOfCoffees(Integer.toString(numberOfCoffees));
            }
        }

        if (v.getId() == R.id.send_an_order_button) {
            sendAnOrder();
            Toast toast = Toast.makeText(this, "Order is accepted!", Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private void displayQuantityOfCoffees(String number) {
        TextView quantity = findViewById(R.id.quantity_of_coffees_text_view);
        quantity.setText(number);
    }

    private void sendAnOrder() {
        String orderList = createOrderList(numberOfCoffees * 3);
        EditText customerEditText = findViewById(R.id.customer_name_edit_text);

        String uriText =
                "mailto:example@gmail.com" +
                        "?subject=" + Uri.encode(customerEditText.getText().toString()) +
                        "&body=" + Uri.encode(orderList);

        Uri uri = Uri.parse(uriText);

        Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
        sendIntent.setData(uri);
        startActivity(Intent.createChooser(sendIntent, "Send email"));
    }

    private String createOrderList(int number) {
        CheckBox hasWhippedCream = findViewById(R.id.topping_whipped_cream_check_box);
        CheckBox hasChocolate = findViewById(R.id.topping_chocolate_check_box);

        /* надбавка стоимости доппингов к цене */
        String orderList = "Quantity: " + numberOfCoffees + " coffees";
        if (hasWhippedCream.isChecked()) {
            number += numberOfCoffees;
            orderList += "\n\t+ add whipped cream";
        }

        if (hasChocolate.isChecked()) {
            number += 2 * numberOfCoffees;
            orderList += "\n\t+ add some chocolate";
        }

        String orderPrice = NumberFormat.getCurrencyInstance(Locale.US).format(number);
        orderList += "\n\nTotal: " + orderPrice;

        return orderList;
    }
}
