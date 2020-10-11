package com.example.donatefood;

import androidx.appcompat.app.AppCompatActivity;


import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;


import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class mainpage extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    MyDatabaseHelper myDB;
    ArrayList<String> info_id, donor_name, location_donor, phone_no, dish_name, comments, food_type;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mainpage.this, AddActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(mainpage.this);
        info_id = new ArrayList<>();
        donor_name = new ArrayList<>();
        location_donor = new ArrayList<>();
        food_type = new ArrayList<>();
        phone_no = new ArrayList<>();
        dish_name = new ArrayList<>();
        comments = new ArrayList<>();

        storeDataInArrays();



        customAdapter = new CustomAdapter(mainpage.this, info_id, donor_name, location_donor, food_type, phone_no, dish_name, comments);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mainpage.this));

    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No Data!", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext() ) {
                info_id.add(cursor.getString(0));
                donor_name.add(cursor.getString(1));
                location_donor.add(cursor.getString(2));
                phone_no.add(cursor.getString(3));
                food_type.add(cursor.getString(4));
                dish_name.add(cursor.getString(5));
                comments.add(cursor.getString(6));
            }
        }
    }
}