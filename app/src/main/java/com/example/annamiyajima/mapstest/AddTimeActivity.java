package com.example.annamiyajima.mapstest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;


public class AddTimeActivity extends ActionBarActivity {

    private Button findMeOnMap;
    private Button back;
    private Button addtime;
    private EditText text1;
    private EditText text2;
    private EditText text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_time);

        findMeOnMap = (Button) findViewById(R.id.findmemap);
        back = (Button) findViewById(R.id.back);
        addtime = (Button) findViewById(R.id.addtime);

        //restaurant name
        text1 = (EditText) findViewById(R.id.editText);

        //wait time
        text2 = (EditText) findViewById(R.id.editText2);

        //party size
        text3 = (EditText) findViewById(R.id.editText3);

        addOnClickfindmemap();
        hideKeyboardMain();
        addOnClickBack();
        addOnClickAdd();

    }

    public void addOnClickAdd(){
        addtime.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getBaseContext(), thankaddingActivity.class);
                startActivity(i);
            }
        });
    }
    public void addOnClickfindmemap(){
        final Context context = this;

        findMeOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placePick();
//                Intent intent = new Intent(context, MapsActivity.class);
//                startActivity(intent);
            }
        });
    }

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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String restaurantName = String.format("Place: %s", place.getName());
                restaurantName = restaurantName.substring(6);
                text1.setText("");
                text1.setText(restaurantName);
            }
        }
    }

    public void addOnClickBack(){
        final Context context = this;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void hideKeyboardMain(){
        View.OnFocusChangeListener hide = new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        };
        text1.setOnFocusChangeListener(hide);
        text2.setOnFocusChangeListener(hide);
        text3.setOnFocusChangeListener(hide);

//        text1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    hideKeyboard(v);
//                }
//            }
//        });

    }
    //helper method to hideKeyboardMain()
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_time, menu);
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
