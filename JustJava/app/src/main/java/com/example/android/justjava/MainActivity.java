package com.example.android.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffees = 2;
    int coffeePrice = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display(numberOfCoffees);
        displayPrice(numberOfCoffees * coffeePrice);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayPrice(numberOfCoffees * coffeePrice);
    }

    /**
     * This method increment the number of Coffee to be ordered
     * @param view
     */
    public void increment(View view){
        numberOfCoffees++;
        display(numberOfCoffees);
    }

    /**
     * This method decrement the number of Coffee to be ordered
     * @param view
     */
    public void decrement(View view){
        if(numberOfCoffees>0){
            numberOfCoffees--;
            display(numberOfCoffees);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method display the given quantity value on the screen.
     */
     private void displayPrice(int number){
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("Total: " + NumberFormat.getCurrencyInstance().format(number)+"\n Thank you!");
    }
}
