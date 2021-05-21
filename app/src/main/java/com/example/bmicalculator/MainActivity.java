package com.example.bmicalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText weight, height;
    TextView resulttest;
    String calculation, BMIresult;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        resulttest = findViewById(R.id.result);
        btn =findViewById(R.id.calculate_button);

        btn.setOnClickListener(v -> {
            try {
                calculateBMI();
                hideSoftKeyboard(MainActivity.this);
            } catch (Exception e){
                Toast.makeText(MainActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        if(inputMethodManager.isAcceptingText()){
            inputMethodManager.hideSoftInputFromWindow(
                    activity.getCurrentFocus().getWindowToken(),
                    0
            );
        }
    }


    private void calculateBMI() {
        String S1 = weight.getText().toString();
        String S2 = height.getText().toString();

        float weightValue = Float.parseFloat(S1);
        float heightValue = Float.parseFloat(S2);

        float bmi = weightValue / ((heightValue / 100) * (heightValue / 100));
        if (bmi <= 15){
            BMIresult = "severely Under weight";
        }else if (bmi >= 15 && bmi <= 18.5){
            BMIresult = "Under weight";
        }else if (bmi >=18.5 && bmi <= 25){
            BMIresult="normal weight";
        }else if (bmi >= 25 && bmi<30){
            BMIresult = "Overweight";
        }else{
            BMIresult = "Obese";
        }

        calculation = "Result:\n\n" + bmi + "\n" + BMIresult;

        resulttest.setText(calculation);
        resulttest.setVisibility(View.VISIBLE);

    }
}