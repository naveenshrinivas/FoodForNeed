package com.example.donatefood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;

    EditText donor_name_input, phone_number_input, location_input, food_input, dish_name, comments_input;
    Button share_button, call_button;
    String id, name, location, typeoffood, mobilenumber, nameofthedish, commentsforpost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        donor_name_input = findViewById(R.id.donor_name_input2);
        phone_number_input = findViewById(R.id.phone_number_input2);
        location_input = findViewById(R.id.location_input2);
        food_input = findViewById(R.id.food_input2);
        dish_name = findViewById(R.id.dish_name2);
        comments_input = findViewById(R.id.comments_input2);
        call_button = findViewById(R.id.call_button);
        call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });



        share_button = findViewById(R.id.share_button);
        share_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();

            }
        });
        getAndSetIntentData();
    }

    private void makePhoneCall() {
        String callnumber = phone_number_input.getText().toString();
        if (callnumber.trim().length() > 0) {

            if(ContextCompat.checkSelfPermission(UpdateActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(UpdateActivity.this,
                        new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + callnumber;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(UpdateActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permisson DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendMail() {
        name = donor_name_input.getText().toString();
        location = location_input.getText().toString();
        mobilenumber = phone_number_input.getText().toString();
        typeoffood = food_input.getText().toString();
        nameofthedish = dish_name.getText().toString();
        commentsforpost = comments_input.getText().toString();
        String body = "Name: " + name + "\n" + "Location: " + location + "\n" + "Mobile no: " + mobilenumber + "\n" + "Food Type: " + typeoffood + "\n" + "Dish Name: " + nameofthedish + "\n" + "Comments: " + commentsforpost;
        String subject = "Donate Food! - Make others happy!";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "Choose an email client"));
    }

    void getAndSetIntentData() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("location") && getIntent().hasExtra("typeoffood") && getIntent().hasExtra("mobilenumber") && getIntent().hasExtra("nameofthedish") && getIntent().hasExtra("commentsforpost")) {
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            location = getIntent().getStringExtra("location");
            typeoffood = getIntent().getStringExtra("typeoffood");
            mobilenumber = getIntent().getStringExtra("mobilenumber");
            nameofthedish = getIntent().getStringExtra("nameofthedish");
            commentsforpost = getIntent().getStringExtra("commentsforpost");

            //Setting Intent Data
            donor_name_input.setText(name);
            location_input.setText(location);
            food_input.setText(typeoffood);
            phone_number_input.setText(mobilenumber);
            dish_name.setText(nameofthedish);
            comments_input.setText(commentsforpost);
        }else {
            Toast.makeText(this, "No data!", Toast.LENGTH_SHORT).show();
        }
    }
}