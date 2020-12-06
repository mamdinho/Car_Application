package com.example.midterm;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Car implements Parcelable {

    String OwnerName;
    Bitmap imageData;
    int year;
    String model;

    protected Car(Parcel in) {
        OwnerName = in.readString();
        imageData = in.readParcelable(Bitmap.class.getClassLoader());
        year = in.readInt();
        model = in.readString();
    }

    public static final Creator<Car> CREATOR = new Creator<Car>() {
        @Override
        public Car createFromParcel(Parcel in) {
            return new Car(in);
        }

        @Override
        public Car[] newArray(int size) {
            return new Car[size];
        }
    };

    public Car() {

    }

    public void setOwnerName(String owner){
        OwnerName = owner;
    }
    public void setImageData(Bitmap image){
        imageData = image;
    }
    public void setYear(int yr){
        year = yr;
    }
    public void setModel(String modl){
        model = modl;
    }

    public String getOwnerName(){
        return OwnerName;
    }

    public String getModel(){
        return model;
    }

    public Bitmap getImageData() {
        return imageData;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(OwnerName);
        dest.writeParcelable(imageData, flags);
        dest.writeInt(year);
        dest.writeString(model);
    }
}
