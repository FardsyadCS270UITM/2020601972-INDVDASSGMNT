package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {
private static final String FILE_NAME1 = "BMI_RECORD(WEIGHT).txt";
    private static final String FILE_NAME2 = "BMI_RECORD(HEIGHT).txt";
    String S2,S1,text;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the references to the widgets
         EditText e1 = (EditText) findViewById(R.id.et1);
         EditText e2 = (EditText) findViewById(R.id.et2);
         TextView tv4 = (TextView) findViewById(R.id.tv4);
         TextView tv1 = (TextView) findViewById(R.id.tv1);


        findViewById(R.id.ib1).setOnClickListener(new View.OnClickListener() {

            // Logic for validation, input can't be empty
            @Override
            public void onClick(View v) {

                String str1 = e1.getText().toString();
                String str2 = e2.getText().toString();

                if(TextUtils.isEmpty(str1)){
                    e1.setError("Please enter your weight");
                    e1.requestFocus();
                    return;
                }

                if(TextUtils.isEmpty(str2)){
                    e2.setError("Please enter your height");
                    e2.requestFocus();
                    return;
                }

//Get the user values from the widget reference
                float weight = Float.parseFloat(str1);
                float height = Float.parseFloat(str2);

//Calculate BMI value

                float bmiValue = calculateBMI(weight, height);
//Define the meaning of the bmi value
                String bmiInterpretation = interpretBMI(bmiValue);

                tv4.setText(String.valueOf("BMI result="+bmiValue + "\n"+"BMI category=" + bmiInterpretation));

                String txt1 = e1.getText().toString();
                String txt2 = e2.getText().toString();

                FileOutputStream fos1 = null;
                try {
                    fos1 = openFileOutput(FILE_NAME1, MODE_PRIVATE);
                    fos1.write(txt1.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(fos1!=null){
                        try {
                            fos1.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                FileOutputStream fos2 = null;
                try {
                    fos2 = openFileOutput(FILE_NAME2, MODE_PRIVATE);
                    fos2.write(txt2.getBytes());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(fos2!=null){
                        try {
                            fos2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });
        //set internal storage
        FileInputStream fis1 = null;

        try {
            fis1 = openFileInput(FILE_NAME1);
            InputStreamReader isr = new InputStreamReader(fis1);
            BufferedReader br = new BufferedReader(isr);
            String txt;

            while((txt = br.readLine()) != null){
                e1.setText(txt);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis1 != null){
                try {
                    fis1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        FileInputStream fis2 = null;

        try {
            fis2 = openFileInput(FILE_NAME2);
            InputStreamReader isr = new InputStreamReader(fis2);
            BufferedReader br = new BufferedReader(isr);
            String txt;

            while((txt = br.readLine()) != null){
                e2.setText(txt);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis2 != null){
                try {
                    fis2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }





    }
    public void openActivity2(){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
    public void openActivity3(){
        Intent intent = new Intent(this,MainActivity3.class);
        startActivity(intent);
    }



    //Calculate BMI
   private float calculateBMI (float weight, float height) {
        if ( height % 1 != 0) {
            return (float) (weight / (height * height));
        }
        else{
            return (float) (weight / (height * height)*10000);
        }
    }


    // Interpret what BMI means
    private String interpretBMI(float bmiValue) {

        if (bmiValue < 18.4) {
            return "Underweight"+"\nHealth risk:Malnutrition risk"+"\nBMI range for underweight:18.4 & below";
        } else if (bmiValue < 24.9) {

            return "Normal weight"+"\nHealth risk:Low risk"+"\nBMI range for normal weight:18.5-24.8";
        } else if (bmiValue < 29.9) {

            return "Overweight"+"\nHealth risk:Enhanced risk"+"\nBMI range for overweight:25-29.9";
        } else if (bmiValue < 34.9) {

            return "Moderately obese"+"\nHealth risk:Medium risk"+"\nBMI range for moderately obese:30-34.9";
        } else if (bmiValue < 39.9){

            return "Severely Obese"+"\nHealth risk:High risk"+"\nBMI range for severely obese:35-39.9";
        } else{

            return "Very severely obese"+"\nHealth risk:Very high risk"+"\nBMI RANGE:40 & above";

        }
    }

}