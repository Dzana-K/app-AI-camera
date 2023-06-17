package org.pytorch.demo.objectdetection;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.common.collect.Maps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class infoActivity extends BottomBarActivity implements View.OnClickListener {

    private ImageView bicycle, car, truck, cat, dog, traffic_light, stop_sign, person;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cats);

        // Get a reference to the ScrollView
        ScrollView scrollView = findViewById(R.id.scrollView);



        // Optionally, you can scroll to a specific position within the ScrollView
        // scrollView.scrollTo(x, y);

        // If you want to programmatically scroll to the top of the ScrollView, you can use:
        // scrollView.smoothScrollTo(0, 0);

        bicycle = findViewById(R.id.bicycle);
        truck = findViewById(R.id.truck);
        person = findViewById(R.id.person);
        traffic_light = findViewById(R.id.traffic_light);
        stop_sign = findViewById(R.id.stop_sign);
        car = findViewById(R.id.car);
        cat = findViewById(R.id.cat);
        dog = findViewById(R.id.dog);

        bicycle.setOnClickListener(this);
        person.setOnClickListener(this);
        stop_sign.setOnClickListener(this);
        traffic_light.setOnClickListener(this);
        truck.setOnClickListener(this);
        car.setOnClickListener(this);
        dog.setOnClickListener(this);
        cat.setOnClickListener(this);

        setBottomBar();


    }
    @Override
    public void onClick(View v) {
        // Handle the click events here
        if (v == cat) {
            // Create an Intent to start the CatActivity
            Intent intent = new Intent(infoActivity.this, infoActivity2.class);
            startActivity(intent);
        } else if (v == dog) {
            // Create an Intent to start the DogActivity
            Intent intent = new Intent(infoActivity.this, dog.class);
            startActivity(intent);
        }

        else if (v == truck) {
            // Create an Intent to start the DogActivity
            Intent intent = new Intent(infoActivity.this, trucks.class);
            startActivity(intent);
        }
        else if (v == bicycle) {
            // Create an Intent to start the DogActivity
            Intent intent = new Intent(infoActivity.this,bicycle.class);
            startActivity(intent);
        }
        else if (v == car) {
            // Create an Intent to start the DogActivity
            Intent intent = new Intent(infoActivity.this, cars.class);
            startActivity(intent);
        }
        else if (v == traffic_light) {
            // Create an Intent to start the DogActivity
            Intent intent = new Intent(infoActivity.this, traffic_light.class);
            startActivity(intent);
        }
        else if (v == stop_sign) {
            // Create an Intent to start the DogActivity
            Intent intent = new Intent(infoActivity.this, stop_sign.class);
            startActivity(intent);
        }
        else if (v == person) {
            // Create an Intent to start the DogActivity
            Intent intent = new Intent(infoActivity.this, person.class);
            startActivity(intent);
        }
        // Handle other ImageViews similarly
    }

}



