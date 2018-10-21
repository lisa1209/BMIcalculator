package com.example.bmicalculator;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button calculateButton = findViewById(R.id.calculate_button);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText heightEditText = findViewById(R.id.height_edit_text);
                EditText weightEditText = findViewById(R.id.weight_edit_text);
                int h = Integer.parseInt(heightEditText.getText().toString());
                int w = Integer.parseInt(weightEditText.getText().toString());
                float bmi = calculateBmi(h, w);

                String resultText = null;
                if (bmi < 18.5) {
                    resultText = "ผอมเกินไป";
                } else if (bmi < 25) {
                    resultText = "น้ำหนักปกติ ไม่อ้วนไม่ผอม";
                } else if (bmi < 30) {
                    resultText = "อ้วน";
                } else {
                    resultText = "อ้วนมาก";
                }

                //String msg = "ค่า BMI เท่ากับ " + String.format(Locale.US, "%.2f", bmi);
                String msg = "น้ำหนักของคุณอยู่ในเกณฑ์: " + resultText;

                Toast.makeText(
                        MainActivity.this,
                        msg,
                        Toast.LENGTH_LONG
                ).show();

                //Method chaining
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Result")
                        .setMessage(msg)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //todo: โค้ดที่ให้ทำ เมื่อ user คลิก OK ในไดอะล็อก
                            }
                        })
                        .show();
            }
        });
    }

    private float calculateBmi(int heightInCm, int weightInKg) {
        // สูตรคำนวณ bmi = kg / m^2
        float height = heightInCm / 100f;
        Log.i(TAG, "ความสูงหน่วยเมตร = " + String.valueOf(height));

        float bmi = weightInKg / (height * height);
        return bmi;
    }

}
