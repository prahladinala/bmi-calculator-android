package com.prahladinala.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BmiActivity extends AppCompatActivity {

    android.widget.Button mrecalculatebmi;

    TextView mbmidisplay, mbmicategory, mgender;
    Intent intent;
    ImageView mimageview;

    String mbmi;
    float intbmi;

    String height;
    String weight;
    float intheight, intweight;

    RelativeLayout mbackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        // getSupportActionBar().hide();
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"white\"></font>"));
        getSupportActionBar().setTitle("Result");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#1E1D1D"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        // GET VALUES FROM INTENT (PASSED)
        intent = getIntent();

        mbmidisplay = findViewById(R.id.bmidisplay);
        mbmicategory = findViewById(R.id.bmicatdisplay);
        mgender = findViewById(R.id.genderdisplay);
        mbackground = findViewById(R.id.contentlayout);
        mimageview = findViewById(R.id.imageview);

        mrecalculatebmi = findViewById(R.id.recalculatebmi);

        height = intent.getStringExtra("height");
        weight = intent.getStringExtra("weight");

        // HEIGHT TO FLOAT
        intheight = Float.parseFloat(height);
        // WEIGHT TO FLOAT
        intweight = Float.parseFloat(weight);

        // AS WE CALCULATE BMI IN METER
        intheight = intheight/100;

        intbmi  = intweight/(intheight*intheight);

        mbmi = Float.toString(intbmi);

        if(intbmi < 16){
            mbmicategory.setText("Severe Thinness");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.crosss);
        } else if(intbmi<16.9 && intbmi>16){
            mbmicategory.setText("Moderate Thinness");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.warning);
        } else if(intbmi<18.4 && intbmi>17){
            mbmicategory.setText("Mild Thinness");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.warning);
        } else if(intbmi<25 && intbmi>18.4){
            mbmicategory.setText("Normal");
            // mbackground.setBackgroundColor(Color.YELLOW);
            mimageview.setImageResource(R.drawable.ok);
        } else if(intbmi<29.4 && intbmi>25){
            mbmicategory.setText("Overweight");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.warning);
        } else {
            mbmicategory.setText("Obese Class I");
            mbackground.setBackgroundColor(Color.RED);
            mimageview.setImageResource(R.drawable.crosss);
        }

        mgender.setText(intent.getStringExtra("gender"));
        mbmidisplay.setText(mbmi);


        mrecalculatebmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BmiActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}