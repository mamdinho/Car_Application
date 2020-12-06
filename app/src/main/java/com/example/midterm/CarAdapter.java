package com.example.midterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CarAdapter extends BaseAdapter {
    ArrayList<Car> arrayList;
    Context context;
    LayoutInflater inflater;

    CarAdapter(Context cntxt, ArrayList<Car> countryList){
        this.context = cntxt;
        arrayList = countryList;
        inflater = LayoutInflater.from(cntxt); //inflate this inflater from calling activity layout
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.car_image_row, null);
        TextView owner = (TextView)convertView.findViewById(R.id.nameOwnerId);
        ImageView carImage = (ImageView)convertView.findViewById(R.id.carImage);
        TextView carModel = (TextView)convertView.findViewById(R.id.modelId);
        TextView carYear = (TextView)convertView.findViewById(R.id.carYearId);

        owner.setText(arrayList.get(position).OwnerName);
        carImage.setImageBitmap(arrayList.get(position).imageData);
        carModel.setText(arrayList.get(position).model);
        carYear.setText(String.valueOf(arrayList.get(position).year));

        return convertView;
    }
}
