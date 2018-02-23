package com.macrealstudios.justjava3;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
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

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, R.string.lotOfJavaMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Get user's name
        EditText nameField = findViewById(R.id.name);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();

        // Figure out if the user wants whipped cream topping
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if the user wants cinnamon topping
        CheckBox cinnamonCheckbox = findViewById(R.id.cinnamon_checkbox);
        boolean hasCinnamon = cinnamonCheckbox.isChecked();

        // Calculate the price
        int price = calculatePrice(hasWhippedCream, hasCinnamon);

        // Display the order summary on the screen
        String message = createOrderSummary(name, price, hasWhippedCream, hasCinnamon);
        TextView summaryView = findViewById(R.id.summaryView);
        summaryView.setText("Thank you " + name + "!");

        // Use an intent to launch an email app.
        // Send the order summary in the email body.
        Intent intent = new Intent(Intent.ACTION_SENDTO)
                .setData(Uri.parse("mailto:"))
                .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.emailTitle, name))
                .putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    /**
     * Calculates the price of the order.
     *
     * @param addWhippedCream is whether or not we should include whipped cream topping in the price
     * @param hasCinnamon     is whether or not we should include chocolate topping in the price
     * @return total price
     */
    private int calculatePrice(boolean addWhippedCream, boolean hasCinnamon) {
        // First calculate the price of one cup of coffee
        int basePrice = 3;

        // If the user wants whipped cream, add $1 per cup
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }

        // If the user wants cinnamon, add $2 per cup
        if (hasCinnamon) {
            basePrice = basePrice + 2;
        }

        // Calculate the total order price by multiplying by the quantity
        return quantity * basePrice;
    }

    /**
     * Create summary of the order.
     *
     * @param name            on the order
     * @param price           of the order
     * @param addWhippedCream is whether or not to add whipped cream to the coffee
     * @param hasCinnamon     is whether or not to add chocolate to the coffee
     * @return text summary
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream,
                                      boolean hasCinnamon) {
        String priceMessage;
        if (hasCinnamon && addWhippedCream) {
            priceMessage = getString(R.string.bothMessage, quantity, price);

        } else if (addWhippedCream) {
            priceMessage = getString(R.string.whippedCreamMessage, quantity, price);

        } else if (hasCinnamon) {
            priceMessage = getString(R.string.cinnamonMessage, quantity, price);
        } else {
            priceMessage = getString(R.string.regularMessage, quantity, price);
        }
        priceMessage += getString(R.string.thankYouMessage, name);
        return priceMessage;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }
}