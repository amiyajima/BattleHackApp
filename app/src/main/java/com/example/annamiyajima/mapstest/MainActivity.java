package com.example.annamiyajima.mapstest;

import android.preference.PreferenceActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.braintreepayments.api.dropin.BraintreePaymentActivity;
import com.braintreepayments.api.dropin.Customization;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.location.places.PlaceFilter;
import com.google.android.gms.location.places.Place;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {
    private Button findbutton;
    private Button searchlocation;
    private Button addtime;
    private Button payment;
    private EditText find;
    private AsyncHttpClient client = new AsyncHttpClient();
    private String clientToken;
    private String SERVERNAME = "http://hmfpa.org/bhacks/clientToken.php";
    private PreferenceActivity.Header[] requestHeaders = null;
    private static final int REQUEST_CODE = Menu.FIRST;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createToken();
        setContentView(R.layout.activity_main);

        find = (EditText) findViewById(R.id.findeditText);
        findbutton = (Button) findViewById(R.id.findbutton);
        searchlocation = (Button) findViewById(R.id.searchbutton);
        addtime = (Button) findViewById(R.id.addtimebutton);
        payment = (Button) findViewById(R.id.paymentbutton);

        addOnClickSearchLocation();
        hideKeyboardMain();
        addOnClickAdd();
        onPayClick();
    }

    private void createToken() {
        client.get(SERVERNAME, new TextHttpResponseHandler() {
            @Override
            public void onStart() {
                // Initiated the request
            }

            @Override
            public void onSuccess(int responseCode, Header[] responseHeaders, String responseBody) {
                // Successfully got a response
                clientToken = responseBody;
                System.out.println("token generated -- " + clientToken);
            }

            @Override
            public void onFailure(int responseCode, Header[] responseHeaders, String responseBody, Throwable e) {
                // Response failed :(
                System.out.println("server connection failed");
            }

            @Override
            public void onFinish() {
                // Completed the request (either success or failure)
            }
        });
    }

    //method called to open braintree payment page
    public void onPayClick() {
        final Context context = this;
        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, AddTimeActivity.class);
//                startActivity(intent);
                braintreeUI();
            }
        });
    }

    private void braintreeUI(){
        Customization customization = new Customization.CustomizationBuilder()
                .primaryDescription("Awesome payment")
                .secondaryDescription("Using the Client SDK")
                .amount("$10.00")
                .submitButtonText("Pay")
                .build();

        Intent intent = new Intent(this, BraintreePaymentActivity.class);
        intent.putExtra(BraintreePaymentActivity.EXTRA_CUSTOMIZATION, customization);
        intent.putExtra(BraintreePaymentActivity.EXTRA_CLIENT_TOKEN, clientToken);

        startActivityForResult(intent, REQUEST_CODE);
    }

    //helper function for placePick. restaurantName contains name of chosen business
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == BraintreePaymentActivity.RESULT_OK) {
            String paymentMethodNonce = data.getStringExtra(BraintreePaymentActivity.EXTRA_PAYMENT_METHOD_NONCE);

            RequestParams requestParams = new RequestParams();
            requestParams.put("payment_method_nonce", paymentMethodNonce);
            requestParams.put("amount", "10.00");

            client.post(SERVERNAME + "/payment", requestParams, new AsyncHttpResponseHandler() {

                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    Toast.makeText(MainActivity.this, responseBody.toString(), Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                }
            });
        }

        else if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String restaurantName = String.format("Place: %s", place.getName());
                restaurantName = restaurantName.substring(6);
            }
        }
    }

    public void hideKeyboardMain() {
        find.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    //helper method to hideKeyboardMain()
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    //Button link to map page
    public void addOnClickSearchLocation() {
        final Context context = this;

        searchlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placePick();
//                Intent intent = new Intent(context, MapsActivity.class);
//                startActivity(intent);
            }
        });
    }

    //place pick function when search location is clicked
    public void placePick() {
        int PLACE_PICKER_REQUEST = 1;
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

        Context context = getApplicationContext();
        try {
//            ArrayList<String> restictToRestaurants = new ArrayList<>();
//            restictToRestaurants.add(restaurants);
//            PlaceFilter placeFilter = new PlaceFilter(false, restictToRestaurants);
            //parameters: intent, int
            startActivityForResult(builder.build(context), PLACE_PICKER_REQUEST);

        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    //button link to add time page
    public void addOnClickAdd() {
        final Context context = this;
        addtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddTimeActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
