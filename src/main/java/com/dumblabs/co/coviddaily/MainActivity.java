package com.dumblabs.co.coviddaily;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button shareButton;
    TextView totalTextView;
    TextView totalDeltaTextView;
    TextView activeTextView;
    TextView activeDeltaTextView;
    TextView recoveredTextView;
    TextView recoveredDeltaTextView;
    TextView deceasedTextView;
    TextView deceasedDetlaTextView;


    LinearLayout precautionLinearLayout, symptomsLinearLayout;

    SwipeRefreshLayout swipeRefreshLayout;

    RequestQueue queue1;
    String url1 = "https://api.covid19india.org/data.json";
    JsonObjectRequest jsonObjectRequest1;

    RecyclerView recyclerView;
    ListAdapter adapter;

    List<ListElements> stateList;


    public void setInfoButton(View view) {
        Intent intent= new Intent(this,Info.class);
        startActivity(intent);
        overridePendingTransition(R.anim.enter_right_to_left,R.anim.no_animation_exit);
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
        setContentView(R.layout.activity_main);

        shareButton = findViewById(R.id.infoButton);
        totalTextView= findViewById(R.id.totalTextView);
        totalDeltaTextView= findViewById(R.id.totalDeltaTextView);
        activeTextView= findViewById(R.id.activeTextView);
        activeDeltaTextView= findViewById(R.id.activeDeltaTextView);
        recoveredTextView= findViewById(R.id.recoveredTextView);
        recoveredDeltaTextView= findViewById(R.id.recoveredDeltaTextView);
        deceasedTextView= findViewById(R.id.deceasedTextView);
        deceasedDetlaTextView=findViewById(R.id.deceasedDetlaTextView);


        precautionLinearLayout= findViewById(R.id.precautionsLinearLayout);
        symptomsLinearLayout= findViewById(R.id.symptomsLinearLayout);



        swipeRefreshLayout= findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                volleyRequest();
                swipeRefreshLayout.setRefreshing(false);
            }
        });


        stateList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        volleyRequest();

    }


    private void volleyRequest() {
        queue1 = Volley.newRequestQueue(this);

//      STATE REQUEST
        jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, url1, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            stateList.clear();
                            JSONArray statewise = response.getJSONArray("statewise");
                            for (int i = 0; i < statewise.length(); i++) {
                                JSONObject state = statewise.getJSONObject(i);
                                int a= (Integer.parseInt(state.getString("deltaconfirmed"))-(Integer.parseInt(state.getString("deltarecovered"))+Integer.parseInt(state.getString("deltadeaths"))));
                                stateList.add(new ListElements(state.getString("state"), state.getString("confirmed"), state.getString("active"),state.getString("recovered"),state.getString("deaths"),state.getString("deltaconfirmed"),Integer.toString(a),state.getString("deltarecovered"),state.getString("deltadeaths"),state.getString("statecode")));

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapterLV();


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            }
        });
        queue1.add(jsonObjectRequest1);


    }

    public void adapterLV()
    {


        if(stateList.get(0).getList_name_view().equals("Total")) {
            totalTextView.setText(stateList.get(0).getList_total_view());
            totalDeltaTextView.setText("+" + stateList.get(0).getDeltaTotal());
            activeTextView.setText(stateList.get(0).getActive());
            recoveredTextView.setText(stateList.get(0).getRecovered());
            recoveredDeltaTextView.setText("+" + stateList.get(0).getDeltaRecovered());
            deceasedTextView.setText(stateList.get(0).getDeath());
            deceasedDetlaTextView.setText("+" + stateList.get(0).getDeltaDeath());
            String aD= stateList.get(0).getDeltaActive();
            if (aD.contains("-")) {
                activeDeltaTextView.setText(aD);
            }
            else{
                activeDeltaTextView.setText("+"+aD);
            }
            stateList.remove(0);
        }

        adapter = new ListAdapter(this,stateList);
        recyclerView.setAdapter(adapter);


    }
}