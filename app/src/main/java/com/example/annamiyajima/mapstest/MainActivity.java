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
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends ActionBarActivity {
    private Button findbutton;
    private Button searchlocation;
    private Button addtime;
    private EditText findEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findEditText = (EditText) findViewById(R.id.findeditText);
        findbutton = (Button) findViewById(R.id.findbutton);
        searchlocation = (Button) findViewById(R.id.searchbutton);
        addtime = (Button) findViewById(R.id.addtimebutton);

        addOnClickSearchLocation();
        addOnClickAdd();
    }
    public void addOnClickSearchLocation(){
        final Context context = this;

        searchlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MapsActivity.class);
                startActivity(intent);
            }
        });

    }

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
