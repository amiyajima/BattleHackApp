package com.example.annamiyajima.mapstest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;


import java.util.ArrayList;

import com.firebase.client.Firebase;

public class qTimesActivity extends ActionBarActivity {

    private TextView header;
    private ListView list;
    private Button back;
    private String restaurantNames;
    private ArrayList<String> toAdd = new ArrayList<String>();
    private ArrayAdapter<String> adapter;


    int numUsers;
    int numRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_times);

        header = (TextView) findViewById(R.id.header);
        list = (ListView) findViewById(R.id.list);
        back = (Button) findViewById(R.id.back);

        Bundle extras = getIntent().getExtras();
        restaurantNames = extras.getString("name");
        header.setText("Waiting Times for" + " " +restaurantNames);


        addItems();
        addOnClickBack();

    }
//    method for dynamic insertion
    public void addItems(){
        //for statement iterating over info and adding each to listViewArray
        toAdd.add("20 min for party of 4");
        toAdd.add("25 min for party of 5");
        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                toAdd);
        list.setAdapter(adapter);
    }



    public void addOnClickBack(){

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_q_times, menu);
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
