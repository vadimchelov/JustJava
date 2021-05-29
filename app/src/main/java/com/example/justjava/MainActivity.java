package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int numberOfCoffees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button orderButton = findViewById(R.id.order_button);
        Button incrementPriceButton = findViewById(R.id.increment_price_button);
        Button decrementPriceButton = findViewById(R.id.decrement_price_button);

        orderButton.setOnClickListener(this);
        incrementPriceButton.setOnClickListener(this);
        decrementPriceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_button:
                displayPrice(numberOfCoffees * 5);
                break;
            case R.id.increment_price_button:
                numberOfCoffees++;
                display(numberOfCoffees);
                break;
            case R.id.decrement_price_button:
                if (numberOfCoffees > 0) {
                    numberOfCoffees--;
                    display(numberOfCoffees);
                }
                break;
        }
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance(Locale.US).format(number));
    }

    public void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }


    /******************************
     старый код для работы с кнопками

     public void submitOrder(View view) {
     displayPrice(numberOfCoffees * 5);
     }

     public void incrementPrice(View view) {
     numberOfCoffees++;
     display(numberOfCoffees);
     }

     public void decrementPrice(View view) {
     if (numberOfCoffees > 0) {
     numberOfCoffees--;
     display(numberOfCoffees);
     }
     }
     */
}
