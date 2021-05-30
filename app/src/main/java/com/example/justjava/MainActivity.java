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

        Button orderButton = findViewById(R.id.order_button);
        Button incrementPriceButton = findViewById(R.id.increment_price_button);
        Button decrementPriceButton = findViewById(R.id.decrement_price_button);

        orderButton.setOnClickListener(this);
        incrementPriceButton.setOnClickListener(this);
        decrementPriceButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.order_button) {
            displayOrder(numberOfCoffees * 3);
            Toast toast = Toast.makeText(this, "Order is accepted!", Toast.LENGTH_SHORT);
            toast.show();
        }
        if (v.getId() == R.id.increment_price_button) {
            if (numberOfCoffees < 99) {
                numberOfCoffees++;
                display(Integer.toString(numberOfCoffees));
            }
        }

        if (v.getId() == R.id.decrement_price_button) {
            if (numberOfCoffees > 0) {
                numberOfCoffees--;
                display(Integer.toString(numberOfCoffees));
            }
        }

//        switch (v.getId()) {
//            case R.id.order_button:
//                displayOrder(numberOfCoffees * 5);
//                Toast toast = Toast.makeText(this, "Order is accepted!",Toast.LENGTH_SHORT);
//                toast.show();
//                break;
//            case R.id.increment_price_button:
//                if (numberOfCoffees < 99) {
//                    numberOfCoffees++;
//                    display(Integer.toString(numberOfCoffees));
//                }
//                break;
//            case R.id.decrement_price_button:
//                if (numberOfCoffees > 0) {
//                    numberOfCoffees--;
//                    display(Integer.toString(numberOfCoffees));
//                }
//                break;
//        }
    }

    private void display(String number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText(number);
    }


    private void displayOrder(int number) {
        TextView orderTextView = findViewById(R.id.order_text_view);
        CheckBox hasWhippedCream = findViewById(R.id.whipped_cream_topping_check_box);
        CheckBox hasChocolate = findViewById(R.id.chocolate_topping_check_box);
        EditText customerEditText = findViewById(R.id.customer_name_edit_text);

        /* надбавка стоимости доппингов к цене */
        String orderList = "Name: " + customerEditText.getText() + "\nQuantity: " + numberOfCoffees;
        if (hasWhippedCream.isChecked()) {
            number += numberOfCoffees;
            orderList += "\n\t+ whipped cream";
        }
        if (hasChocolate.isChecked()) {
            number += 2 * numberOfCoffees;
            orderList += "\n\t+ some chocolate";
        }
        String orderPrice = NumberFormat.getCurrencyInstance(Locale.US).format(number);
        orderList += "\nTotal: " + orderPrice;

        orderTextView.setText(orderList);


        /*неудачная попытка в Intent*/
//
//        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "vadimchelov@gmail.com"));
//        intent.putExtra(Intent.EXTRA_SUBJECT, customerEditText.getText());
//        intent.putExtra(Intent.EXTRA_TEXT, orderList);
//        if (intent.resolveActivity(getPackageManager()) != null) {
//            startActivity(Intent.createChooser(intent, getString(R.string.сhoose_email_app)));
//        }
    }
}