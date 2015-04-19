package com.example.annamiyajima.mapstest;

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

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

public class MainActivity extends ActionBarActivity {
    private Button findbutton;
    private Button searchlocation;
    private Button addtime;
    private EditText findtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findtext = (EditText) findViewById(R.id.findeditText);
        findbutton = (Button) findViewById(R.id.findbutton);
        searchlocation = (Button) findViewById(R.id.searchbutton);
        addtime = (Button) findViewById(R.id.addtimebutton);

        addOnClickFind();
        addOnClickSearchLocation();
        hideKeyboardMain();
        addOnClickAdd();
    }

    public void addOnClickFind(){

//        final Context context = this;
        findbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = findtext.getText().toString();
                //Add if statements that either link to list page if successfull or back to same page if search failed

//                Bundle b = new Bundle();
                Intent intent = new Intent(getBaseContext(), qTimesActivity.class);
//                intent.putExtra()
                startActivity(intent);
            }
        });


    }

    //Button link to map page
    public void addOnClickSearchLocation(){
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
            startActivityForResult(builder.build(context), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    //helper function for placePick. restaurantName contains name of chosen business
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String restaurantName = String.format("Place: %s", place.getName());
                restaurantName = restaurantName.substring(6);

                //pass String restaurantName into qTimesActivity
                Bundle b = new Bundle();
                Intent intent = new Intent(getBaseContext(), qTimesActivity.class);
                intent.putExtra("name",restaurantName);
                startActivity(intent);
            }
        }
    }

    //button link to add time page
    public void addOnClickAdd(){
        final Context context = this;
        addtime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AddTimeActivity.class);
                startActivity(intent);
            }
        });
    }
    //hiding keyboard method
    public void hideKeyboardMain(){
        findtext.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
