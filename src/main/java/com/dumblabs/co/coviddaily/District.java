package com.dumblabs.co.coviddaily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class District extends AppCompatActivity {


    Button backButton;

    LinearLayout precautionLinearLayout, symptomsLinearLayout;

    Intent intent;
    TextView stateTitleTextView;
    TextView stateTotalTextView;
    TextView stateTotalDeltaTextView;
    TextView stateActiveTextView;
    TextView stateActiveDeltaTextView;
    TextView stateRecoveredTextView;
    TextView stateRecoveredDeltaTextView;
    TextView stateDeceasedTextView;
    TextView stateDeceasedDetlaTextView;


    public void setShareButton(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "COVID-19 situation in "+intent.getExtras().getString("Title")+"-\nTotal: "+intent.getExtras().getString("Total")+"\nActive: "+intent.getExtras().getString("active")+"\nDeceased: "+intent.getExtras().getString("death")+"\nNew Cases reported today: "+intent.getExtras().getString("totaldelta")+"\nFor more information kindly download our app: https://dumblabs-co.github.io/coviddaily");
        sendIntent.setType("text/plain");
        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
    }

    public void setBackButton(View view){
        onBackPressed();
    }

    public void setPrecautionLinearLayout(View view){
        Intent intent= new Intent(this,Precautions.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_right_to_left,R.anim.no_animation_exit);
    }

    public void setSymptomsLinearLayout(View view){
        Intent intent= new Intent(this,Symptoms.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_right_to_left,R.anim.no_animation_exit);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);

        backButton= findViewById(R.id.backButton);


        precautionLinearLayout= findViewById(R.id.precautionsLinearLayout);
        symptomsLinearLayout= findViewById(R.id.symptomsLinearLayout);

        stateTitleTextView = findViewById(R.id.districtTitleTextView);
        stateTotalTextView= findViewById(R.id.distTotalTextView);
        stateTotalDeltaTextView= findViewById(R.id.distTotalDeltaTextView);
        stateActiveTextView=findViewById(R.id.distActiveTextView);
        stateActiveDeltaTextView=findViewById(R.id.distActiveDeltaTextView);
        stateRecoveredTextView=findViewById(R.id.distRecoveredTextView);
        stateRecoveredDeltaTextView=findViewById(R.id.distRecoveredDeltaTextView);
        stateDeceasedTextView=findViewById(R.id.distDeceasedTextView);
        stateDeceasedDetlaTextView=findViewById(R.id.distDeceasedDetlaTextView);


        intent= getIntent();
        stateTitleTextView.setText(intent.getExtras().getString("Title"));
        stateTotalTextView.setText(intent.getExtras().getString("Total"));
        stateTotalDeltaTextView.setText("+"+intent.getExtras().getString("totaldelta"));
        stateActiveTextView.setText(intent.getExtras().getString("active"));
        stateRecoveredTextView.setText(intent.getExtras().getString("recovered"));
        stateRecoveredDeltaTextView.setText("+"+intent.getExtras().getString("recovereddelta"));
        stateDeceasedTextView.setText(intent.getExtras().getString("death"));
        stateDeceasedDetlaTextView.setText("+"+intent.getExtras().getString("deathdelta"));
        String aD= intent.getExtras().getString("activedelta");
        if (aD.contains("-")) {
            stateActiveDeltaTextView.setText(aD);
        }
        else{
            stateActiveDeltaTextView.setText("+"+aD);
        }


    }
}