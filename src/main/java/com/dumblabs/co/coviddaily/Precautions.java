package com.dumblabs.co.coviddaily;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Precautions extends AppCompatActivity {

    Button backButton;

    public void setBackButton(View view){
        onBackPressed();
        overridePendingTransition(R.anim.no_animation_enter,R.anim.exit_left_to_right);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_animation_enter,R.anim.exit_left_to_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_precautions);

        backButton=findViewById(R.id.backButton);

    }
}