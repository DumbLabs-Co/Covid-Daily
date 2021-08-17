package com.dumblabs.co.coviddaily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class StateActivity extends AppCompatActivity {


    String active;
    String confirmed;
    String deceased;
    String recovered;
    String comfirmedDelta;
    String deceasedDelta;
    String recoveredDelta;
    int ad;
    String activeDelta;

    Button shareButton;
    Button backButton;
    TextView stateTitleTextView;
    TextView stateTotalTextView;
    TextView stateTotalDeltaTextView;
    TextView stateActiveTextView;
    TextView stateActiveDeltaTextView;
    TextView stateRecoveredTextView;
    TextView stateRecoveredDeltaTextView;
    TextView stateDeceasedTextView;
    TextView stateDeceasedDetlaTextView;

    LinearLayout precautionLinearLayout, symptomsLinearLayout;

    String title;
    String statecode;

    Intent intent;

    RequestQueue queue2;
    String url2="https://data.covid19india.org/state_district_wise.json";
    JsonObjectRequest jsonObjectRequest2;


    ListAdapterDistrict adapterDistrict;
    RecyclerView stateRecyclerView;

    List <ListElementsDistrict> districtList;
    List <ListElementsDistrict> un;
    List <ListElementsDistrict> an;
    List <ListElementsDistrict> ap;
    List <ListElementsDistrict> ar;
    List <ListElementsDistrict> as;
    List <ListElementsDistrict> br;
    List <ListElementsDistrict> ch;
    List <ListElementsDistrict> ct;
    List <ListElementsDistrict> dl;
    List <ListElementsDistrict> dn;
    List <ListElementsDistrict> ga;
    List <ListElementsDistrict> gj;
    List <ListElementsDistrict> hp;
    List <ListElementsDistrict> hr;
    List <ListElementsDistrict> jh;
    List <ListElementsDistrict> jk;
    List <ListElementsDistrict> ka;
    List <ListElementsDistrict> kl;
    List <ListElementsDistrict> la;
    List <ListElementsDistrict> ld;
    List <ListElementsDistrict> mh;
    List <ListElementsDistrict> ml;
    List <ListElementsDistrict> mn;
    List <ListElementsDistrict> mp;
    List <ListElementsDistrict> mz;
    List <ListElementsDistrict> nl;
    List <ListElementsDistrict> or;
    List <ListElementsDistrict> pb;
    List <ListElementsDistrict> py;
    List <ListElementsDistrict> rj;
    List <ListElementsDistrict> sk;
    List <ListElementsDistrict> tg;
    List <ListElementsDistrict> tn;
    List <ListElementsDistrict> tr;
    List <ListElementsDistrict> up;
    List <ListElementsDistrict> ut;
    List <ListElementsDistrict> wb;





    public void setShareButton(View view) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "COVID-19 situation in "+title+"-\nTotal: "+intent.getExtras().getString("Total")+"\nActive: "+intent.getExtras().getString("active")+"\nDeceased: "+intent.getExtras().getString("death")+"\nNew Cases reported today: "+intent.getExtras().getString("totaldelta")+"\nFor more information kindly download our app: https://dumblabs-co.github.io/coviddaily");
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
        setContentView(R.layout.activity_state);

        shareButton= findViewById(R.id.infoButton);
        backButton= findViewById(R.id.backButton);
        stateTitleTextView = findViewById(R.id.stateTitleTextView);
        stateTotalTextView= findViewById(R.id.distTotalTextView);
        stateTotalDeltaTextView= findViewById(R.id.distTotalDeltaTextView);
        stateActiveTextView=findViewById(R.id.distActiveTextView);
        stateActiveDeltaTextView=findViewById(R.id.distActiveDeltaTextView);
        stateRecoveredTextView=findViewById(R.id.distRecoveredTextView);
        stateRecoveredDeltaTextView=findViewById(R.id.distRecoveredDeltaTextView);
        stateDeceasedTextView=findViewById(R.id.distDeceasedTextView);
        stateDeceasedDetlaTextView=findViewById(R.id.distDeceasedDetlaTextView);

        precautionLinearLayout= findViewById(R.id.precautionsLinearLayout);
        symptomsLinearLayout= findViewById(R.id.symptomsLinearLayout);


        districtList= new ArrayList<>();
        un = new ArrayList<>();
        an = new ArrayList<>();
        ap = new ArrayList<>();
        ar = new ArrayList<>();
        as = new ArrayList<>();
        br = new ArrayList<>();
        ch = new ArrayList<>();
        ct = new ArrayList<>();
        dl = new ArrayList<>();
        dn = new ArrayList<>();
        ga = new ArrayList<>();
        gj = new ArrayList<>();
        hp = new ArrayList<>();
        hr = new ArrayList<>();
        jh = new ArrayList<>();
        jk = new ArrayList<>();
        ka = new ArrayList<>();
        kl = new ArrayList<>();
        la = new ArrayList<>();
        ld = new ArrayList<>();
        mh = new ArrayList<>();
        ml = new ArrayList<>();
        mn = new ArrayList<>();
        mp = new ArrayList<>();
        mz = new ArrayList<>();
        nl = new ArrayList<>();
        or = new ArrayList<>();
        pb = new ArrayList<>();
        py = new ArrayList<>();
        rj = new ArrayList<>();
        sk = new ArrayList<>();
        tg = new ArrayList<>();
        tn = new ArrayList<>();
        tr = new ArrayList<>();
        up = new ArrayList<>();
        ut = new ArrayList<>();
        wb = new ArrayList<>();



        stateRecyclerView = findViewById(R.id.stateRecyclerView);
        stateRecyclerView.setHasFixedSize(true);
        stateRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        intent= getIntent();
        title= intent.getExtras().getString("Title");
        stateTitleTextView.setText(title);
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
        statecode= intent.getExtras().getString("statecode");



        volleyRequest2();

    }



    public void volleyRequest2()
    {
        queue2 = Volley.newRequestQueue(this);

        jsonObjectRequest2= new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                if (statecode.contains("RJ")){
                    rajasthan(response);
                }
                else if (statecode.contains("MH")){
                    maharastra(response);
                }
                else if (statecode.contains("TN")){
                    tamilNadu(response);
                }
                else if (statecode.contains("DL")){
                    delhi(response);
                }
                else if (statecode.contains("KA")){
                    karnataka(response);
                }
                else if (statecode.contains("AP")){
                    andhraPradesh(response);
                }
                else if (statecode.contains("UP")){
                    uttarPradesh(response);
                }
                else if (statecode.contains("GJ")){
                    gujrat(response);
                }
                else if (statecode.contains("WB")){
                    westBengal(response);
                }
                else if (statecode.contains("TG")){
                    telangana(response);
                }
                else if (statecode.contains("BR")){
                    bihar(response);
                }
                else if (statecode.contains("HR")){
                    harayana(response);
                }
                else if (statecode.contains("AS")){
                    assam(response);
                }
                else if (statecode.contains("MP")){
                    madhyaPradesh(response);
                }
                else if (statecode.contains("OR")){
                    odisha(response);
                }
                else if (statecode.contains("JK")){
                    jammuAndKashmir(response);
                }
                else if (statecode.contains("KL")){
                    kerala(response);
                }
                else if (statecode.contains("PB")){
                    punjab(response);
                }
                else if (statecode.contains("JH")){
                    jharkhand(response);
                }
                else if (statecode.contains("CT")){
                    chattisgarh(response);
                }
                else if (statecode.contains("UT")){
                    uttarakhand(response);
                }
                else if (statecode.contains("GA")){
                    goa(response);
                }
                else if (statecode.contains("TR")){
                    tripura(response);
                }
                else if (statecode.contains("PY")){
                    puducherry(response);
                }
                else if (statecode.contains("MN")){
                    manipur(response);
                }
                else if (statecode.contains("HP")){
                    himachalPradesh(response);
                }
                else if (statecode.contains("LA")){
                    ladakh(response);
                }
                else if (statecode.contains("NL")){
                    nagaland(response);
                }
                else if (statecode.contains("AR")){
                    arunachalPradesh(response);
                }
                else if (statecode.contains("CH")){
                    chandigarh(response);
                }
                else if (statecode.contains("DN")){
                    dadarAndNagarHaveli(response);
                }
                else if (statecode.contains("ML")){
                    megalaya(response);
                }
                else if (statecode.contains("SK")){
                    sikkim(response);
                }
                else if (statecode.contains("MZ")){
                    mizoram(response);
                }
                else if (statecode.contains("AN")){
                    andaman(response);
                }
                else if (statecode.contains("UN")){
                    stateUnassigned(response);
                }
                else if (statecode.contains("LD")){
                    lakshadweep(response);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StateActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        queue2.add(jsonObjectRequest2);



    }

    public void maharastra(JSONObject response){
        try {
            JSONObject MH = response.getJSONObject("Maharashtra");
            JSONObject districtData = MH.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Ahmednagar");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Ahmednagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Akola");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Akola", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Amravati");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Amravati", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Aurangabad");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Aurangabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Beed");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Beed", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Bhandara");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Bhandara", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Buldhana");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Buldhana", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Chandrapur");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Chandrapur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Dhule");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Dhule", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Gadchiroli");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Gadchiroli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Gondia");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Gondia", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Hingoli");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Hingoli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Jalgaon");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Jalgaon", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Jalna");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Jalna", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Kolhapur");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Kolhapur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Latur");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Latur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Mumbai");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Mumbai", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Mumbai Suburban");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Mumbai Suburban", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Nagpur");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Nagpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Nanded");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Nanded", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Nandurbar");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Nandurbar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Nashik");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Nashik", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Osmanabad");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Osmanabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("Other State");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Other State", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district25 = districtData.getJSONObject("Palghar");
            active = district25.getString("active");
            confirmed = district25.getString("confirmed");
            deceased = district25.getString("deceased");
            recovered = district25.getString("recovered");
            delta = district25.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Palghar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district26 = districtData.getJSONObject("Parbhani");
            active = district26.getString("active");
            confirmed = district26.getString("confirmed");
            deceased = district26.getString("deceased");
            recovered = district26.getString("recovered");
            delta = district26.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Parbhani", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district27 = districtData.getJSONObject("Pune");
            active = district27.getString("active");
            confirmed = district27.getString("confirmed");
            deceased = district27.getString("deceased");
            recovered = district27.getString("recovered");
            delta = district27.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Pune", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district28 = districtData.getJSONObject("Raigad");
            active = district28.getString("active");
            confirmed = district28.getString("confirmed");
            deceased = district28.getString("deceased");
            recovered = district28.getString("recovered");
            delta = district28.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Raigad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district29 = districtData.getJSONObject("Ratnagiri");
            active = district29.getString("active");
            confirmed = district29.getString("confirmed");
            deceased = district29.getString("deceased");
            recovered = district29.getString("recovered");
            delta = district29.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Ratnagiri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district30 = districtData.getJSONObject("Sangli");
            active = district30.getString("active");
            confirmed = district30.getString("confirmed");
            deceased = district30.getString("deceased");
            recovered = district30.getString("recovered");
            delta = district30.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Sangli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district31 = districtData.getJSONObject("Satara");
            active = district31.getString("active");
            confirmed = district31.getString("confirmed");
            deceased = district31.getString("deceased");
            recovered = district31.getString("recovered");
            delta = district31.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Satara", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district32 = districtData.getJSONObject("Sindhudurg");
            active = district32.getString("active");
            confirmed = district32.getString("confirmed");
            deceased = district32.getString("deceased");
            recovered = district32.getString("recovered");
            delta = district32.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Sindhudurg", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district33 = districtData.getJSONObject("Solapur");
            active = district33.getString("active");
            confirmed = district33.getString("confirmed");
            deceased = district33.getString("deceased");
            recovered = district33.getString("recovered");
            delta = district33.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Solapur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district34 = districtData.getJSONObject("Thane");
            active = district34.getString("active");
            confirmed = district34.getString("confirmed");
            deceased = district34.getString("deceased");
            recovered = district34.getString("recovered");
            delta = district34.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Thane", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district35 = districtData.getJSONObject("Wardha");
            active = district35.getString("active");
            confirmed = district35.getString("confirmed");
            deceased = district35.getString("deceased");
            recovered = district35.getString("recovered");
            delta = district35.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Wardha", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district36 = districtData.getJSONObject("Washim");
            active = district36.getString("active");
            confirmed = district36.getString("confirmed");
            deceased = district36.getString("deceased");
            recovered = district36.getString("recovered");
            delta = district36.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Washim", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district37 = districtData.getJSONObject("Yavatmal");
            active = district37.getString("active");
            confirmed = district37.getString("confirmed");
            deceased = district37.getString("deceased");
            recovered = district37.getString("recovered");
            delta = district37.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mh.add(new ListElementsDistrict("Yavatmal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, mh);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void tamilNadu(JSONObject response){
        try {
            JSONObject TN = response.getJSONObject("Tamil Nadu");
            JSONObject districtData = TN.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district4 = districtData.getJSONObject("Ariyalur");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Ariyalur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Chengalpattu");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Chengalpattu", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Chennai");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Chennai", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Coimbatore");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Coimbatore", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Cuddalore");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Cuddalore", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Dharmapuri");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Dharmapuri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Dindigul");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Dindigul", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Erode");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Erode", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Kallakurichi");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Kallakurichi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Kancheepuram");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Kancheepuram", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Kanyakumari");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Kanyakumari", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Karur");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Karur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Krishnagiri");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Krishnagiri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Madurai");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Madurai", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Nagapattinam");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Nagapattinam", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Namakkal");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Namakkal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Nilgiris");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Nilgiris", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Perambalur");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Perambalur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Pudukkottai");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Pudukkottai", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Ramanathapuram");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Ramanathapuram", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("Ranipet");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Ranipet", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district25 = districtData.getJSONObject("Salem");
            active = district25.getString("active");
            confirmed = district25.getString("confirmed");
            deceased = district25.getString("deceased");
            recovered = district25.getString("recovered");
            delta = district25.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Salem", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district26 = districtData.getJSONObject("Sivaganga");
            active = district26.getString("active");
            confirmed = district26.getString("confirmed");
            deceased = district26.getString("deceased");
            recovered = district26.getString("recovered");
            delta = district26.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Sivaganga", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district27 = districtData.getJSONObject("Tenkasi");
            active = district27.getString("active");
            confirmed = district27.getString("confirmed");
            deceased = district27.getString("deceased");
            recovered = district27.getString("recovered");
            delta = district27.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Tenkasi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district28 = districtData.getJSONObject("Thanjavur");
            active = district28.getString("active");
            confirmed = district28.getString("confirmed");
            deceased = district28.getString("deceased");
            recovered = district28.getString("recovered");
            delta = district28.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Thanjavur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district29 = districtData.getJSONObject("Theni");
            active = district29.getString("active");
            confirmed = district29.getString("confirmed");
            deceased = district29.getString("deceased");
            recovered = district29.getString("recovered");
            delta = district29.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Theni", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district30 = districtData.getJSONObject("Thiruvallur");
            active = district30.getString("active");
            confirmed = district30.getString("confirmed");
            deceased = district30.getString("deceased");
            recovered = district30.getString("recovered");
            delta = district30.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Thiruvallur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district31 = districtData.getJSONObject("Thiruvarur");
            active = district31.getString("active");
            confirmed = district31.getString("confirmed");
            deceased = district31.getString("deceased");
            recovered = district31.getString("recovered");
            delta = district31.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Thiruvarur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district32 = districtData.getJSONObject("Thoothukkudi");
            active = district32.getString("active");
            confirmed = district32.getString("confirmed");
            deceased = district32.getString("deceased");
            recovered = district32.getString("recovered");
            delta = district32.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Thoothukkudi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district33 = districtData.getJSONObject("Tiruchirappalli");
            active = district33.getString("active");
            confirmed = district33.getString("confirmed");
            deceased = district33.getString("deceased");
            recovered = district33.getString("recovered");
            delta = district33.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Tiruchirappalli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district34 = districtData.getJSONObject("Tirunelveli");
            active = district34.getString("active");
            confirmed = district34.getString("confirmed");
            deceased = district34.getString("deceased");
            recovered = district34.getString("recovered");
            delta = district34.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Tirunelveli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district35 = districtData.getJSONObject("Tirupathur");
            active = district35.getString("active");
            confirmed = district35.getString("confirmed");
            deceased = district35.getString("deceased");
            recovered = district35.getString("recovered");
            delta = district35.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Tirupathur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district36 = districtData.getJSONObject("Tiruppur");
            active = district36.getString("active");
            confirmed = district36.getString("confirmed");
            deceased = district36.getString("deceased");
            recovered = district36.getString("recovered");
            delta = district36.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Tiruppur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district37 = districtData.getJSONObject("Tiruvannamalai");
            active = district37.getString("active");
            confirmed = district37.getString("confirmed");
            deceased = district37.getString("deceased");
            recovered = district37.getString("recovered");
            delta = district37.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Tiruvannamalai", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district38 = districtData.getJSONObject("Vellore");
            active = district38.getString("active");
            confirmed = district38.getString("confirmed");
            deceased = district38.getString("deceased");
            recovered = district38.getString("recovered");
            delta = district38.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Vellore", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district39 = districtData.getJSONObject("Viluppuram");
            active = district39.getString("active");
            confirmed = district39.getString("confirmed");
            deceased = district39.getString("deceased");
            recovered = district39.getString("recovered");
            delta = district39.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Viluppuram", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district40 = districtData.getJSONObject("Virudhunagar");
            active = district40.getString("active");
            confirmed = district40.getString("confirmed");
            deceased = district40.getString("deceased");
            recovered = district40.getString("recovered");
            delta = district40.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Virudhunagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district1 = districtData.getJSONObject("Railway Quarantine");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Railway Quarantine", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Airport Quarantine");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Airport Quarantine", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Other State");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tn.add(new ListElementsDistrict("Other State", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this,tn);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void delhi(JSONObject response){
        try {
            JSONObject DL = response.getJSONObject("Delhi");
            JSONObject districtData = DL.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Central Delhi");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dl.add(new ListElementsDistrict("Central Delhi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("East Delhi");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dl.add(new ListElementsDistrict("East Delhi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("New Delhi");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dl.add(new ListElementsDistrict("New Delhi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("North Delhi");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dl.add(new ListElementsDistrict("North Delhi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("North East Delhi");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dl.add(new ListElementsDistrict("North East Delhi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("North West Delhi");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dl.add(new ListElementsDistrict("North West Delhi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Shahdara");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dl.add(new ListElementsDistrict("Shahdara", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("South Delhi");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dl.add(new ListElementsDistrict("South Delhi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("South East Delhi");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dl.add(new ListElementsDistrict("South East Delhi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("South West Delhi");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dl.add(new ListElementsDistrict("South West Delhi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("West Delhi");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dl.add(new ListElementsDistrict("West Delhi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, dl);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void karnataka(JSONObject response){
        try {
            JSONObject KA = response.getJSONObject("Karnataka");
            JSONObject districtData = KA.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Bagalkote");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Bagalkote", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Ballari");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Ballari", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Belagavi");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Belagavi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Bengaluru Rural");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Bengaluru Rural", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Bengaluru Urban");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Bengaluru Urban", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Bidar");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Bidar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Chamarajanagara");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Chamarajanagara", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Chikkaballapura");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Chikkaballapura", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Chikkamagaluru");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Chikkamagaluru", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Chitradurga");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Chitradurga", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Dakshina Kannada");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Dakshina Kannada", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Davanagere");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Davanagere", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Dharwad");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Dharwad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Gadag");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Gadag", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Hassan");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Hassan", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Haveri");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Haveri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Kalaburagi");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Kalaburagi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Kodagu");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Kodagu", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Kolar");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Kolar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Koppal");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Koppal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Mandya");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Mandya", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Mysuru");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Mysuru", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("Raichur");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Raichur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district25 = districtData.getJSONObject("Ramanagara");
            active = district25.getString("active");
            confirmed = district25.getString("confirmed");
            deceased = district25.getString("deceased");
            recovered = district25.getString("recovered");
            delta = district25.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Ramanagara", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district26 = districtData.getJSONObject("Shivamogga");
            active = district26.getString("active");
            confirmed = district26.getString("confirmed");
            deceased = district26.getString("deceased");
            recovered = district26.getString("recovered");
            delta = district26.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Shivamogga", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district27 = districtData.getJSONObject("Tumakuru");
            active = district27.getString("active");
            confirmed = district27.getString("confirmed");
            deceased = district27.getString("deceased");
            recovered = district27.getString("recovered");
            delta = district27.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Tumakuru", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district28 = districtData.getJSONObject("Udupi");
            active = district28.getString("active");
            confirmed = district28.getString("confirmed");
            deceased = district28.getString("deceased");
            recovered = district28.getString("recovered");
            delta = district28.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Udupi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district29 = districtData.getJSONObject("Uttara Kannada");
            active = district29.getString("active");
            confirmed = district29.getString("confirmed");
            deceased = district29.getString("deceased");
            recovered = district29.getString("recovered");
            delta = district29.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Uttara Kannada", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district30 = districtData.getJSONObject("Vijayapura");
            active = district30.getString("active");
            confirmed = district30.getString("confirmed");
            deceased = district30.getString("deceased");
            recovered = district30.getString("recovered");
            delta = district30.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Vijayapura", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district31 = districtData.getJSONObject("Yadgir");
            active = district31.getString("active");
            confirmed = district31.getString("confirmed");
            deceased = district31.getString("deceased");
            recovered = district31.getString("recovered");
            delta = district31.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Yadgir", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Other State");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ka.add(new ListElementsDistrict("Other State", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, ka);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void andhraPradesh(JSONObject response){
        try {
            JSONObject AP = response.getJSONObject("Andhra Pradesh");
            JSONObject districtData = AP.getJSONObject("districtData");
            JSONObject delta;



            JSONObject district2 = districtData.getJSONObject("Anantapur");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("Anantapur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Chittoor");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("Chittoor", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("East Godavari");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("East Godavari", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Guntur");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("Guntur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Krishna");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("Krishna", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Kurnool");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("Kurnool", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Other State");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("Other State", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Prakasam");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("Prakasham", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("S.P.S. Nellore");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("S.P.S. Nellore", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Srikakulam");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("Srikakalum", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Visakhapatnam");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("Visakhapatnam", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Vizianagaram");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("Vizianagaram", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("West Godavari");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("West Godavari", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Y.S.R. Kadapa");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("Y.S.R. Kadapa", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district1 = districtData.getJSONObject("Foreign Evacuees");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ap.add(new ListElementsDistrict("Foreign Evacuees", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, ap);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void uttarPradesh(JSONObject response){
        try {
            JSONObject UP = response.getJSONObject("Uttar Pradesh");
            JSONObject districtData = UP.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Agra");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Agra", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Aligarh");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Aligarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Ambedkar Nagar");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Ambedkar Nagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Amethi");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Amethi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Amroha");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Amroha", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Auraiya");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Auraiya", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Ayodhya");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Ayodhya", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Azamgarh");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Azamgarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Baghpat");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Baghpat", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Bahraich");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Bahraich", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Ballia");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Ballia", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Balrampur");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Balrampur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Banda");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Banda", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Barabanki");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Barabanki", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Bareilly");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Bareilly", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Basti");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Basti", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Bhadohi");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Bhadohi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Bijnor");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Bijnor", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Budaun");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Budaun", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Bulandshahr");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Bulandshahr", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Chandauli");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Chandauli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Chitrakoot");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Chitrakoot", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Deoria");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Deoria", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("Etah");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Etah", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district25 = districtData.getJSONObject("Etawah");
            active = district25.getString("active");
            confirmed = district25.getString("confirmed");
            deceased = district25.getString("deceased");
            recovered = district25.getString("recovered");
            delta = district25.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Etawah", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district26 = districtData.getJSONObject("Farrukhabad");
            active = district26.getString("active");
            confirmed = district26.getString("confirmed");
            deceased = district26.getString("deceased");
            recovered = district26.getString("recovered");
            delta = district26.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Farrukhabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district27 = districtData.getJSONObject("Fatehpur");
            active = district27.getString("active");
            confirmed = district27.getString("confirmed");
            deceased = district27.getString("deceased");
            recovered = district27.getString("recovered");
            delta = district27.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Fatehpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district28 = districtData.getJSONObject("Firozabad");
            active = district28.getString("active");
            confirmed = district28.getString("confirmed");
            deceased = district28.getString("deceased");
            recovered = district28.getString("recovered");
            delta = district28.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Firozabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district29 = districtData.getJSONObject("Gautam Buddha Nagar");
            active = district29.getString("active");
            confirmed = district29.getString("confirmed");
            deceased = district29.getString("deceased");
            recovered = district29.getString("recovered");
            delta = district29.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Gautam Buddha Nagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district30 = districtData.getJSONObject("Ghaziabad");
            active = district30.getString("active");
            confirmed = district30.getString("confirmed");
            deceased = district30.getString("deceased");
            recovered = district30.getString("recovered");
            delta = district30.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Ghaziabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district31 = districtData.getJSONObject("Ghazipur");
            active = district31.getString("active");
            confirmed = district31.getString("confirmed");
            deceased = district31.getString("deceased");
            recovered = district31.getString("recovered");
            delta = district31.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Ghazipur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district32 = districtData.getJSONObject("Gonda");
            active = district32.getString("active");
            confirmed = district32.getString("confirmed");
            deceased = district32.getString("deceased");
            recovered = district32.getString("recovered");
            delta = district32.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Gonda", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district33 = districtData.getJSONObject("Gorakhpur");
            active = district33.getString("active");
            confirmed = district33.getString("confirmed");
            deceased = district33.getString("deceased");
            recovered = district33.getString("recovered");
            delta = district33.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Gorakhpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district34 = districtData.getJSONObject("Hamirpur");
            active = district34.getString("active");
            confirmed = district34.getString("confirmed");
            deceased = district34.getString("deceased");
            recovered = district34.getString("recovered");
            delta = district34.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Hamirpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district35 = districtData.getJSONObject("Hapur");
            active = district35.getString("active");
            confirmed = district35.getString("confirmed");
            deceased = district35.getString("deceased");
            recovered = district35.getString("recovered");
            delta = district35.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Hapur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district36 = districtData.getJSONObject("Hardoi");
            active = district36.getString("active");
            confirmed = district36.getString("confirmed");
            deceased = district36.getString("deceased");
            recovered = district36.getString("recovered");
            delta = district36.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Hardoi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district37 = districtData.getJSONObject("Hathras");
            active = district37.getString("active");
            confirmed = district37.getString("confirmed");
            deceased = district37.getString("deceased");
            recovered = district37.getString("recovered");
            delta = district37.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Hathras", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district38 = districtData.getJSONObject("Jalaun");
            active = district38.getString("active");
            confirmed = district38.getString("confirmed");
            deceased = district38.getString("deceased");
            recovered = district38.getString("recovered");
            delta = district38.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Jalaun", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district39 = districtData.getJSONObject("Jaunpur");
            active = district39.getString("active");
            confirmed = district39.getString("confirmed");
            deceased = district39.getString("deceased");
            recovered = district39.getString("recovered");
            delta = district39.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Jaunpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district40 = districtData.getJSONObject("Jhansi");
            active = district40.getString("active");
            confirmed = district40.getString("confirmed");
            deceased = district40.getString("deceased");
            recovered = district40.getString("recovered");
            delta = district40.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Jhansi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district41 = districtData.getJSONObject("Kannauj");
            active = district41.getString("active");
            confirmed = district41.getString("confirmed");
            deceased = district41.getString("deceased");
            recovered = district41.getString("recovered");
            delta = district41.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Kannauj", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district42 = districtData.getJSONObject("Kanpur Dehat");
            active = district42.getString("active");
            confirmed = district42.getString("confirmed");
            deceased = district42.getString("deceased");
            recovered = district42.getString("recovered");
            delta = district42.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Kanpur Dehat", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district43 = districtData.getJSONObject("Kanpur Nagar");
            active = district43.getString("active");
            confirmed = district43.getString("confirmed");
            deceased = district43.getString("deceased");
            recovered = district43.getString("recovered");
            delta = district43.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Kanpur Nagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district44 = districtData.getJSONObject("Kasganj");
            active = district44.getString("active");
            confirmed = district44.getString("confirmed");
            deceased = district44.getString("deceased");
            recovered = district44.getString("recovered");
            delta = district44.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Kasganj", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district45 = districtData.getJSONObject("Kaushambi");
            active = district45.getString("active");
            confirmed = district45.getString("confirmed");
            deceased = district45.getString("deceased");
            recovered = district45.getString("recovered");
            delta = district45.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Kaushambi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district46 = districtData.getJSONObject("Kushinagar");
            active = district46.getString("active");
            confirmed = district46.getString("confirmed");
            deceased = district46.getString("deceased");
            recovered = district46.getString("recovered");
            delta = district46.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Kushinagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district47 = districtData.getJSONObject("Lakhimpur Kheri");
            active = district47.getString("active");
            confirmed = district47.getString("confirmed");
            deceased = district47.getString("deceased");
            recovered = district47.getString("recovered");
            delta = district47.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Lakhimpur Kheri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district48 = districtData.getJSONObject("Lalitpur");
            active = district48.getString("active");
            confirmed = district48.getString("confirmed");
            deceased = district48.getString("deceased");
            recovered = district48.getString("recovered");
            delta = district48.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Lalitpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district49 = districtData.getJSONObject("Lucknow");
            active = district49.getString("active");
            confirmed = district49.getString("confirmed");
            deceased = district49.getString("deceased");
            recovered = district49.getString("recovered");
            delta = district49.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Lucknow", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district50 = districtData.getJSONObject("Maharajganj");
            active = district50.getString("active");
            confirmed = district50.getString("confirmed");
            deceased = district50.getString("deceased");
            recovered = district50.getString("recovered");
            delta = district50.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Maharajganj", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district51 = districtData.getJSONObject("Mahoba");
            active = district51.getString("active");
            confirmed = district51.getString("confirmed");
            deceased = district51.getString("deceased");
            recovered = district51.getString("recovered");
            delta = district51.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Mahoba", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district52 = districtData.getJSONObject("Mainpuri");
            active = district52.getString("active");
            confirmed = district52.getString("confirmed");
            deceased = district52.getString("deceased");
            recovered = district52.getString("recovered");
            delta = district52.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Mainpuri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district53 = districtData.getJSONObject("Mathura");
            active = district53.getString("active");
            confirmed = district53.getString("confirmed");
            deceased = district53.getString("deceased");
            recovered = district53.getString("recovered");
            delta = district53.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Mathura", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district54 = districtData.getJSONObject("Mau");
            active = district54.getString("active");
            confirmed = district54.getString("confirmed");
            deceased = district54.getString("deceased");
            recovered = district54.getString("recovered");
            delta = district54.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Mau", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district55 = districtData.getJSONObject("Meerut");
            active = district55.getString("active");
            confirmed = district55.getString("confirmed");
            deceased = district55.getString("deceased");
            recovered = district55.getString("recovered");
            delta = district55.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Meerut", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district56 = districtData.getJSONObject("Mirzapur");
            active = district56.getString("active");
            confirmed = district56.getString("confirmed");
            deceased = district56.getString("deceased");
            recovered = district56.getString("recovered");
            delta = district56.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Mirzapur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district57 = districtData.getJSONObject("Moradabad");
            active = district57.getString("active");
            confirmed = district57.getString("confirmed");
            deceased = district57.getString("deceased");
            recovered = district57.getString("recovered");
            delta = district57.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Moradabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district58 = districtData.getJSONObject("Muzaffarnagar");
            active = district58.getString("active");
            confirmed = district58.getString("confirmed");
            deceased = district58.getString("deceased");
            recovered = district58.getString("recovered");
            delta = district58.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Muzaffarnagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district59 = districtData.getJSONObject("Pilibhit");
            active = district59.getString("active");
            confirmed = district59.getString("confirmed");
            deceased = district59.getString("deceased");
            recovered = district59.getString("recovered");
            delta = district59.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Pilibhit", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district60 = districtData.getJSONObject("Pratapgarh");
            active = district60.getString("active");
            confirmed = district60.getString("confirmed");
            deceased = district60.getString("deceased");
            recovered = district60.getString("recovered");
            delta = district60.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Pratapgarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district61 = districtData.getJSONObject("Prayagraj");
            active = district61.getString("active");
            confirmed = district61.getString("confirmed");
            deceased = district61.getString("deceased");
            recovered = district61.getString("recovered");
            delta = district61.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Prayagraj", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district62 = districtData.getJSONObject("Rae Bareli");
            active = district62.getString("active");
            confirmed = district62.getString("confirmed");
            deceased = district62.getString("deceased");
            recovered = district62.getString("recovered");
            delta = district62.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Rae Bareli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district63 = districtData.getJSONObject("Rampur");
            active = district63.getString("active");
            confirmed = district63.getString("confirmed");
            deceased = district63.getString("deceased");
            recovered = district63.getString("recovered");
            delta = district63.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Rampur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district64 = districtData.getJSONObject("Saharanpur");
            active = district64.getString("active");
            confirmed = district64.getString("confirmed");
            deceased = district64.getString("deceased");
            recovered = district64.getString("recovered");
            delta = district64.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Saharanpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district65 = districtData.getJSONObject("Sambhal");
            active = district65.getString("active");
            confirmed = district65.getString("confirmed");
            deceased = district65.getString("deceased");
            recovered = district65.getString("recovered");
            delta = district65.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Sambhal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district66 = districtData.getJSONObject("Sant Kabir Nagar");
            active = district66.getString("active");
            confirmed = district66.getString("confirmed");
            deceased = district66.getString("deceased");
            recovered = district66.getString("recovered");
            delta = district66.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Sant Kabir Nagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district67 = districtData.getJSONObject("Shahjahanpur");
            active = district67.getString("active");
            confirmed = district67.getString("confirmed");
            deceased = district67.getString("deceased");
            recovered = district67.getString("recovered");
            delta = district67.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Shahjahanpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district68 = districtData.getJSONObject("Shamli");
            active = district68.getString("active");
            confirmed = district68.getString("confirmed");
            deceased = district68.getString("deceased");
            recovered = district68.getString("recovered");
            delta = district68.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Shamli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district69 = districtData.getJSONObject("Shrawasti");
            active = district69.getString("active");
            confirmed = district69.getString("confirmed");
            deceased = district69.getString("deceased");
            recovered = district69.getString("recovered");
            delta = district69.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Shrawasti", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district70 = districtData.getJSONObject("Siddharthnagar");
            active = district70.getString("active");
            confirmed = district70.getString("confirmed");
            deceased = district70.getString("deceased");
            recovered = district70.getString("recovered");
            delta = district70.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Siddharthnagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district71 = districtData.getJSONObject("Sitapur");
            active = district71.getString("active");
            confirmed = district71.getString("confirmed");
            deceased = district71.getString("deceased");
            recovered = district71.getString("recovered");
            delta = district71.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Sitapur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district72 = districtData.getJSONObject("Sonbhadra");
            active = district72.getString("active");
            confirmed = district72.getString("confirmed");
            deceased = district72.getString("deceased");
            recovered = district72.getString("recovered");
            delta = district72.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Sonbhadra", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district73 = districtData.getJSONObject("Sultanpur");
            active = district73.getString("active");
            confirmed = district73.getString("confirmed");
            deceased = district73.getString("deceased");
            recovered = district73.getString("recovered");
            delta = district73.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Sultanpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district74 = districtData.getJSONObject("Unnao");
            active = district74.getString("active");
            confirmed = district74.getString("confirmed");
            deceased = district74.getString("deceased");
            recovered = district74.getString("recovered");
            delta = district74.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Unnao", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district75 = districtData.getJSONObject("Varanasi");
            active = district75.getString("active");
            confirmed = district75.getString("confirmed");
            deceased = district75.getString("deceased");
            recovered = district75.getString("recovered");
            delta = district75.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            up.add(new ListElementsDistrict("Varanasi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this,up);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void gujrat(JSONObject response){
        try {
            JSONObject GJ = response.getJSONObject("Gujarat");
            JSONObject districtData = GJ.getJSONObject("districtData");
            JSONObject delta;


            JSONObject district2 = districtData.getJSONObject("Ahmedabad");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Ahmedabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Amreli");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Amreli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Anand");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Anand", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Aravalli");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Aravalli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Banaskantha");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Banaskantha", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Bharuch");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Bharuch", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Bhavnagar");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Bhavnagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Botad");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Botad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Chhota Udaipur");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Chhota Udaipur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Dahod");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Dahod", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Dang");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Dang", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Devbhumi Dwarka");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Devbhumi Dwarka", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Gandhinagar");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Gandhinagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Gir Somnath");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Gir Somnath", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Jamnagar");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Jamnagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Junagadh");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Junagadh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Kheda");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Kheda", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Kutch");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Kutch", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Mahisagar");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Mahisagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Mehsana");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Mehsana", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Morbi");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Morbi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Narmada");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Narmada", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("Navsari");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Navsari", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district25 = districtData.getJSONObject("Panchmahal");
            active = district25.getString("active");
            confirmed = district25.getString("confirmed");
            deceased = district25.getString("deceased");
            recovered = district25.getString("recovered");
            delta = district25.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Panchmahal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district26 = districtData.getJSONObject("Patan");
            active = district26.getString("active");
            confirmed = district26.getString("confirmed");
            deceased = district26.getString("deceased");
            recovered = district26.getString("recovered");
            delta = district26.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Patan", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district27 = districtData.getJSONObject("Porbandar");
            active = district27.getString("active");
            confirmed = district27.getString("confirmed");
            deceased = district27.getString("deceased");
            recovered = district27.getString("recovered");
            delta = district27.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Porbandar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district28 = districtData.getJSONObject("Rajkot");
            active = district28.getString("active");
            confirmed = district28.getString("confirmed");
            deceased = district28.getString("deceased");
            recovered = district28.getString("recovered");
            delta = district28.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Rajkot", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district29 = districtData.getJSONObject("Sabarkantha");
            active = district29.getString("active");
            confirmed = district29.getString("confirmed");
            deceased = district29.getString("deceased");
            recovered = district29.getString("recovered");
            delta = district29.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Sabarkantha", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district30 = districtData.getJSONObject("Surat");
            active = district30.getString("active");
            confirmed = district30.getString("confirmed");
            deceased = district30.getString("deceased");
            recovered = district30.getString("recovered");
            delta = district30.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Surat", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district31 = districtData.getJSONObject("Surendranagar");
            active = district31.getString("active");
            confirmed = district31.getString("confirmed");
            deceased = district31.getString("deceased");
            recovered = district31.getString("recovered");
            delta = district31.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Surendranagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district32 = districtData.getJSONObject("Tapi");
            active = district32.getString("active");
            confirmed = district32.getString("confirmed");
            deceased = district32.getString("deceased");
            recovered = district32.getString("recovered");
            delta = district32.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Tapi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district33 = districtData.getJSONObject("Vadodara");
            active = district33.getString("active");
            confirmed = district33.getString("confirmed");
            deceased = district33.getString("deceased");
            recovered = district33.getString("recovered");
            delta = district33.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Vadodara", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district34 = districtData.getJSONObject("Valsad");
            active = district34.getString("active");
            confirmed = district34.getString("confirmed");
            deceased = district34.getString("deceased");
            recovered = district34.getString("recovered");
            delta = district34.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Valsad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district1 = districtData.getJSONObject("Other State");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            gj.add(new ListElementsDistrict("Other State", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, gj);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void westBengal(JSONObject response){
        try {
            JSONObject WB = response.getJSONObject("West Bengal");
            JSONObject districtData = WB.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Alipurduar");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Alipurduar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Bankura");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Bankura", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Birbhum");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Birbhum", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Cooch Behar");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Cooch Behar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Dakshin Dinajpur");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Dakshin Dinajpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Darjeeling");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Darjeeling", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Hooghly");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Hooghly", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Howrah");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Howrah", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Jalpaiguri");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Jalpaiguri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Jhargram");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Jhargram", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Kalimpong");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Kalimpong", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Kolkata");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Kolkata", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Malda");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Malda", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Murshidabad");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Murshidabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Nadia");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Nadia", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("North 24 Parganas");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("North 24 Parganas", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Other State");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Other State", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Paschim Bardhaman");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Paschim Bardhaman", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Paschim Medinipur");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Paschim Medinipur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Purba Bardhaman");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Purba Bardhaman", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Purba Medinipur");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Purba Medinipur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Purulia");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Purulia", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("South 24 Parganas");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("South 24 Parganas", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("Uttar Dinajpur");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            wb.add(new ListElementsDistrict("Uttar Dinajpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this,wb);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void telangana(JSONObject response){
        try {
            JSONObject TG = response.getJSONObject("Telangana");
            JSONObject districtData = TG.getJSONObject("districtData");
            JSONObject delta;


            JSONObject district3 = districtData.getJSONObject("Adilabad");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Adilabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Bhadradri Kothagudem");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Bhadradri Kothagudem", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Hyderabad");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Hyderabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Jagtial");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Jagtial", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Jangaon");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Jangaon", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Jayashankar Bhupalapally");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Jayashankar Bhupalapally", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Jogulamba Gadwal");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Jogulamba Gadwal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Kamareddy");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Kamareddy", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Karimnagar");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Karimnagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Khammam");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Khammam", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Komaram Bheem");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Komaram Bheem", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Mahabubabad");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Mahabubabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Mahabubnagar");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Mahabubnagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Mancherial");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Mancherial", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Medak");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Medak", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Medchal Malkajgiri");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Medchal Malkajgiri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Mulugu");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Mulugu", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Nagarkurnool");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Nagarkurnool", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Nalgonda");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Nalgonda", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Narayanpet");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Narayanpet", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Nirmal");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Nirmal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("Nizamabad");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Nizamabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district25 = districtData.getJSONObject("Peddapalli");
            active = district25.getString("active");
            confirmed = district25.getString("confirmed");
            deceased = district25.getString("deceased");
            recovered = district25.getString("recovered");
            delta = district25.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Peddapalli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district26 = districtData.getJSONObject("Rajanna Sircilla");
            active = district26.getString("active");
            confirmed = district26.getString("confirmed");
            deceased = district26.getString("deceased");
            recovered = district26.getString("recovered");
            delta = district26.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Rajanna Sircilla", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district27 = districtData.getJSONObject("Ranga Reddy");
            active = district27.getString("active");
            confirmed = district27.getString("confirmed");
            deceased = district27.getString("deceased");
            recovered = district27.getString("recovered");
            delta = district27.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Ranga Reddy", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district28 = districtData.getJSONObject("Sangareddy");
            active = district28.getString("active");
            confirmed = district28.getString("confirmed");
            deceased = district28.getString("deceased");
            recovered = district28.getString("recovered");
            delta = district28.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Sangareddy", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district29 = districtData.getJSONObject("Siddipet");
            active = district29.getString("active");
            confirmed = district29.getString("confirmed");
            deceased = district29.getString("deceased");
            recovered = district29.getString("recovered");
            delta = district29.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Siddipet", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district30 = districtData.getJSONObject("Suryapet");
            active = district30.getString("active");
            confirmed = district30.getString("confirmed");
            deceased = district30.getString("deceased");
            recovered = district30.getString("recovered");
            delta = district30.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Suryapet", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district31 = districtData.getJSONObject("Vikarabad");
            active = district31.getString("active");
            confirmed = district31.getString("confirmed");
            deceased = district31.getString("deceased");
            recovered = district31.getString("recovered");
            delta = district31.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Vikarabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district32 = districtData.getJSONObject("Wanaparthy");
            active = district32.getString("active");
            confirmed = district32.getString("confirmed");
            deceased = district32.getString("deceased");
            recovered = district32.getString("recovered");
            delta = district32.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Wanaparthy", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district33 = districtData.getJSONObject("Warangal Rural");
            active = district33.getString("active");
            confirmed = district33.getString("confirmed");
            deceased = district33.getString("deceased");
            recovered = district33.getString("recovered");
            delta = district33.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Warangal Rural", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district34 = districtData.getJSONObject("Warangal Urban");
            active = district34.getString("active");
            confirmed = district34.getString("confirmed");
            deceased = district34.getString("deceased");
            recovered = district34.getString("recovered");
            delta = district34.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Warangal Urban", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district35 = districtData.getJSONObject("Yadadri Bhuvanagiri");
            active = district35.getString("active");
            confirmed = district35.getString("confirmed");
            deceased = district35.getString("deceased");
            recovered = district35.getString("recovered");
            delta = district35.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Yadadri Bhuvanagiri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district1 = districtData.getJSONObject("Foreign Evacuees");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Foreign Evacuees", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Other State");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tg.add(new ListElementsDistrict("Other State", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this,tg);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void rajasthan(JSONObject response){

        try {
            JSONObject RJ = response.getJSONObject("Rajasthan");
            JSONObject districtData = RJ.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Ajmer");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Ajmer", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Alwar");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Alwar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Banswara");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Banswara", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Baran");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Baran", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Barmer");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Barmer", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Bharatpur");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Bharatpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Bhilwara");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Bhilwara", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Bikaner");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Bikaner", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("BSF Camp");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("BSF Camp", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Bundi");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Bundi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Chittorgarh");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Chittorgarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Churu");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Churu", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Dausa");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Dausa", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Dholpur");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Dholpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Dungarpur");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Dungarpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Evacuees");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Evacuees", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Ganganagar");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Ganganagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Hanumangarh");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Hanumangarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Italians");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Italians", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Jaipur");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Jaipur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Jaisalmer");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Jaisalmer", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Jalore");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Jalore", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Jhalawar");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Jhalawar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("Jhunjhunu");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Jhunjhunu", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district25 = districtData.getJSONObject("Jodhpur");
            active = district25.getString("active");
            confirmed = district25.getString("confirmed");
            deceased = district25.getString("deceased");
            recovered = district25.getString("recovered");
            delta = district25.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Jodhpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district26 = districtData.getJSONObject("Karauli");
            active = district26.getString("active");
            confirmed = district26.getString("confirmed");
            deceased = district26.getString("deceased");
            recovered = district26.getString("recovered");
            delta = district26.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Karauli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district27 = districtData.getJSONObject("Kota");
            active = district27.getString("active");
            confirmed = district27.getString("confirmed");
            deceased = district27.getString("deceased");
            recovered = district27.getString("recovered");
            delta = district27.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Kota", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district28 = districtData.getJSONObject("Nagaur");
            active = district28.getString("active");
            confirmed = district28.getString("confirmed");
            deceased = district28.getString("deceased");
            recovered = district28.getString("recovered");
            delta = district28.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Nagaur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district29 = districtData.getJSONObject("Other State");
            active = district29.getString("active");
            confirmed = district29.getString("confirmed");
            deceased = district29.getString("deceased");
            recovered = district29.getString("recovered");
            delta = district29.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Other State", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district30 = districtData.getJSONObject("Pali");
            active = district30.getString("active");
            confirmed = district30.getString("confirmed");
            deceased = district30.getString("deceased");
            recovered = district30.getString("recovered");
            delta = district30.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Pali", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district31 = districtData.getJSONObject("Pratapgarh");
            active = district31.getString("active");
            confirmed = district31.getString("confirmed");
            deceased = district31.getString("deceased");
            recovered = district31.getString("recovered");
            delta = district31.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Pratapgarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district32 = districtData.getJSONObject("Rajsamand");
            active = district32.getString("active");
            confirmed = district32.getString("confirmed");
            deceased = district32.getString("deceased");
            recovered = district32.getString("recovered");
            delta = district32.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Rajsamand", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district33 = districtData.getJSONObject("Sawai Madhopur");
            active = district33.getString("active");
            confirmed = district33.getString("confirmed");
            deceased = district33.getString("deceased");
            recovered = district33.getString("recovered");
            delta = district33.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Sawai Madhopur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district34 = districtData.getJSONObject("Sikar");
            active = district34.getString("active");
            confirmed = district34.getString("confirmed");
            deceased = district34.getString("deceased");
            recovered = district34.getString("recovered");
            delta = district34.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Sikar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district35 = districtData.getJSONObject("Sirohi");
            active = district35.getString("active");
            confirmed = district35.getString("confirmed");
            deceased = district35.getString("deceased");
            recovered = district35.getString("recovered");
            delta = district35.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Sirohi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district36 = districtData.getJSONObject("Tonk");
            active = district36.getString("active");
            confirmed = district36.getString("confirmed");
            deceased = district36.getString("deceased");
            recovered = district36.getString("recovered");
            delta = district36.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Tonk", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district37 = districtData.getJSONObject("Udaipur");
            active = district37.getString("active");
            confirmed = district37.getString("confirmed");
            deceased = district37.getString("deceased");
            recovered = district37.getString("recovered");
            delta = district37.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            rj.add(new ListElementsDistrict("Udaipur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this,rj);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void arunachalPradesh(JSONObject response){
        try {
            JSONObject AR = response.getJSONObject("Arunachal Pradesh");
            JSONObject districtData = AR.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Anjaw");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Anjaw", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Changlang");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Changlang", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("East Kameng");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("East Kameng", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("East Siang");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("East Siang", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Kamle");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Kamle", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Kra Daadi");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Kra Daadi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Kurung Kumey");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Kurung Kumey", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Lepa Rada");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Lepa Rada", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Lohit");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Lohit", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Longding");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Longding", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Lower Dibang Valley");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Lower Dibang Valley", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Lower Siang");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Lower Siang", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Lower Subansiri");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Lower Subansiri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Namsai");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Namsai", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Pakke Kessang");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Pakke Kessang", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Papum Pare");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Papum Pare", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Shi Yomi");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Shi Yomi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Siang");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Siang", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Tawang");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Tawang", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Tirap");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Tirap", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Upper Dibang Valley");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Upper Dibang Valley", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Upper Siang");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Upper Siang", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Upper Subansiri");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("Upper Subansiri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("West Kameng");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("West Kameng", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district25 = districtData.getJSONObject("West Siang");
            active = district25.getString("active");
            confirmed = district25.getString("confirmed");
            deceased = district25.getString("deceased");
            recovered = district25.getString("recovered");
            delta = district25.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ar.add(new ListElementsDistrict("West Siang", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, ar);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void assam(JSONObject response){
        try {
            JSONObject AS = response.getJSONObject("Assam");
            JSONObject districtData = AS.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district2 = districtData.getJSONObject("Baksa");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Baksa", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Barpeta");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Barpeta", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Biswanath");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Biswanath", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Bongaigaon");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Bongaigaon", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Cachar");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Cachar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Charaideo");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Charaideo", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Chirang");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Chirang", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Darrang");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Darrang", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Dhemaji");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Dhemaji", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Dhubri");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Dhubri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Dibrugarh");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Dibrugarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Dima Hasao");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Dima Hasao", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Goalpara");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Goalpara", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Golaghat");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Golaghat", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Hailakandi");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Hailakandi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Hojai");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Hojai", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Jorhat");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Jorhat", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Kamrup");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Kamrup", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Kamrup Metropolitan");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Kamrup Metropolitan", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Karbi Anglong");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Kabri Anglong", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Karimganj");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Karimganj", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Kokrajhar");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Kokrajhar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("Lakhimpur");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Lakhimpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district25 = districtData.getJSONObject("Majuli");
            active = district25.getString("active");
            confirmed = district25.getString("confirmed");
            deceased = district25.getString("deceased");
            recovered = district25.getString("recovered");
            delta = district25.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Majuli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district26 = districtData.getJSONObject("Morigaon");
            active = district26.getString("active");
            confirmed = district26.getString("confirmed");
            deceased = district26.getString("deceased");
            recovered = district26.getString("recovered");
            delta = district26.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Morigaon", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district27 = districtData.getJSONObject("Nagaon");
            active = district27.getString("active");
            confirmed = district27.getString("confirmed");
            deceased = district27.getString("deceased");
            recovered = district27.getString("recovered");
            delta = district27.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Nagaon", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district28 = districtData.getJSONObject("Nalbari");
            active = district28.getString("active");
            confirmed = district28.getString("confirmed");
            deceased = district28.getString("deceased");
            recovered = district28.getString("recovered");
            delta = district28.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Nalbari", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district29 = districtData.getJSONObject("Other State");
            active = district29.getString("active");
            confirmed = district29.getString("confirmed");
            deceased = district29.getString("deceased");
            recovered = district29.getString("recovered");
            delta = district29.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Other State", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district30 = districtData.getJSONObject("Sivasagar");
            active = district30.getString("active");
            confirmed = district30.getString("confirmed");
            deceased = district30.getString("deceased");
            recovered = district30.getString("recovered");
            delta = district30.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Sivasagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district31 = districtData.getJSONObject("Sonitpur");
            active = district31.getString("active");
            confirmed = district31.getString("confirmed");
            deceased = district31.getString("deceased");
            recovered = district31.getString("recovered");
            delta = district31.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Sonitpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district32 = districtData.getJSONObject("South Salmara Mankachar");
            active = district32.getString("active");
            confirmed = district32.getString("confirmed");
            deceased = district32.getString("deceased");
            recovered = district32.getString("recovered");
            delta = district32.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("South Salmara Mankachar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district33 = districtData.getJSONObject("Tinsukia");
            active = district33.getString("active");
            confirmed = district33.getString("confirmed");
            deceased = district33.getString("deceased");
            recovered = district33.getString("recovered");
            delta = district33.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Tinsukia", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district34 = districtData.getJSONObject("Udalguri");
            active = district34.getString("active");
            confirmed = district34.getString("confirmed");
            deceased = district34.getString("deceased");
            recovered = district34.getString("recovered");
            delta = district34.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Udalguri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district35 = districtData.getJSONObject("West Karbi Anglong");
            active = district35.getString("active");
            confirmed = district35.getString("confirmed");
            deceased = district35.getString("deceased");
            recovered = district35.getString("recovered");
            delta = district35.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("West Karbi Anglong", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district1 = districtData.getJSONObject("Airport Quarantine");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            as.add(new ListElementsDistrict("Airport Quarantine", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, as);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void bihar(JSONObject response){
        try {
            JSONObject BR = response.getJSONObject("Bihar");
            JSONObject districtData = BR.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Araria");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Araria", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Arwal");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Arwal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Aurangabad");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Aurangabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Banka");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Banka", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Begusarai");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Begusarai", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Bhagalpur");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Bhagalpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Bhojpur");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Bhojpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Buxar");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Buxar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Darbhanga");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Darbhanga", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("East Champaran");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("East Champaran", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Gaya");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Gaya", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Gopalganj");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Gopalganj", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Jamui");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Jamui", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Jehanabad");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Jehanabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Kaimur");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Kaimur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Katihar");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Katihar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Khagaria");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Khagaria", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Kishanganj");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Kishanganj", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Lakhisarai");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Lakhisarai", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Madhepura");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Madhepura", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Madhubani");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Madhubani", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Munger");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Munger", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Muzaffarpur");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Muzaffarpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("Nalanda");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Nalanda", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district25 = districtData.getJSONObject("Nawada");
            active = district25.getString("active");
            confirmed = district25.getString("confirmed");
            deceased = district25.getString("deceased");
            recovered = district25.getString("recovered");
            delta = district25.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Nawada", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district26 = districtData.getJSONObject("Patna");
            active = district26.getString("active");
            confirmed = district26.getString("confirmed");
            deceased = district26.getString("deceased");
            recovered = district26.getString("recovered");
            delta = district26.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Patna", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district27 = districtData.getJSONObject("Purnia");
            active = district27.getString("active");
            confirmed = district27.getString("confirmed");
            deceased = district27.getString("deceased");
            recovered = district27.getString("recovered");
            delta = district27.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Purnia", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district28 = districtData.getJSONObject("Rohtas");
            active = district28.getString("active");
            confirmed = district28.getString("confirmed");
            deceased = district28.getString("deceased");
            recovered = district28.getString("recovered");
            delta = district28.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Rohtas", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district29 = districtData.getJSONObject("Saharsa");
            active = district29.getString("active");
            confirmed = district29.getString("confirmed");
            deceased = district29.getString("deceased");
            recovered = district29.getString("recovered");
            delta = district29.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Saharsa", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district30 = districtData.getJSONObject("Samastipur");
            active = district30.getString("active");
            confirmed = district30.getString("confirmed");
            deceased = district30.getString("deceased");
            recovered = district30.getString("recovered");
            delta = district30.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Samastipur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district31 = districtData.getJSONObject("Saran");
            active = district31.getString("active");
            confirmed = district31.getString("confirmed");
            deceased = district31.getString("deceased");
            recovered = district31.getString("recovered");
            delta = district31.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Saran", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district32 = districtData.getJSONObject("Sheikhpura");
            active = district32.getString("active");
            confirmed = district32.getString("confirmed");
            deceased = district32.getString("deceased");
            recovered = district32.getString("recovered");
            delta = district32.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Sheikhpura", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district33 = districtData.getJSONObject("Sheohar");
            active = district33.getString("active");
            confirmed = district33.getString("confirmed");
            deceased = district33.getString("deceased");
            recovered = district33.getString("recovered");
            delta = district33.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Sheohar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district34 = districtData.getJSONObject("Sitamarhi");
            active = district34.getString("active");
            confirmed = district34.getString("confirmed");
            deceased = district34.getString("deceased");
            recovered = district34.getString("recovered");
            delta = district34.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Sitamarhi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district35 = districtData.getJSONObject("Siwan");
            active = district35.getString("active");
            confirmed = district35.getString("confirmed");
            deceased = district35.getString("deceased");
            recovered = district35.getString("recovered");
            delta = district35.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Siwan", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district36 = districtData.getJSONObject("Supaul");
            active = district36.getString("active");
            confirmed = district36.getString("confirmed");
            deceased = district36.getString("deceased");
            recovered = district36.getString("recovered");
            delta = district36.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Supaul", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district37 = districtData.getJSONObject("Vaishali");
            active = district37.getString("active");
            confirmed = district37.getString("confirmed");
            deceased = district37.getString("deceased");
            recovered = district37.getString("recovered");
            delta = district37.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("Vaishali", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district38 = districtData.getJSONObject("West Champaran");
            active = district38.getString("active");
            confirmed = district38.getString("confirmed");
            deceased = district38.getString("deceased");
            recovered = district38.getString("recovered");
            delta = district38.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            br.add(new ListElementsDistrict("West Champaran", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, br);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void chandigarh(JSONObject response){
        try {
            JSONObject CH = response.getJSONObject("Chandigarh");
            JSONObject districtData = CH.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Chandigarh");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ch.add(new ListElementsDistrict("Chandigarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, ch);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void chattisgarh(JSONObject response){
        try {
            JSONObject CT = response.getJSONObject("Chhattisgarh");
            JSONObject districtData = CT.getJSONObject("districtData");
            JSONObject delta;


            JSONObject district2 = districtData.getJSONObject("Balod");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Balod", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Baloda Bazar");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Baloda Bazar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Balrampur");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Balrampur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Bametara");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Bametara", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Bastar");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Bastar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Bijapur");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Bijapur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Bilaspur");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Bilaspur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Dakshin Bastar Dantewada");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Dakshin Bastar Dantewada", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Dhamtari");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Dhamtari", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Durg");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Durg", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Gariaband");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Gariaband", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Janjgir Champa");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Janjgir Champa", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Jashpur");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Jashpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Kabeerdham");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Kabeerdham", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Kondagaon");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Kondagaon", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Korba");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Korba", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Koriya");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Koriya", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Mahasamund");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Mahasamund", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Mungeli");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Mungeli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Narayanpur");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Narayanpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Raigarh");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Raigarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Raipur");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Raipur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("Rajnandgaon");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Rajnandgaon", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district25 = districtData.getJSONObject("Sukma");
            active = district25.getString("active");
            confirmed = district25.getString("confirmed");
            deceased = district25.getString("deceased");
            recovered = district25.getString("recovered");
            delta = district25.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Sukma", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district26 = districtData.getJSONObject("Surajpur");
            active = district26.getString("active");
            confirmed = district26.getString("confirmed");
            deceased = district26.getString("deceased");
            recovered = district26.getString("recovered");
            delta = district26.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Surajpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district27 = districtData.getJSONObject("Surguja");
            active = district27.getString("active");
            confirmed = district27.getString("confirmed");
            deceased = district27.getString("deceased");
            recovered = district27.getString("recovered");
            delta = district27.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Surguja", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district28 = districtData.getJSONObject("Uttar Bastar Kanker");
            active = district28.getString("active");
            confirmed = district28.getString("confirmed");
            deceased = district28.getString("deceased");
            recovered = district28.getString("recovered");
            delta = district28.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Uttar Bastar Kanker", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district29 = districtData.getJSONObject("Gaurela Pendra Marwahi");
            active = district29.getString("active");
            confirmed = district29.getString("confirmed");
            deceased = district29.getString("deceased");
            recovered = district29.getString("recovered");
            delta = district29.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Gaurela Pendra Marwahi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district1 = districtData.getJSONObject("Other State");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ct.add(new ListElementsDistrict("Other State", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, ct);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void dadarAndNagarHaveli(JSONObject response){
        try {
            JSONObject DN = response.getJSONObject("Dadra and Nagar Haveli and Daman and Diu");
            JSONObject districtData = DN.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district2 = districtData.getJSONObject("Dadra and Nagar Haveli");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dn.add(new ListElementsDistrict("Dadra and Nagar Haveli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Daman");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dn.add(new ListElementsDistrict("Daman", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Diu");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dn.add(new ListElementsDistrict("Diu", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district1 = districtData.getJSONObject("Other State");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            dn.add(new ListElementsDistrict("Other State", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, dn);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void goa(JSONObject response){
        try {
            JSONObject GA = response.getJSONObject("Goa");
            JSONObject districtData = GA.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district2 = districtData.getJSONObject("North Goa");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ga.add(new ListElementsDistrict("North Goa", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("South Goa");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ga.add(new ListElementsDistrict("South Goa", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district1 = districtData.getJSONObject("Other State");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ga.add(new ListElementsDistrict("Other State", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, ga);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void himachalPradesh(JSONObject response){
        try {
            JSONObject HP = response.getJSONObject("Himachal Pradesh");
            JSONObject districtData = HP.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Bilaspur");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hp.add(new ListElementsDistrict("Bilaspur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Chamba");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hp.add(new ListElementsDistrict("Chamba", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Hamirpur");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hp.add(new ListElementsDistrict("Hamirpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Kangra");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hp.add(new ListElementsDistrict("Kangra", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Kinnaur");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hp.add(new ListElementsDistrict("Kinnaur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Kullu");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hp.add(new ListElementsDistrict("Kullu", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Lahaul and Spiti");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hp.add(new ListElementsDistrict("Lahaul and Spiti", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Mandi");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hp.add(new ListElementsDistrict("Mandi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Shimla");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hp.add(new ListElementsDistrict("Shimla", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Sirmaur");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hp.add(new ListElementsDistrict("Sirmaur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Solan");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hp.add(new ListElementsDistrict("Solan", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Una");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hp.add(new ListElementsDistrict("Una", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, hp);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void harayana(JSONObject response){
        try {
            JSONObject HR = response.getJSONObject("Haryana");
            JSONObject districtData = HR.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Foreign Evacuees");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Foreign Evacuees", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Ambala");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Ambala", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Bhiwani");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Bhiwani", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Charkhi Dadri");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Charkhi Dadri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Faridabad");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Faridabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Fatehabad");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Fatehabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Gurugram");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Gurugram", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Hisar");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Hisar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Italians");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Italians", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Jhajjar");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Jhajjar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Jind");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Jind", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Kaithal");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Kaithal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Karnal");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Karnal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Kurukshetra");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Kurukshetra", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Mahendragarh");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Mahendragarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Nuh");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Nuh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Palwal");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Palwal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Panchkula");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Panchkula", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Panipat");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Panipat", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Rewari");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Rewari", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Rohtak");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Rohtak", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Sirsa");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Sirsa", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Sonipat");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Sonipat", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("Yamunanagar");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            hr.add(new ListElementsDistrict("Yamunanagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, hr);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void jharkhand(JSONObject response){
        try {
            JSONObject JH = response.getJSONObject("Jharkhand");
            JSONObject districtData = JH.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Bokaro");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Bokaro", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Chatra");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Chatra", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Deoghar");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Deoghar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Dhanbad");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Dhanbad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Dumka");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Dumka", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("East Singhbhum");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("East Singhbhum", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Garhwa");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Garhwa", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Giridih");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Giridih", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Godda");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Godda", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Gumla");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Gumla", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Hazaribagh");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Hazaribagh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Jamtara");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Jamtara", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Khunti");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Khunti", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Koderma");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Koderma", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Latehar");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Latehar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Lohardaga");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Lohardaga", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Pakur");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Pakur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Palamu");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Palamu", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Ramgarh");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Ramgarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Ranchi");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Ranchi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Sahibganj");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Sahibganj", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Saraikela-Kharsawan");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Saraikela-Kharsawan", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Simdega");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("Simdega", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("West Singhbhum");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jh.add(new ListElementsDistrict("West Singhbhum", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, jh);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void jammuAndKashmir(JSONObject response){
        try {
            JSONObject JK = response.getJSONObject("Jammu and Kashmir");
            JSONObject districtData = JK.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Anantnag");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Anantnag", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Bandipora");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Bandipora", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Baramulla");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Baramulla", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Budgam");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Budgam", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Doda");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Doda", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Ganderbal");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Ganderbal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Jammu");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Jammu", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Kathua");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Kathua", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Kishtwar");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Kishtwar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Kulgam");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Kulgam", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Kupwara");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Kupwara", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Mirpur");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Mirpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Muzaffarabad");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Muzaffarabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Pulwama");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Pulwama", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Punch");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Punch", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Rajouri");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Rajouri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Ramban");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Ramban", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Reasi");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Reasi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Samba");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Samba", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Shopiyan");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Shopiyan", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Srinagar");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Srinagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Udhampur");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            jk.add(new ListElementsDistrict("Udhampur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, jk);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void kerala(JSONObject response){
        try {
            JSONObject KL = response.getJSONObject("Kerala");
            JSONObject districtData = KL.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district2 = districtData.getJSONObject("Alappuzha");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Alappuzha", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Ernakulam");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Ernakulam", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Idukki");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Idukki", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Kannur");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Kannur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Kasaragod");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Kasaragod", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Kollam");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Kollam", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Kottayam");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Kottayam", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Kozhikode");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Kozhikode", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Malappuram");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Malappuram", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Palakkad");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Palakkad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Pathanamthitta");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Pathanamthitta", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Thiruvananthapuram");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Thiruvananthapuram", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Thrissur");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Thrissur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Wayanad");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Wayanad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district1 = districtData.getJSONObject("Other State");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            kl.add(new ListElementsDistrict("Other State", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, kl);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void ladakh(JSONObject response){
        try {
            JSONObject LA = response.getJSONObject("Ladakh");
            JSONObject districtData = LA.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Kargil");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            la.add(new ListElementsDistrict("Kargil", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Leh");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            la.add(new ListElementsDistrict("Leh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, la);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void lakshadweep(JSONObject response){
        try {
            JSONObject LD = response.getJSONObject("Lakshadweep");
            JSONObject districtData = LD.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Lakshadweep");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ld.add(new ListElementsDistrict("Lakshadweep", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, ld);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void megalaya(JSONObject response){
        try {
            JSONObject ML = response.getJSONObject("Meghalaya");
            JSONObject districtData = ML.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("East Garo Hills");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ml.add(new ListElementsDistrict("East Garo Hills", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("East Jaintia Hills");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ml.add(new ListElementsDistrict("East Jaintia Hills", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("East Khasi Hills");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ml.add(new ListElementsDistrict("East Khasi Hills", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("North Garo Hills");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ml.add(new ListElementsDistrict("North Garo Hills", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Ribhoi");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ml.add(new ListElementsDistrict("Ribhoi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("South Garo Hills");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ml.add(new ListElementsDistrict("South Garo Hills", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("South West Garo Hills");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ml.add(new ListElementsDistrict("South West Garo Hills", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("South West Khasi Hills");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ml.add(new ListElementsDistrict("South West Khasi Hills", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("West Garo Hills");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ml.add(new ListElementsDistrict("West Garo Hills", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("West Jaintia Hills");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ml.add(new ListElementsDistrict("West Jaintia Hills", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("West Khasi Hills");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ml.add(new ListElementsDistrict("West Khasi Hills", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, ml);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void manipur(JSONObject response){
        try {
            JSONObject MN = response.getJSONObject("Manipur");
            JSONObject districtData = MN.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Bishnupur");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Bishnupur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Chandel");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Chandel", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Churachandpur");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Churachandpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Imphal East");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Imphal East", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Imphal West");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Imphal West", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Jiribam");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Jiribam", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Kakching");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Kakching", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Kamjong");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Kamjong", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Kangpokpi");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Kangpokpi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Noney");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Noney", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Pherzawl");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Pherzawl", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Senapati");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Senapati", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Tamenglong");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Tamenglong", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Tengnoupal");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Tengnoupal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Thoubal");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Thoubal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Ukhrul");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mn.add(new ListElementsDistrict("Ukhrul", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, mn);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void madhyaPradesh(JSONObject response){
        try {
            JSONObject MP = response.getJSONObject("Madhya Pradesh");
            JSONObject districtData = MP.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Agar Malwa");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Agar Malwa", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Alirajpur");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Alirajpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Anuppur");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Anuppur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Ashoknagar");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Ashoknagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Balaghat");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Balaghat", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Barwani");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Barwani", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Betul");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Betul", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Bhind");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Bhind", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Bhopal");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Bhopal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Burhanpur");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Burhanpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Chhatarpur");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Chhatarpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Chhindwara");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Chhindwara", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Damoh");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Damoh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Datia");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Datia", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Dewas");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Dewas", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Dhar");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Dhar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Dindori");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Dindori", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Guna");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Guna", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Gwalior");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Gwalior", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Harda");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Harda", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Hoshangabad");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Hoshangabad", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Indore");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Indore", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Jabalpur");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Jabalpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("Jhabua");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Jhabua", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district25 = districtData.getJSONObject("Katni");
            active = district25.getString("active");
            confirmed = district25.getString("confirmed");
            deceased = district25.getString("deceased");
            recovered = district25.getString("recovered");
            delta = district25.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Katni", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district26 = districtData.getJSONObject("Khandwa");
            active = district26.getString("active");
            confirmed = district26.getString("confirmed");
            deceased = district26.getString("deceased");
            recovered = district26.getString("recovered");
            delta = district26.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Khandwa", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district27 = districtData.getJSONObject("Khargone");
            active = district27.getString("active");
            confirmed = district27.getString("confirmed");
            deceased = district27.getString("deceased");
            recovered = district27.getString("recovered");
            delta = district27.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Khargone", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district28 = districtData.getJSONObject("Mandla");
            active = district28.getString("active");
            confirmed = district28.getString("confirmed");
            deceased = district28.getString("deceased");
            recovered = district28.getString("recovered");
            delta = district28.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Mandla", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district29 = districtData.getJSONObject("Mandsaur");
            active = district29.getString("active");
            confirmed = district29.getString("confirmed");
            deceased = district29.getString("deceased");
            recovered = district29.getString("recovered");
            delta = district29.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Mandsaur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district30 = districtData.getJSONObject("Morena");
            active = district30.getString("active");
            confirmed = district30.getString("confirmed");
            deceased = district30.getString("deceased");
            recovered = district30.getString("recovered");
            delta = district30.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Morena", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district31 = districtData.getJSONObject("Narsinghpur");
            active = district31.getString("active");
            confirmed = district31.getString("confirmed");
            deceased = district31.getString("deceased");
            recovered = district31.getString("recovered");
            delta = district31.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Narsinghpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district32 = districtData.getJSONObject("Neemuch");
            active = district32.getString("active");
            confirmed = district32.getString("confirmed");
            deceased = district32.getString("deceased");
            recovered = district32.getString("recovered");
            delta = district32.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Neemuch", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district33 = districtData.getJSONObject("Niwari");
            active = district33.getString("active");
            confirmed = district33.getString("confirmed");
            deceased = district33.getString("deceased");
            recovered = district33.getString("recovered");
            delta = district33.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Niwari", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district34 = districtData.getJSONObject("Other Region");
            active = district34.getString("active");
            confirmed = district34.getString("confirmed");
            deceased = district34.getString("deceased");
            recovered = district34.getString("recovered");
            delta = district34.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Other Region", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district35 = districtData.getJSONObject("Panna");
            active = district35.getString("active");
            confirmed = district35.getString("confirmed");
            deceased = district35.getString("deceased");
            recovered = district35.getString("recovered");
            delta = district35.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Panna", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district36 = districtData.getJSONObject("Raisen");
            active = district36.getString("active");
            confirmed = district36.getString("confirmed");
            deceased = district36.getString("deceased");
            recovered = district36.getString("recovered");
            delta = district36.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Raisen", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district37 = districtData.getJSONObject("Rajgarh");
            active = district37.getString("active");
            confirmed = district37.getString("confirmed");
            deceased = district37.getString("deceased");
            recovered = district37.getString("recovered");
            delta = district37.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Rajgarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district38 = districtData.getJSONObject("Ratlam");
            active = district38.getString("active");
            confirmed = district38.getString("confirmed");
            deceased = district38.getString("deceased");
            recovered = district38.getString("recovered");
            delta = district38.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Ratlam", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district39 = districtData.getJSONObject("Rewa");
            active = district39.getString("active");
            confirmed = district39.getString("confirmed");
            deceased = district39.getString("deceased");
            recovered = district39.getString("recovered");
            delta = district39.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Rewa", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district40 = districtData.getJSONObject("Sagar");
            active = district40.getString("active");
            confirmed = district40.getString("confirmed");
            deceased = district40.getString("deceased");
            recovered = district40.getString("recovered");
            delta = district40.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Sagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district41 = districtData.getJSONObject("Satna");
            active = district41.getString("active");
            confirmed = district41.getString("confirmed");
            deceased = district41.getString("deceased");
            recovered = district41.getString("recovered");
            delta = district41.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Satna", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district42 = districtData.getJSONObject("Sehore");
            active = district42.getString("active");
            confirmed = district42.getString("confirmed");
            deceased = district42.getString("deceased");
            recovered = district42.getString("recovered");
            delta = district42.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Sehore", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district43 = districtData.getJSONObject("Seoni");
            active = district43.getString("active");
            confirmed = district43.getString("confirmed");
            deceased = district43.getString("deceased");
            recovered = district43.getString("recovered");
            delta = district43.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Seoni", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district44 = districtData.getJSONObject("Shahdol");
            active = district44.getString("active");
            confirmed = district44.getString("confirmed");
            deceased = district44.getString("deceased");
            recovered = district44.getString("recovered");
            delta = district44.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Shahdol", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district45 = districtData.getJSONObject("Shajapur");
            active = district45.getString("active");
            confirmed = district45.getString("confirmed");
            deceased = district45.getString("deceased");
            recovered = district45.getString("recovered");
            delta = district45.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Shajapur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district46 = districtData.getJSONObject("Sheopur");
            active = district46.getString("active");
            confirmed = district46.getString("confirmed");
            deceased = district46.getString("deceased");
            recovered = district46.getString("recovered");
            delta = district46.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Sheopur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district47 = districtData.getJSONObject("Shivpuri");
            active = district47.getString("active");
            confirmed = district47.getString("confirmed");
            deceased = district47.getString("deceased");
            recovered = district47.getString("recovered");
            delta = district47.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Shivpuri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district48 = districtData.getJSONObject("Sidhi");
            active = district48.getString("active");
            confirmed = district48.getString("confirmed");
            deceased = district48.getString("deceased");
            recovered = district48.getString("recovered");
            delta = district48.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Sidhi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district49 = districtData.getJSONObject("Singrauli");
            active = district49.getString("active");
            confirmed = district49.getString("confirmed");
            deceased = district49.getString("deceased");
            recovered = district49.getString("recovered");
            delta = district49.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Singrauli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district50 = districtData.getJSONObject("Tikamgarh");
            active = district50.getString("active");
            confirmed = district50.getString("confirmed");
            deceased = district50.getString("deceased");
            recovered = district50.getString("recovered");
            delta = district50.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Tikamgarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district51 = districtData.getJSONObject("Ujjain");
            active = district51.getString("active");
            confirmed = district51.getString("confirmed");
            deceased = district51.getString("deceased");
            recovered = district51.getString("recovered");
            delta = district51.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Ujjain", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district52 = districtData.getJSONObject("Umaria");
            active = district52.getString("active");
            confirmed = district52.getString("confirmed");
            deceased = district52.getString("deceased");
            recovered = district52.getString("recovered");
            delta = district52.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Umaria", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district53 = districtData.getJSONObject("Vidisha");
            active = district53.getString("active");
            confirmed = district53.getString("confirmed");
            deceased = district53.getString("deceased");
            recovered = district53.getString("recovered");
            delta = district53.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mp.add(new ListElementsDistrict("Vidisha", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, mp);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void mizoram(JSONObject response){
        try {
            JSONObject MZ = response.getJSONObject("Mizoram");
            JSONObject districtData = MZ.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Aizawl");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mz.add(new ListElementsDistrict("Aizawl", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Champhai");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mz.add(new ListElementsDistrict("Champhai", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Hnahthial");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mz.add(new ListElementsDistrict("Hnahthial", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Khawzawl");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mz.add(new ListElementsDistrict("Khawzawl", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Kolasib");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mz.add(new ListElementsDistrict("Kolasib", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Lawngtlai");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mz.add(new ListElementsDistrict("Lawngtlai", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Lunglei");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mz.add(new ListElementsDistrict("Lunglei", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Mamit");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mz.add(new ListElementsDistrict("Mamit", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Saiha");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mz.add(new ListElementsDistrict("Saiha", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Saitual");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mz.add(new ListElementsDistrict("Saitual", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Serchhip");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            mz.add(new ListElementsDistrict("Serchhip", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this,mz);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void nagaland(JSONObject response){
        try {
            JSONObject NL = response.getJSONObject("Nagaland");
            JSONObject districtData = NL.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district2 = districtData.getJSONObject("Dimapur");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            nl.add(new ListElementsDistrict("Dimapur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Kiphire");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            nl.add(new ListElementsDistrict("Kiphire", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Kohima");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            nl.add(new ListElementsDistrict("Kohima", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Longleng");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            nl.add(new ListElementsDistrict("Longleng", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Mokokchung");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            nl.add(new ListElementsDistrict("Mokokchung", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Mon");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            nl.add(new ListElementsDistrict("Mon", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Peren");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            nl.add(new ListElementsDistrict("Peren", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Phek");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            nl.add(new ListElementsDistrict("Phek", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Tuensang");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            nl.add(new ListElementsDistrict("Tuensang", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Wokha");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            nl.add(new ListElementsDistrict("Wokha", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Zunheboto");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            nl.add(new ListElementsDistrict("Zunheboto", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district1 = districtData.getJSONObject("Others");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            nl.add(new ListElementsDistrict("Others", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this,nl);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void odisha(JSONObject response){
        try {
            JSONObject OR = response.getJSONObject("Odisha");
            JSONObject districtData = OR.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district2 = districtData.getJSONObject("Angul");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Angul", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Balangir");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Balangir", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Balasore");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Balasore", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Bargarh");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Bargarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Bhadrak");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Bhadrak", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Boudh");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Boudh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Cuttack");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Cuttack", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Deogarh");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Deogarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Dhenkanal");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Dhenkanal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Gajapati");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Gajapati", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Ganjam");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Ganjam", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Jagatsinghpur");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Jagatsinghpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Jajpur");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Jajpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Jharsuguda");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Jharsuguda", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Kalahandi");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Kalahandi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Kandhamal");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Kandhamal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("Kendrapara");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Kendrapara", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Kendujhar");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Kendujhar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Khordha");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Khordha", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Koraput");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Koraput", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Malkangiri");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Malkangiri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district23 = districtData.getJSONObject("Mayurbhanj");
            active = district23.getString("active");
            confirmed = district23.getString("confirmed");
            deceased = district23.getString("deceased");
            recovered = district23.getString("recovered");
            delta = district23.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Mayurbhanj", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district24 = districtData.getJSONObject("Nabarangapur");
            active = district24.getString("active");
            confirmed = district24.getString("confirmed");
            deceased = district24.getString("deceased");
            recovered = district24.getString("recovered");
            delta = district24.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Nabarangapur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district25 = districtData.getJSONObject("Nayagarh");
            active = district25.getString("active");
            confirmed = district25.getString("confirmed");
            deceased = district25.getString("deceased");
            recovered = district25.getString("recovered");
            delta = district25.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Nayagarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district26 = districtData.getJSONObject("Nuapada");
            active = district26.getString("active");
            confirmed = district26.getString("confirmed");
            deceased = district26.getString("deceased");
            recovered = district26.getString("recovered");
            delta = district26.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Nuapada", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district27 = districtData.getJSONObject("Puri");
            active = district27.getString("active");
            confirmed = district27.getString("confirmed");
            deceased = district27.getString("deceased");
            recovered = district27.getString("recovered");
            delta = district27.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Puri", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district28 = districtData.getJSONObject("Rayagada");
            active = district28.getString("active");
            confirmed = district28.getString("confirmed");
            deceased = district28.getString("deceased");
            recovered = district28.getString("recovered");
            delta = district28.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Rayagada", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district29 = districtData.getJSONObject("Sambalpur");
            active = district29.getString("active");
            confirmed = district29.getString("confirmed");
            deceased = district29.getString("deceased");
            recovered = district29.getString("recovered");
            delta = district29.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Sambalpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district30 = districtData.getJSONObject("Subarnapur");
            active = district30.getString("active");
            confirmed = district30.getString("confirmed");
            deceased = district30.getString("deceased");
            recovered = district30.getString("recovered");
            delta = district30.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Subarnapur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district31 = districtData.getJSONObject("Sundargarh");
            active = district31.getString("active");
            confirmed = district31.getString("confirmed");
            deceased = district31.getString("deceased");
            recovered = district31.getString("recovered");
            delta = district31.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Sundargarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district1 = districtData.getJSONObject("Others");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            or.add(new ListElementsDistrict("Others", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));
            adapterDistrict = new ListAdapterDistrict(this,or);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void punjab(JSONObject response){
        try {
            JSONObject PB = response.getJSONObject("Punjab");
            JSONObject districtData = PB.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Amritsar");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Amritsar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Barnala");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Barnala", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Bathinda");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Bathinda", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Faridkot");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Faridkot", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Fatehgarh Sahib");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Fatehgarh Sahib", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Fazilka");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Fazilka", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Ferozepur");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Ferozepur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Gurdaspur");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Gurdaspur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Hoshiarpur");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Hoshiarpur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Jalandhar");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Jalandhar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Kapurthala");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Kapurthala", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Ludhiana");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Ludhiana", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Mansa");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Mansa", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district14 = districtData.getJSONObject("Moga");
            active = district14.getString("active");
            confirmed = district14.getString("confirmed");
            deceased = district14.getString("deceased");
            recovered = district14.getString("recovered");
            delta = district14.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Moga", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district15 = districtData.getJSONObject("Pathankot");
            active = district15.getString("active");
            confirmed = district15.getString("confirmed");
            deceased = district15.getString("deceased");
            recovered = district15.getString("recovered");
            delta = district15.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Pathankot", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district16 = districtData.getJSONObject("Patiala");
            active = district16.getString("active");
            confirmed = district16.getString("confirmed");
            deceased = district16.getString("deceased");
            recovered = district16.getString("recovered");
            delta = district16.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Patiala", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district17 = districtData.getJSONObject("Rupnagar");
            active = district17.getString("active");
            confirmed = district17.getString("confirmed");
            deceased = district17.getString("deceased");
            recovered = district17.getString("recovered");
            delta = district17.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Rupnagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district18 = districtData.getJSONObject("S.A.S. Nagar");
            active = district18.getString("active");
            confirmed = district18.getString("confirmed");
            deceased = district18.getString("deceased");
            recovered = district18.getString("recovered");
            delta = district18.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("S.A.S. Nagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district19 = districtData.getJSONObject("Sangrur");
            active = district19.getString("active");
            confirmed = district19.getString("confirmed");
            deceased = district19.getString("deceased");
            recovered = district19.getString("recovered");
            delta = district19.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Sangrur", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district20 = districtData.getJSONObject("Shahid Bhagat Singh Nagar");
            active = district20.getString("active");
            confirmed = district20.getString("confirmed");
            deceased = district20.getString("deceased");
            recovered = district20.getString("recovered");
            delta = district20.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Shahid Bhagat Singh Nagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district21 = districtData.getJSONObject("Sri Muktsar Sahib");
            active = district21.getString("active");
            confirmed = district21.getString("confirmed");
            deceased = district21.getString("deceased");
            recovered = district21.getString("recovered");
            delta = district21.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Sri Muktsar Sahib", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district22 = districtData.getJSONObject("Tarn Taran");
            active = district22.getString("active");
            confirmed = district22.getString("confirmed");
            deceased = district22.getString("deceased");
            recovered = district22.getString("recovered");
            delta = district22.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            pb.add(new ListElementsDistrict("Tarn Taran", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this,pb);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void puducherry(JSONObject response){
        try {
            JSONObject PY = response.getJSONObject("Puducherry");
            JSONObject districtData = PY.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Karaikal");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            py.add(new ListElementsDistrict("Karaikal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Mahe");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            py.add(new ListElementsDistrict("Mahe", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Puducherry");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            py.add(new ListElementsDistrict("Puducherry", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Yanam");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            py.add(new ListElementsDistrict("Yanam", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this,py);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void sikkim(JSONObject response){
        try {
            JSONObject SK = response.getJSONObject("Sikkim");
            JSONObject districtData = SK.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("East Sikkim");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            sk.add(new ListElementsDistrict("East Sikkim", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("North Sikkim");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            sk.add(new ListElementsDistrict("North Sikkim", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("South Sikkim");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            sk.add(new ListElementsDistrict("South Sikkim", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("West Sikkim");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            sk.add(new ListElementsDistrict("West Sikkim", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this,sk);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void tripura(JSONObject response){
        try {
            JSONObject TR = response.getJSONObject("Tripura");
            JSONObject districtData = TR.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Dhalai");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tr.add(new ListElementsDistrict("Dhalai", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Gomati");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tr.add(new ListElementsDistrict("Gomati", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Khowai");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tr.add(new ListElementsDistrict("Khowai", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("North Tripura");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tr.add(new ListElementsDistrict("North Tripura", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Sipahijala");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tr.add(new ListElementsDistrict("Sipahijala", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("South Tripura");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tr.add(new ListElementsDistrict("South Tripura", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Unokoti");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tr.add(new ListElementsDistrict("Unokoti", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("West Tripura");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            tr.add(new ListElementsDistrict("West Tripura", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this,tr);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void uttarakhand(JSONObject response){
        try {
            JSONObject UT = response.getJSONObject("Uttarakhand");
            JSONObject districtData = UT.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Almora");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ut.add(new ListElementsDistrict("Almora", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("Bageshwar");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ut.add(new ListElementsDistrict("Bageshwar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("Chamoli");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ut.add(new ListElementsDistrict("Chamoli", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Champawat");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ut.add(new ListElementsDistrict("Champawat", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district5 = districtData.getJSONObject("Dehradun");
            active = district5.getString("active");
            confirmed = district5.getString("confirmed");
            deceased = district5.getString("deceased");
            recovered = district5.getString("recovered");
            delta = district5.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ut.add(new ListElementsDistrict("Dehradun", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district6 = districtData.getJSONObject("Haridwar");
            active = district6.getString("active");
            confirmed = district6.getString("confirmed");
            deceased = district6.getString("deceased");
            recovered = district6.getString("recovered");
            delta = district6.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ut.add(new ListElementsDistrict("Haridwar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district7 = districtData.getJSONObject("Nainital");
            active = district7.getString("active");
            confirmed = district7.getString("confirmed");
            deceased = district7.getString("deceased");
            recovered = district7.getString("recovered");
            delta = district7.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ut.add(new ListElementsDistrict("Nainital", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district8 = districtData.getJSONObject("Pauri Garhwal");
            active = district8.getString("active");
            confirmed = district8.getString("confirmed");
            deceased = district8.getString("deceased");
            recovered = district8.getString("recovered");
            delta = district8.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ut.add(new ListElementsDistrict("Pauri Garhwal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district9 = districtData.getJSONObject("Pithoragarh");
            active = district9.getString("active");
            confirmed = district9.getString("confirmed");
            deceased = district9.getString("deceased");
            recovered = district9.getString("recovered");
            delta = district9.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ut.add(new ListElementsDistrict("Pithoragarh", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district10 = districtData.getJSONObject("Rudraprayag");
            active = district10.getString("active");
            confirmed = district10.getString("confirmed");
            deceased = district10.getString("deceased");
            recovered = district10.getString("recovered");
            delta = district10.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ut.add(new ListElementsDistrict("Rudraprayag", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district11 = districtData.getJSONObject("Tehri Garhwal");
            active = district11.getString("active");
            confirmed = district11.getString("confirmed");
            deceased = district11.getString("deceased");
            recovered = district11.getString("recovered");
            delta = district11.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ut.add(new ListElementsDistrict("Tehri Garhwal", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district12 = districtData.getJSONObject("Udham Singh Nagar");
            active = district12.getString("active");
            confirmed = district12.getString("confirmed");
            deceased = district12.getString("deceased");
            recovered = district12.getString("recovered");
            delta = district12.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ut.add(new ListElementsDistrict("Udham Singh Nagar", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district13 = districtData.getJSONObject("Uttarkashi");
            active = district13.getString("active");
            confirmed = district13.getString("confirmed");
            deceased = district13.getString("deceased");
            recovered = district13.getString("recovered");
            delta = district13.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            ut.add(new ListElementsDistrict("Uttarkashi", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this,ut);
            stateRecyclerView.setAdapter(adapterDistrict);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void stateUnassigned(JSONObject response){
        try {
            JSONObject UN = response.getJSONObject("State Unassigned");
            JSONObject districtData = UN.getJSONObject("districtData");
            JSONObject Unassigned = districtData.getJSONObject("Unassigned");
            active = Unassigned.getString("active");
            confirmed = Unassigned.getString("confirmed");
            deceased = Unassigned.getString("deceased");
            recovered = Unassigned.getString("recovered");
            JSONObject delta = Unassigned.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            un.add(new ListElementsDistrict("Unassigned", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            adapterDistrict = new ListAdapterDistrict(this, un);
            stateRecyclerView.setAdapter(adapterDistrict);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void andaman(JSONObject response){
        try {
            JSONObject AN = response.getJSONObject("Andaman and Nicobar Islands");
            JSONObject districtData = AN.getJSONObject("districtData");
            JSONObject delta;

            JSONObject district1 = districtData.getJSONObject("Nicobars");
            active = district1.getString("active");
            confirmed = district1.getString("confirmed");
            deceased = district1.getString("deceased");
            recovered = district1.getString("recovered");
            delta = district1.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            an.add(new ListElementsDistrict("Nicobars", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district2 = districtData.getJSONObject("North and Middle Andaman");
            active = district2.getString("active");
            confirmed = district2.getString("confirmed");
            deceased = district2.getString("deceased");
            recovered = district2.getString("recovered");
            delta = district2.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            an.add(new ListElementsDistrict("North and Middle Andaman", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district3 = districtData.getJSONObject("South Andaman");
            active = district3.getString("active");
            confirmed = district3.getString("confirmed");
            deceased = district3.getString("deceased");
            recovered = district3.getString("recovered");
            delta = district3.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            an.add(new ListElementsDistrict("South Andaman", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));

            JSONObject district4 = districtData.getJSONObject("Unknown");
            active = district4.getString("active");
            confirmed = district4.getString("confirmed");
            deceased = district4.getString("deceased");
            recovered = district4.getString("recovered");
            delta = district4.getJSONObject("delta");
            comfirmedDelta = delta.getString("confirmed");
            deceasedDelta = delta.getString("deceased");
            recoveredDelta = delta.getString("recovered");
            ad = Integer.parseInt(comfirmedDelta) - Integer.parseInt(deceasedDelta) - Integer.parseInt(recoveredDelta);
            activeDelta = Integer.toString(ad);
            an.add(new ListElementsDistrict("Unknown", confirmed, active, recovered, deceased, comfirmedDelta, activeDelta, recoveredDelta, deceasedDelta));


            adapterDistrict = new ListAdapterDistrict(this, an);
            stateRecyclerView.setAdapter(adapterDistrict);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}