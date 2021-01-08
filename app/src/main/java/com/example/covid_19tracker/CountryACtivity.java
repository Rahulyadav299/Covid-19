package com.example.covid_19tracker;

import androidx.annotation.ArrayRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.file.Watchable;
import java.util.ArrayList;
import java.util.List;
import java.util.LongSummaryStatistics;

import static java.util.Locale.filter;

public class CountryACtivity extends AppCompatActivity {
    EditText editText;
    ListView listView;
    ProgressBar progressBar;

public  static List<CountryModel> countryModelList=new ArrayList<>();
     CountryModel countryModel;
     MyCustomAsapter myCustomAsapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_activity);

        getSupportActionBar().setTitle("Affected Countries");
        listView=findViewById(R.id.list_view);
        editText=findViewById(R.id.ediittext);
        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        fetchdata();

//        editText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//              filter(s.toString());
//
//                 }
//        });
//othermethod

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getApplicationContext(),CountryInfo.class)
                .putExtra("position",position));
    }
}
);
    }

//    private void filter(String text) {
//        ArrayList<CountryModel> filteredList=new ArrayList<>();
//
//        for(CountryModel item: countryModelList){
//            if(item.getCountry().toLowerCase().contains(text.toLowerCase())){
//                filteredList.add(item);
//
//            }
//
//            }
//        myCustomAsapter.filterList(filteredList);
//
//
//    }
//    othermethod


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchdata() {
        String url="https://corona.lmao.ninja/v2/countries";
        StringRequest request1=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for(int i=0;i<array.length();i++){
                        JSONObject object=array.getJSONObject(i);
                        String countryname=object.getString("country");
                        String cases=object.getString("cases");
                        String todaycases=object.getString("todayCases");
                        String death=object.getString("deaths");
                        String todaydeaths=object.getString("todayDeaths");
                        String recovered=object.getString("recovered");
                        String todayrecovered=object.getString("todayRecovered");
                        String active=object.getString("active");
                        String critical=object.getString("critical");


                        JSONObject object1=object.getJSONObject("countryInfo");
                        String flagurl=object1.getString("flag");
                        countryModel=new CountryModel(cases,todaycases,death,todaydeaths,recovered,todayrecovered,active,critical,flagurl,countryname);
//                         countryModel=new CountryModel(flagurl,countryname);
                        countryModelList.add(countryModel);
                    }
                    myCustomAsapter=new MyCustomAsapter(CountryACtivity.this,countryModelList);

                    progressBar.setVisibility(View.GONE);
                listView.setAdapter(myCustomAsapter);
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {


                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                            myCustomAsapter.getFilter().filter(s);


                            myCustomAsapter.notifyDataSetChanged();


                        }

                        @Override
                        public void afterTextChanged(Editable s) {


                        }
                    });


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CountryACtivity.this,error.getMessage()+"error occured",Toast.LENGTH_SHORT).show();

            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request1);
    }



    }

