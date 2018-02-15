package com.macrealstudios.justjava3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int priceOfCup = 6;
    EditText name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);

    }

    public void checkNameSize(EditText name){
        int nameCharSize = 10;
        int length = name.length( );
        if (length > nameCharSize) {
            Toast toast = Toast.makeText(MainActivity.this, R.string.long_name, Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    private int calculatePrice() {
        int price = priceOfCup * quantity;
        return price;
    }

    /**
     * Calculates the price of the order.
     *
     * @return total price
     */

    public void decrementBtn(View view) {
        quantity--;
        if (quantity < 1) {
            quantity = 1;
            Toast.makeText(MainActivity.this, "1 cup of air java coming up!!", Toast.LENGTH_SHORT).show();
        }
        displayquantity(quantity);
    }

    public void incrementBtn(View view) {
        quantity++;
        if (quantity > 99) {
            quantity = 99;
            Toast.makeText(MainActivity.this, "That's enough buddy!", Toast.LENGTH_SHORT).show();
        }
        displayquantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayquantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayMessage(String message) {
        TextView toppingTitle = findViewById(R.id.toppingTitle);
        toppingTitle.setText(message);
    }

    private String createOrderSummary(int price) {
        String priceMessage = "Kaptin Kunal" +
                "\nQuantity: " + quantity +
                "\nTotal: $" + price +
                "\nThank you!";
        displayMessage(priceMessage);
        return priceMessage;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        int price = calculatePrice();
        checkNameSize(name);
        displayMessage(createOrderSummary(price));
    }
}