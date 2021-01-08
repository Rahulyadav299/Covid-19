package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private TextView t1, t2, t3, t4, t5, t6, t7, t8, t9;
    TextView tvt, tvdeath, tvrec, tvactive;
    Button trckbtn;
    PieChart pieChart;
    ProgressBar progressBar;
    String json_url = "https://corona.lmao.ninja/v2/all";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = findViewById(R.id.total_case);
        t2 = findViewById(R.id.active_case);
        t3 = findViewById(R.id.recovered_case);
        t4 = findViewById(R.id.death_case);
        t5 = findViewById(R.id.today_case);
        t6 = findViewById(R.id.today_recovered);
        t7 = findViewById(R.id.today_death);
        t8 = findViewById(R.id.critical_case);
        t9 = findViewById(R.id.affected_country);

        tvt = findViewById(R.id.tc);
        trckbtn = findViewById(R.id.track_btn);
        tvdeath = findViewById(R.id.death);
        tvrec = findViewById(R.id.rec);
        tvactive = findViewById(R.id.active);
        pieChart = findViewById(R.id.piechart);
        progressBar = findViewById(R.id.progressbar);


        trckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CountryACtivity.class));
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        getdata();


    }


    public void getdata() {
        StringRequest request = new StringRequest(Request.Method.GET, json_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    t1.setText(jsonObject.getString("cases"));
                    t2.setText(jsonObject.getString("active"));
                    t3.setText(jsonObject.getString("recovered"));
                    t4.setText(jsonObject.getString("deaths"));
                    t5.setText(jsonObject.getString("todayCases"));
                    t6.setText(jsonObject.getString("todayRecovered"));
                    t7.setText(jsonObject.getString("todayDeaths"));
                    t8.setText(jsonObject.getString("critical"));
                    t9.setText(jsonObject.getString("affectedCountries"));

                    pieChart.addPieSlice(new PieModel("Active", Integer.parseInt(t2.getText().toString()), Color.parseColor("#FE6DA8")));
                    pieChart.addPieSlice(new PieModel("Recovered cases", Integer.parseInt(t3.getText().toString()), Color.parseColor("#56B7F1")));
                    pieChart.addPieSlice(new PieModel("Critical cases", Integer.parseInt(t8.getText().toString()), Color.parseColor("#CDA67F")));
                    pieChart.addPieSlice(new PieModel("Deaths", Integer.parseInt(t4.getText().toString()), Color.parseColor("#FED70E")));
                    pieChart.startAnimation();

                    progressBar.setVisibility(View.GONE);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "something went wrong", Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}

