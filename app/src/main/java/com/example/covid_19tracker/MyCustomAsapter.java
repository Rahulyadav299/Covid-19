package com.example.covid_19tracker;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import static com.example.covid_19tracker.R.layout.notification_action;
import static com.example.covid_19tracker.R.layout.single_country_item;

public class MyCustomAsapter extends ArrayAdapter<CountryModel>  {
    private Context context;
    private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelListFiltered;

    public MyCustomAsapter(@NonNull Context context, List<CountryModel> countryModelList) {
        super(context, single_country_item,countryModelList);
        this.context=context;
        this.countryModelList=countryModelList;
       this.countryModelListFiltered=countryModelList;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(single_country_item,null,true);
        final TextView countryname_tv=view.findViewById(R.id.country_tv);
        final ImageView imageView=view.findViewById(R.id.flag_img);

        countryname_tv.setText(countryModelListFiltered.get(position).getCountry());
        Glide.with(context).load(countryModelListFiltered.get(position).getFlag()).into(imageView);



        return view;
    }

    @Override
    public int getCount() {

        return countryModelListFiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
       return position;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                if(constraint == null || constraint.length() == 0){
                    filterResults.count = countryModelList.size();
                    filterResults.values = countryModelList;

                }else{
                    List<CountryModel> resultsModel = new ArrayList<>();
                    String searchStr = constraint.toString().toLowerCase();

                    for(CountryModel itemsModel:countryModelList){
                        if(itemsModel.getCountry().toLowerCase().contains(searchStr)){
                            resultsModel.add(itemsModel);

                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }


                }

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                countryModelListFiltered= (List<CountryModel>) results.values;
                CountryACtivity.countryModelList = (List<CountryModel>) results.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }


//        @Override
//    public Filter getFilter() {
//        Filter filter = new Filter() {
//            @Override
//            protected FilterResults performFiltering(CharSequence constraint) {
//
//                FilterResults filterResults = new FilterResults();
//                if(constraint == null || constraint.length() == 0 ){
//                    filterResults.count = countryModelList.size();
//                    filterResults.values = countryModelList;
//
//                }else{
//                    List<CountryModel> resultsModel = new ArrayList<>();
//                    String searchStr = constraint.toString().toLowerCase();
//
//                    for(CountryModel itemsModel:countryModelList){
//                        if(itemsModel.getCountry().contains(searchStr)) {
//                            resultsModel.add(itemsModel);
//                        }
//                            filterResults.count = resultsModel.size();
//                            filterResults.values = resultsModel;
//                    }
//
//                }
//
//                return filterResults;
//            }
//            @Override
//            protected void publishResults(CharSequence constraint, FilterResults results) {
//
//                countryModelListFiltered= (List<CountryModel>) results.values;
//                CountryACtivity.countryModelList=(List<CountryModel>) results.values;
//                notifyDataSetChanged();
//
//            }
//        };
//        return filter;
//    }



//
//othermethod


//    searchview
//    method

}



