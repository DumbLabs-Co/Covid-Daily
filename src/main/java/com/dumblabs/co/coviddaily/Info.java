package com.dumblabs.co.coviddaily;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Info extends AppCompatActivity {

    Button backButton;

    TextView info2;

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
        setContentView(R.layout.activity_info);

        backButton= findViewById(R.id.backButton);
        info2= findViewById(R.id.info2);
        info2.setMovementMethod(LinkMovementMethod.getInstance());
    }
}