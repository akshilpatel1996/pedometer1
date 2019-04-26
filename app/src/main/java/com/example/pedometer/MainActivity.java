package com.example.pedometer;

import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private TextView textView;
    private SensorManager sensorManager;
    private Sensor accel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv_steps);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);


    }


    @Override
    protected void onResume() {
        super.onResume();
        if (accel != null) {
            sensorManager.registerListener(this, accel, SensorManager.SENSOR_DELAY_UI);
        } else {
            Toast.makeText(getApplicationContext(), "Sensor is not available", Toast.LENGTH_SHORT).show();
            ;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        textView.setText("Steps: " + event.values[0]);
    }


}

