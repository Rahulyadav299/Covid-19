package com.example.covid_19tracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class CountryInfo extends AppCompatActivity {
     TextView totalcases,todaycases,deaths,todaydeaths,recovered,todayrecovered,active,critical;
     int postioncoountry;
     TextView countryname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_info);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);




        Intent intent=getIntent();
        postioncoountry=intent.getIntExtra("position",0);

        getSupportActionBar().setTitle("Details of"+" "+CountryACtivity.countryModelList.get(postioncoountry).getCountry());

        totalcases=findViewById(R.id.cases);

        countryname=findViewById(R.id.country_name);
        todaycases=findViewById(R.id.today_cases);
        deaths=findViewById(R.id.death_cases);
        todaydeaths=findViewById(R.id.todaydeaths_cases);
        recovered=findViewById(R.id.recovered_cases);
        todayrecovered=findViewById(R.id.today_recovered);
        active=findViewById(R.id.active_cases);
        critical=findViewById(R.id.critical_cases);


        totalcases.setText(CountryACtivity.countryModelList.get(postioncoountry).getCases());
        todaycases.setText(CountryACtivity.countryModelList.get(postioncoountry).getTodaycases());
        deaths.setText(CountryACtivity.countryModelList.get(postioncoountry).getDeaths());
        todaydeaths.setText(CountryACtivity.countryModelList.get(postioncoountry).getTodaydeaths());
        recovered.setText(CountryACtivity.countryModelList.get(postioncoountry).getRecovered());
        todayrecovered.setText(CountryACtivity.countryModelList.get(postioncoountry).getTodayrecovered());
        active.setText(CountryACtivity.countryModelList.get(postioncoountry).getActive());
        critical.setText(CountryACtivity.countryModelList.get(postioncoountry).getCritical());
        countryname.setText(CountryACtivity.countryModelList.get(postioncoountry).getCountry());




    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                startActivity(new Intent(getApplicationContext(),CountryACtivity.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
