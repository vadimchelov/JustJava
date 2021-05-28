package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int numberOfCoffees = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayPrice(0);
    }

    public void submitOrder(View view) {
        displayPrice(numberOfCoffees * 5);
    }

    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance(Locale.US).format(number));
    }

    public void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
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
}
