package com.example.donatefood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity {

    EditText donor_name_input, phone_number_input, location_input, food_input, dish_name, comments_input;
    Button post_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        donor_name_input = findViewById(R.id.donor_name_input);
        phone_number_input = findViewById(R.id.phone_number_input);
        location_input = findViewById(R.id.location_input);
        food_input = findViewById(R.id.food_input);
        dish_name = findViewById(R.id.dish_name);
        comments_input = findViewById(R.id.comments_input);
        post_button = findViewById(R.id.post_button);
        post_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addInfo(donor_name_input.getText().toString().trim(),
                        location_input.getText().toString().trim(),
                        comments_input.getText().toString().trim(),
                        food_input.getText().toString().trim(),
                        dish_name.getText().toString().trim(),
                        phone_number_input.getText().toString().trim());
            }
        });
    }
}