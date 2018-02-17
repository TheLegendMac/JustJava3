package com.macrealstudios.justjava3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 0;
    int priceOfCup = 3;
    EditText name;
    Button submitOrderButton;
    CheckBox cinnamon, whippedCream;
    Boolean hasWhippedCream = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        submitOrderButton = findViewById(R.id.submitButton);
        cinnamon = findViewById(R.id.cinnamonCheckbox);
        whippedCream = findViewById(R.id.whippedCreamCheckbox);

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

    private String createOrderSummary(int price, EditText name) {
        String priceMessage =
                "Your total for " + quantity + " cups is $" + price + "." +
                "\nThank you " + name.getText().toString() + "!";
        displayMessage(priceMessage);
        return priceMessage;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        if (cinnamon.isChecked()) {
            Log.v("Add cream", "Adding Cream");
            cinnamon.setChecked(false);
        }
        Toast.makeText(this,"Coming right up!", Toast.LENGTH_SHORT).show();
        checkNameSize(name);
        displayMessage(createOrderSummary(calculatePrice(), name));
    }
}