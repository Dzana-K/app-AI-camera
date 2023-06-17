package org.pytorch.demo.objectdetection;

import static org.pytorch.demo.objectdetection.R.id.buttonGraph;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

public class infoActivity2 extends AppCompatActivity implements View.OnClickListener {
    private Button button ;
    private Button backButton;
    private TextView number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aaa);

        // Add your activity code here

        // Get a reference to the ScrollView
        ScrollView scrollView = findViewById(R.id.scrollView);
        button = findViewById(buttonGraph);
        button.setOnClickListener(this);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this) ;

        number = findViewById(R.id.number);




        // If you want to programmatically scroll to the top of the ScrollView, you can use:
        scrollView.smoothScrollTo(0, 0);
    }


    @Override
    public void onClick(View v) {
        if (v == backButton) {
            onBackPressed();
        }

        else{
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String token = sharedPreferences.getString("authToken", "");
        TrafficAlertApiClient.getTrafficAlerts(token, new TrafficAlertApiClient.ApiResponseListener() {
            @Override
            public void onSuccess(String response) {
                // Handle the successful response here
                Log.d("API", "Response: " + response);

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        JSONArray data = jsonResponse.getJSONArray("data");

                        // Create a list to store the time values and detections for the selected image
                        ArrayList<String> timeList = new ArrayList<>();
                        ArrayList<Integer> detectionList = new ArrayList<>();

                        for (int i = 0; i < data.length(); i++) {
                            JSONObject alert = data.getJSONObject(i);
                            JSONArray detections = alert.getJSONArray("detections");

                            // Extract the detection values
                            int[] detectionsArray = new int[detections.length()];
                            for (int j = 0; j < detections.length(); j++) {
                                detectionsArray[j] = detections.getInt(j);
                            }

                            String time = alert.getString("time");
                            Log.d("Time", time);

                            // Determine which ImageView triggered the intent

                                    timeList.add(time);
                                    detectionList.add(detectionsArray[3]);


                        }
                        int sum = 0;
                        for (int value : detectionList) {
                            sum += value;
                        }

                        // Update the TextView with the sum

                        number.setText("You saw: " + sum+"cats.");


                        // Create an Intent and pass the time list and detection list to DataActivity
                        Intent intent = new Intent(infoActivity2.this, DataActivity.class);
                        intent.putStringArrayListExtra("timeList", timeList);
                        intent.putIntegerArrayListExtra("detectionList", detectionList);
                        startActivity(intent);
                    } else {
                        // Handle the case when success is false
                    }
                } catch (JSONException e) {
                    Log.e("EXCEPTION", e.toString());
                }

            }

            @Override
            public void onFailure(String errorMessage) {
                // Handle the failure here
                Log.e("API", "Error: " + errorMessage);
            }
        });
}}}
